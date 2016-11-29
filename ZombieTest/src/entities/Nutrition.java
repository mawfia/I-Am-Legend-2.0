package entities;

import javax.persistence.*;

@Entity
public class Nutrition {
	private String calories;
	@Column(name = "food_type")
	private String foodType;
	@Id
	@Column(name = "inventory_item_id")
	private int inventoryItemId;
	@OneToOne
	@JoinColumn(name = "inventory_item_id")
	private InventoryItem item;

	public Nutrition() {
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public int getInventoryItemId() {
		return inventoryItemId;
	}

	public InventoryItem getItem() {
		return item;
	}

	public void setItem(InventoryItem item) {
		this.item = item;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	@Override
	public String toString() {
		return "Nutrition [calories=" + calories + ", foodType=" + foodType + ", inventoryItemId=" + inventoryItemId
				+ ", item=" + item.getName() + "]";
	}


}
