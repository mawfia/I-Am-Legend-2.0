package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.criterion.Property;

import entities.InventoryItem;
import searchfilters.Filter;

public class TestSearch {
	private Map<String, Property> properties = new HashMap<>();
	Filter filter = new Filter();

	public Map<String, Property> getPropertyByAddress(InventoryItem inventoryItem) {
		System.out.println(inventoryItem);
		EntityManager em = Persistence.createEntityManagerFactory("Unlivable").createEntityManager();
		ArrayList<String> sql = filter.makeSQLbyInventoryItem(inventoryItem);
		Query query = em.createQuery(sql.get(0));
		for (int i = 1; i < sql.size(); i++) {
			query.setParameter(i, sql.get(i));
		}
		List<Property> properties = query.getResultList();
		for (Property property : properties) {
			System.out.println(property);
		}

		// EntityTransaction tx = em.getTransaction();
		// tx.begin();
		// em.persist(a);
		// tx.commit();

		em.close();
		return null;
	}

}