package test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.InventoryItem;

import javax.persistence.*;

public class InventoryTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("IAmLegend2");
		em = emf.createEntityManager();
	}

	@Test
	public void InventoryItemTest() {
		InventoryItem item = em.find(InventoryItem.class, 1);
		assertEquals("XXXX", item.getName());
		assertEquals("XXXX", item.getDescription());
		assertEquals("XXXX", item.getWeight());
		assertEquals("XXXX", item.getPrice());
		assertEquals("XXXX", item.getImageUrl());
		assertEquals("XXXX", item.getQuantityInStock());
		assertEquals("XXXX", item.getWeight());
	
	}

	@After
	public void tearDown() throws Exception {
	}

}
