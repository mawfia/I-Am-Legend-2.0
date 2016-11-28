package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.InventoryItem;
import searchfilters.Filter;

public class TestSearch {
	Filter filter = new Filter();

	public List<InventoryItem> getInvetoryItemBySearch(InventoryItem inventoryItem) {
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

	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("IAmLegend2").createEntityManager();
		String queryString = "SELECT i FROM InventoryItem i WHERE i.id=5";
		Object[] ii = em.createQuery(queryString, Object[].class).getSingleResult();
		System.out.println(ii);
		TestSearch t = new TestSearch();
//		t.getInvetoryItemBySearch((InventoryItem) ii);
		
	}

}