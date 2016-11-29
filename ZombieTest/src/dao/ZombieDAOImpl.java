package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Cart;
import entities.InventoryItem;
import searchfilters.Filter;

public class ZombieDAOImpl implements ZombieDAO {
	Filter filter = new Filter();
	Cart cart;

	// +----------------------+
	// | Tables_in_iamlegend2 |
	// +----------------------+
	// | ammo |
	// | cart |
	// | cart_items |
	// | customer |
	// | equipment |
	// | inventory_item |
	// | nutrition |
	// | optic |
	// | weapon |
	// +----------------------+

	public List<InventoryItem> getRandomItems() {
		EntityManager em = Persistence.createEntityManagerFactory("IAmLegend2").createEntityManager();
		List<InventoryItem> inventoryItems = new ArrayList<>();
		String sqlAllInCategory = "SELECT i FROM InventoryItem AS i WHERE i.category=?1 AND i.quantityInStock>0";
		String[] categories = { "WEAPON", "AMMO", "EQUIPMENT", "NUTRITION", "OPTIC", "WEAPON" };

		for (String category : categories) {
			List<InventoryItem> allInCategory = em.createQuery(sqlAllInCategory).setParameter(1, category)
					.getResultList();
			Collections.shuffle(allInCategory);
			inventoryItems.add(allInCategory.get(0));
		}

		em.close();
		return inventoryItems;
	}

	public List<InventoryItem> getInvetoryItemsBySearch(InventoryItem inventoryItem) {
		System.out.println(inventoryItem);
		EntityManager em = Persistence.createEntityManagerFactory("IAmLegend2").createEntityManager();
		ArrayList<String> sql = filter.makeSQLbyInventoryItem(inventoryItem);
		Query query = em.createQuery(sql.get(0));
		for (int i = 1; i < sql.size(); i++) {
			query.setParameter(i, sql.get(i));
		}
		List<InventoryItem> inventoryItems = query.getResultList();
		for (InventoryItem item : inventoryItems) {
			System.out.println(item);
		}

		// EntityTransaction tx = em.getTransaction();
		// tx.begin();
		// em.persist(a); //for additon of new inventoryItems only
		// tx.commit();

		em.close();
		return inventoryItems;
	}

	public String addItemToCart(InventoryItem inventoryItem, Integer quantity) {
		System.out.println(inventoryItem);
		int countAdded = 0;
		boolean soldOut = false;
		EntityManager em = Persistence.createEntityManagerFactory("IAmLegend2").createEntityManager();
		if (cart == null || cart.getInventoryItems().size() == 0) {
			cart = new Cart();
		}
		for (int i = 0; i < quantity; i++) {
			InventoryItem item = em.find(InventoryItem.class, inventoryItem.getId());
			if (item.getQuantityInStock() > 0) {
				cart.addInventoryItem(inventoryItem);
				countAdded++;
				item.setQuantityInStock(item.getQuantityInStock() - 1);
			} else {
				soldOut = true;
			}
		}
		String report = null;
		if (countAdded > 0) {
			report = countAdded + " added to cart. ";
		}
		if (soldOut) {
			report += "Sorry,  are no more remaining ";
		}

		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();
		return report;
	}

	public List<InventoryItem> checkStock(InventoryItem inventoryItem) {
		System.out.println(inventoryItem);
		EntityManager em = Persistence.createEntityManagerFactory("IAmLegend2").createEntityManager();

		// EntityTransaction tx = em.getTransaction();
		// tx.begin();
		// em.persist(a); //for additon of new inventoryItems only
		// tx.commit();

		em.close();
		return null;
	}

	public List<InventoryItem> fetchItems(String category) {

		return null;
	}

}
