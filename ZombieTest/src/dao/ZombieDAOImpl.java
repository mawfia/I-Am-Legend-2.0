package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import entities.Cart;
import entities.InventoryItem;
import searchfilters.Filter;

@Transactional
public class ZombieDAOImpl implements ZombieDAO {
	Filter filter = new Filter();
	Cart cart;

	@PersistenceContext
	EntityManager em;

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
		List<InventoryItem> inventoryItems = new ArrayList<>();
		String sqlAllInCategory = "SELECT i FROM InventoryItem AS i WHERE i.category=?1 AND i.quantityInStock>0";
		String[] categories = { "WEAPON", "AMMO", "EQUIPMENT", "NUTRITION", "OPTIC", "WEAPON" };

		for (String category : categories) {
			List<InventoryItem> allInCategory = em.createQuery(sqlAllInCategory).setParameter(1, category).getResultList();
			Collections.shuffle(allInCategory);
			inventoryItems.add(allInCategory.get(0));
		}

		return inventoryItems;
	}

	public List<InventoryItem> getInvetoryItemsBySearch(InventoryItem inventoryItem) {
		System.out.println(inventoryItem);
		ArrayList<String> sql = filter.makeSQLbyInventoryItem(inventoryItem);
		Query query = em.createQuery(sql.get(0));
		for (int i = 1; i < sql.size(); i++) {
			query.setParameter(i, sql.get(i));
		}
		List<InventoryItem> inventoryItems = query.getResultList();

		return inventoryItems;
	}

	public String addItemToCart(InventoryItem inventoryItem, Integer quantity) {
		System.out.println(inventoryItem);
		int countAdded = 0;
		boolean soldOut = false;
		if (cart == null) {
			cart = new Cart();
		}
		int cartCount = 0;

		try {
			for (InventoryItem i : cart.getInventoryItems()) {
				System.out.println("Try succeeded");
				if (i.getId() == inventoryItem.getId()) {
					cartCount++;
				}
			}
		} catch (Exception e) {
			System.err.println("Cart is created");
		}

		for (int i = 0; i < quantity && !soldOut; i++) {
			InventoryItem item = em.find(InventoryItem.class, inventoryItem.getId());
			if (i + cartCount < item.getQuantityInStock()) {
				cart.addInventoryItem(inventoryItem);
				countAdded++;
			} else {
				soldOut = true;
			}
		}

		String report = null;
		if (countAdded > 0) {
			report = countAdded + "";
			if (countAdded > 1) {
				report += " have ";
			} else {
				report += " has ";
			}
			report += " been added to your cart. ";
		}
		if (soldOut) {
			report += "Sorry, we have no more " + inventoryItem.getName() + "s.";
		}

		return report;
	}

	public InventoryItem getInventoryItemById(int id) {
		return em.find(InventoryItem.class, id);
	}

	public Cart fetchCart() {
		return this.cart;
	}

}
