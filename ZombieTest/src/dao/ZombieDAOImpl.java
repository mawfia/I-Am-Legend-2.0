package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import entities.InventoryItem;
import searchfilters.Filter;

public class ZombieDAOImpl implements ZombieDAO {
	Filter filter = new Filter();

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
		String sqlAllInCategory = "SELECT i FROM InventoryItem AS i WHERE i.category=?1";
		String[] categories = { "WEAPON", "AMMO", "EQUIPMENT", "NUTRITION", "OPTIC", "WEAPON" };
		
		for (String category : categories) {
			List<InventoryItem> allInCategory = em.createQuery(sqlAllInCategory).setParameter(1, category).getResultList();
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

	public List<InventoryItem> fetchItems(String category) {

		return null;
	}

}
