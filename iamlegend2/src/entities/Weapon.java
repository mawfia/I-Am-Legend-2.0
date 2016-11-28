package entities;
import javax.persistence.*;


@Entity
public class Weapon {
	private String manufactuer;
	@Column(name="weapon_type")
	private String weaponType;
	@Column(name="country_of_origin")
	private String countryOfOrigin;
	@Column(name="production_year")
	private int productionYear;
	private int length;
	@Column(name="barrel_length")
	private int barrelLenght;
	@Column(name="rate_of_fire")
	private int rateOfFire;
	@Column(name="muzzel_velocity")
	private int muzzelVelocity;
	@Column(name="effective_firing_range")
	private int effectiveFiringRange;
	@Column(name="maximum_firing_range")
	private int maximumFiringRange;
	@Id
	@Column(name="inventory_item_id")
	private int inventoryItemId;
	@OneToOne
	@JoinColumn(name="inventory_item_id")
	private InventoryItem item;
	
	public Weapon(){}
	
	public String getManufactuer() {
		return manufactuer;
	}
	public void setManufactuer(String manufactuer) {
		this.manufactuer = manufactuer;
	}
	public String getWeaponType() {
		return weaponType;
	}
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public int getProductionYear() {
		return productionYear;
	}
	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getBarrelLenght() {
		return barrelLenght;
	}
	public void setBarrelLenght(int barrelLenght) {
		this.barrelLenght = barrelLenght;
	}
	public int getRateOfFire() {
		return rateOfFire;
	}
	public void setRateOfFire(int rateOfFire) {
		this.rateOfFire = rateOfFire;
	}
	public int getMuzzelVelocity() {
		return muzzelVelocity;
	}
	public void setMuzzelVelocity(int muzzelVelocity) {
		this.muzzelVelocity = muzzelVelocity;
	}
	public int getEffectiveFiringRange() {
		return effectiveFiringRange;
	}
	public void setEffectiveFiringRange(int effectiveFiringRange) {
		this.effectiveFiringRange = effectiveFiringRange;
	}
	public int getMaximumFiringRange() {
		return maximumFiringRange;
	}
	public void setMaximumFiringRange(int maximumFiringRange) {
		this.maximumFiringRange = maximumFiringRange;
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
		return "Weapon [manufactuer=" + manufactuer + ", weaponType=" + weaponType + ", countryOfOrigin="
				+ countryOfOrigin + ", productionYear=" + productionYear + ", length=" + length + ", barrelLenght="
				+ barrelLenght + ", rateOfFire=" + rateOfFire + ", muzzelVelocity=" + muzzelVelocity
				+ ", effectiveFiringRange=" + effectiveFiringRange + ", maximumFiringRange=" + maximumFiringRange
				+ ", inventoryItemId=" + inventoryItemId + ", item=" + item.getName() + "]";
	}


}
