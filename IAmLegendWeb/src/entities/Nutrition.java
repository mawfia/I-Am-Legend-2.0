package entities;
import javax.persistence.*;



@Entity
public class Nutrition {
	private String calories;
	private String category;
	@Id
	@Column(name="inventory_item_id")
	private int inventoryItemId;
	@OneToOne
	@JoinColumn(name="inventory_item_id")
	private InventoryItem item;
	
	public Nutrition(){}
	
	
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
	public InventoryItem getItem() {
		return item;
	}
	
	
	public void setItem(InventoryItem item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "Nutrition [calories=" + calories + ", category=" + category + ", inventoryItemId=" + inventoryItemId
				+ "]";
	}


}
