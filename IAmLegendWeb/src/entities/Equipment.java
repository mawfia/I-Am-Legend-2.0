package entities;
import javax.persistence.*;


@Entity
public class Equipment {
	@Id
	@Column(name="inventory_item_id")
	private int inventoryItemId;
	@OneToOne
	@JoinColumn(name="inventory_item_id")
	private InventoryItem item;
	
	public Equipment(){}

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
		return "Equipment [inventoryItemId=" + inventoryItemId + ", item=" + item.getName() + "]";
	}

}
