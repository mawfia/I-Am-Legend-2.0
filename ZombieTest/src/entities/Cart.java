package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean active;
	@Column(name = "total_price")
	private Double totalCost;
	@Column(name = "total_weight")
	private Double totalWeight;
	// @Column(name = "customer_account_number")
	// private int customerAccountNumber;
	@Column(name = "survival_score")
	private int survivalScore;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_account_number")
	private Customer customer;
	@OneToMany(mappedBy = "cart")
	private List<CartItems> cartItems;
	// @ManyToMany
	// @JoinTable(name = "cart_items", joinColumns = @JoinColumn(name =
	// "cart_id"), inverseJoinColumns = @JoinColumn(name = "inventory_item_id"))
	// List<InventoryItem> inventoryItems;

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public void calcTotalCost() {
		this.totalCost = 0.0;
		for (CartItems cartItem : cartItems) {
			this.totalCost += cartItem.getInventoryItem().getPrice();
		}
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public void calcTotalWeight() {
		this.totalCost = 0.0;
		for (CartItems cartItem : cartItems) {
			this.totalCost += cartItem.getInventoryItem().getWeight();
		}
	}

	public List<InventoryItem> getInventoryItems() {
		List<InventoryItem> inventoryItems= new ArrayList<>();
		for (CartItems cartItem : cartItems) {
			System.out.println(cartItem);
			inventoryItems.add(cartItem.getInventoryItem());
		}
		return inventoryItems;
	}
	//
	// public void setInventoryItems(List<InventoryItem> inventoryItems) {
	// this.inventoryItems = inventoryItems;
	// }

	public void addInventoryItem(InventoryItem inventoryItem) {
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}
		CartItems cartItem = new CartItems();
		cartItem.setInventoryItem(inventoryItem);
		cartItem.setCart(this);
		cartItems.add(cartItem);
		this.calcTotalCost();
		this.calcTotalWeight();
	}
	//
	// public void removeInventoryItem(InventoryItem inventoryItem) {
	// if (inventoryItems != null && inventoryItems.contains(inventoryItem)) {
	// inventoryItems.remove(inventoryItem);
	// inventoryItem.removeCart(this);
	// }
	// this.calcTotalCost();
	// this.calcTotalWeight();
	// }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// public void setCustomerAccountNumber(int customerAccountNumber) {
	// this.customerAccountNumber = customerAccountNumber;
	// }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getSurvivalScore() {
		return survivalScore;
	}

	public void setSurvivalScore(int survivalScore) {
		this.survivalScore = survivalScore;
	}

	public int getId() {
		return id;
	}
	//
	// public int getCustomerAccountNumber() {
	// return customerAccountNumber;
	// }

	@Override
	public String toString() {
		return "Cart [id=" + id + ", active=" + active + ", customerAccountNumber=" + ", survivalScore=" + survivalScore
				+ "]";
	}
}
