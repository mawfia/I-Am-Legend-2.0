package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_item")
public class InventoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private Double weight;
	private Double price;
	private String category;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "quantity_in_stock")
	private int quantityInStock;
	@OneToOne(mappedBy = "item")
	private Ammo ammo;
	@OneToOne(mappedBy = "item")
	private Equipment equipment;
	@OneToOne(mappedBy = "item")
	private Nutrition nutrition;
	@OneToOne(mappedBy = "item")
	private Optic optic;
	@OneToOne(mappedBy = "item")
	private Weapon weapon;
	@OneToMany(mappedBy = "inventoryItems", fetch=FetchType.EAGER)
	List<Cart> carts;

	public void addCart(Cart cart) {
		if (carts == null) {
			carts = new ArrayList<>();
		}
		if (!carts.contains(cart)) {
			carts.add(cart);
			cart.addInventoryItem(this);
		}
	}
	
	public void removeCart(Cart cart) {
		if (carts != null && carts.contains(cart)) {
			carts.remove(cart);
			cart.removeInventoryItem(this);
		}
	}

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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", name=" + name + ", description=" + description + ", weight=" + weight
				+ ", price=" + price + ", imageUrl=" + imageUrl + ", quantityInStock=" + quantityInStock + ", ammo="
				+ ammo + ", equipment=" + equipment + ", nutrition=" + nutrition + ", optic=" + optic + ", weapon="
				+ weapon + ", category=" + category + "]";
	}

}