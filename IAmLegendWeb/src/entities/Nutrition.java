package entities;

public class Nutrition {
	private String calories;
	private String category;
	private int inventoryItemId;
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getInventoryItemId() {
		return inventoryItemId;
	}
	@Override
	public String toString() {
		return "Nutrition [calories=" + calories + ", category=" + category + ", inventoryItemId=" + inventoryItemId
				+ "]";
	}
}
