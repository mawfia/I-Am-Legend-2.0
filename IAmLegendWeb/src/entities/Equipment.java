package entities;

public class Equipment {
	private int inventoryItemId;

	public int getInventoryItemId() {
		return inventoryItemId;
	}

	@Override
	public String toString() {
		return "Equipment [inventoryItemId=" + inventoryItemId + "]";
	}
}
