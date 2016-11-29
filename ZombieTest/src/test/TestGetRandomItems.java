package test;

import java.util.List;

import dao.ZombieDAO;
import dao.ZombieDAOImpl;
import entities.InventoryItem;

public class TestGetRandomItems {
	public static void main(String[] args) {
		ZombieDAO zombieDAO = new ZombieDAOImpl();
		List<InventoryItem> iis = zombieDAO.getRandomItems();
		for (InventoryItem inventoryItem : iis) {
			System.out.println(inventoryItem);
		}
	}
}
