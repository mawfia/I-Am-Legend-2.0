package entities;
import javax.persistence.*;


@Entity
public class Ammo {
	@Id
	@Column(name="inventory_item_id")
	private int inventoryItemId;
	@OneToOne
	@JoinColumn(name="inventory_item_id")
	private InventoryItem item;

	public Ammo(){}
	
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
		return "Ammo [inventoryItemId=" + inventoryItemId + ", item=" + item.getName() + "]";
	}

}
