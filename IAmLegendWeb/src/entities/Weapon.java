package entities;

public class Weapon {
	private String manufactuer;
	private String weaponType;
	private String countryOfOrigin;
	private int productionYear;
	private int length;
	private int barrelLenght;
	private int rateOfFire;
	private int muzzelVelocity;
	private int effectiveFiringRange;
	private int maximumFiringRange;
	private int inventoryItemId;
	
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

	@Override
	public String toString() {
		return "Weapon [manufactuer=" + manufactuer + ", weaponType=" + weaponType + ", countryOfOrigin="
				+ countryOfOrigin + ", productionYear=" + productionYear + ", length=" + length + ", barrelLenght="
				+ barrelLenght + ", rateOfFire=" + rateOfFire + ", muzzelVelocity=" + muzzelVelocity
				+ ", effectiveFiringRange=" + effectiveFiringRange + ", maximumFiringRange=" + maximumFiringRange
				+ ", inventoryItemId=" + inventoryItemId + "]";
	}
}
