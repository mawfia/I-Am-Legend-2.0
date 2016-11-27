package entities;
import javax.persistence.*;


@Entity
public class Optic {
	@Id
	@Column(name="inventory_item_id")
	private int inventoryItemId;

	public Optic(){}
	
	public int getInventoryItemId() {
		return inventoryItemId;
	}

	@Override
	public String toString() {
		return "Optic [inventoryItemId=" + inventoryItemId + "]";
	}
	
	
}
