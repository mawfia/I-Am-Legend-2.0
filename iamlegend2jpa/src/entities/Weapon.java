package entities;

import javax.persistence.*;

@Entity
public class Weapon {
	private String manufactuer;
	@Column(name = "weapon_type")
	private String weaponType;
	@Column(name = "country_of_origin")
	private String countryOfOrigin;
	@Column(name = "production_year")
	private String productionYear;
	private String length;
	@Column(name = "barrel_length")
	private String barrelLenght;
	@Column(name = "rate_of_fire")
	private String rateOfFire;
	@Column(name = "muzzel_velocity")
	private String muzzelVelocity;
	@Column(name = "effective_firing_range")
	private String effectiveFiringRange;
	@Column(name = "maximum_firing_range")
	private String maximumFiringRange;
	@Id
	@Column(name = "inventory_item_id")
	private int inventoryItemId;
	@OneToOne
	@JoinColumn(name = "inventory_item_id")
	private InventoryItem item;

	public Weapon() {
	}

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

	public String getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getBarrelLenght() {
		return barrelLenght;
	}

	public void setBarrelLenght(String barrelLenght) {
		this.barrelLenght = barrelLenght;
	}

	public String getRateOfFire() {
		return rateOfFire;
	}

	public void setRateOfFire(String rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	public String getMuzzelVelocity() {
		return muzzelVelocity;
	}

	public void setMuzzelVelocity(String muzzelVelocity) {
		this.muzzelVelocity = muzzelVelocity;
	}

	public String getEffectiveFiringRange() {
		return effectiveFiringRange;
	}

	public void setEffectiveFiringRange(String effectiveFiringRange) {
		this.effectiveFiringRange = effectiveFiringRange;
	}

	public String getMaximumFiringRange() {
		return maximumFiringRange;
	}

	public void setMaximumFiringRange(String maximumFiringRange) {
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
