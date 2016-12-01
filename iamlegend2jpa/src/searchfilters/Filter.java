package searchfilters;

import java.util.ArrayList;

import entities.InventoryItem;

public class Filter {
	public ArrayList<String> makeSQLbyInventoryItem(InventoryItem inventoryItem) {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> criteria = new ArrayList<>();
		boolean and = false;
		int parameter = 1;

		sql.append("SELECT i FROM InventoryItem AS i WHERE i.quantityInStock>0 AND");

		if (inventoryItem.getCategory() != null && !inventoryItem.getCategory().equals("")) {
			sql.append(" category=?" + parameter++);
			criteria.add(inventoryItem.getCategory());
			and = true;
		}
		if (inventoryItem.getName() != null && !inventoryItem.getName().equals("")) {
			if (and)
				sql.append(" AND");
			sql.append(" description LIKE ?" + parameter++);
			criteria.add("%" + inventoryItem.getName() + "%");
			sql.append(" OR");
			sql.append(" name LIKE ?" + parameter++);
			criteria.add("%" + inventoryItem.getName() + "%");
			and = true;
		}

		if (!and)
			sql = new StringBuilder("SELECT i FROM InventoryItem AS i WHERE i.quantityInStock>0");

		criteria.add(0, sql.toString());
		for (String string : criteria) {
			System.out.println("In makeSQLbyAddress: " + string);
		}
		return criteria;
	}
}
