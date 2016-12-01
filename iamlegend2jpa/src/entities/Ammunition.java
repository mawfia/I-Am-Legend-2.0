
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ammunition {

	@Id
	@Column(name = "inventory_item_id")
	private Integer id;
	
	public Ammunition() {
	}


	@Override
	public String toString() {
		return "Ammunition []";
	}
	
}
