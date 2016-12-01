package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartItems {
	// +-------------------+---------+------+-----+---------+----------------+
	// | Field | Type | Null | Key | Default | Extra |
	// +-------------------+---------+------+-----+---------+----------------+
	// | cart_id | int(11) | NO | MUL | NULL | |
	// | inventory_item_id | int(11) | NO | MUL | NULL | |
	// | id | int(11) | NO | PRI | NULL | auto_increment |
	// | quantity | int(11) | YES | | NULL | |
	// +-------------------+---------+------+-----+---------+----------------+

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "inventory_items_id")
	private InventoryItem inventoryItem;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	private Integer quantity = 0;
	private Double itemsWeight;

	public Double getItemsWeight() {
		calcItemsWeight();
		return this.itemsWeight;
	}

	public void calcItemsWeight() {
		this.itemsWeight = this.getInventoryItem().getWeight() * this.getQuantity();
	}

	public Double getItemsCost() {
		calcItemsCost();
		return itemsCost;
	}

	public void calcItemsCost() {
		this.itemsCost = this.getInventoryItem().getPrice() * this.getQuantity();
	}

	private Double itemsCost;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void calcQuantity() {
		this.quantity = 0;
		List<CartItems> cartItems = this.getCart().getCartItems();
		for (CartItems cartItem : cartItems) {
			if (cartItem.getId() == this.id)
				this.quantity++;
		}
	}

	@Override
	public String toString() {
		return "CartItems [id=" + id + ", inventoryItem=" + inventoryItem + ", cart=" + cart + "]";
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getId() {
		return id;
	}

}
