package entities;
import javax.persistence.*;


@Entity
public class Ammo {
	@Id
	@Column(name="inventory_item_id")
	private int inventoryItemId;

	public Ammo(){}
	
	public int getInventoryItemId() {
		return inventoryItemId;
	}

	@Override
	public String toString() {
		return "Ammo [inventoryItemId=" + inventoryItemId + "]";
	}
}
