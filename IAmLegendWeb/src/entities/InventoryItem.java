package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private double weight;
	private double price;
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
	private String category;
	@OneToMany(mappedBy = "inventoryItem")
	List<CartItem> cartItems;

	public void addCartItem(CartItem cartItem) {
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}
		if (!cartItems.contains(cartItem)) {
			cartItems.add(cartItem);
			cartItem.setInventoryItem(this);
		}
	}

	public void removeCart(CartItem cartItem) {
		cartItem.setInventoryItem(null);
		if (cartItems != null) {
			cartItems.remove(cartItem);
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", name=" + name + ", description=" + description + ", weight=" + weight
				+ ", price=" + price + ", imageUrl=" + imageUrl + ", quantityInStock=" + quantityInStock + ", ammo="
				+ ammo + ", equipment=" + equipment + ", nutrition=" + nutrition + ", optic=" + optic + ", weapon="
				+ weapon + ", category=" + category + "]";
	}

}