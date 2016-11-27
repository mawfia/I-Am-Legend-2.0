package entities;
import javax.persistence.*;


@Entity
public class Equipment {
	@Id
	private int inventoryItemId;
	
	public Equipment(){}

	public int getInventoryItemId() {
		return inventoryItemId;
	}

	@Override
	public String toString() {
		return "Equipment [inventoryItemId=" + inventoryItemId + "]";
	}
}
