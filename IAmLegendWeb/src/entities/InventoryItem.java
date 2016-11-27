package entities;

import javax.persistence.*;

@Entity
@Table(name="inventory_item")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private double weight;
	private double price;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "quantity_in_stock")
	private int quantityInStock;
	@OneToOne(mappedBy = "ammo")
	private Ammo ammo;
	@OneToOne(mappedBy = "equipment")
	private Equipment equipment;
	@OneToOne(mappedBy = "nutrition")
	private Nutrition nutrition;
	@OneToOne(mappedBy = "optic")
	private Optic optic;
	@OneToOne(mappedBy = "weapon")
	private Weapon weapon;

	public InventoryItem() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public int getId() {
		return id;
	}

	public Ammo getAmmo() {
		return ammo;
	}

	public void setAmmo(Ammo ammo) {
		this.ammo = ammo;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Nutrition getNutrition() {
		return nutrition;
	}

	public void setNutrition(Nutrition nutrition) {
		this.nutrition = nutrition;
	}

	public Optic getOptic() {
		return optic;
	}

	public void setOptic(Optic optic) {
		this.optic = optic;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", name=" + name + ", description=" + description + ", weight=" + weight
				+ ", price=" + price + ", imageUrl=" + imageUrl + ", quantityInStock=" + quantityInStock + "]";
	}
}