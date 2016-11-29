package dao;

import java.util.List;

import entities.InventoryItem;

public interface ZombieDAO {
	public List<InventoryItem> getRandomItems();
	
	public List<InventoryItem> getInvetoryItemsBySearch(InventoryItem inventoryItem);
}
