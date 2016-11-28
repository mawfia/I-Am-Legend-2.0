package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean active;
	@Column(name = "customer_account_number")
	private int customerAccountNumber;
	@Column(name = "survival_score")
	private int survivalScore;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@ManyToMany
	@JoinTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "inventory_item_id"))
	List<InventoryItem> inventoryItems;
	
	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public void addInventoryItem(InventoryItem inventoryItem) {
		if (inventoryItems == null) {
			inventoryItems = new ArrayList<>();
		}
		if (!inventoryItems.contains(inventoryItem)) {
			inventoryItems.add(inventoryItem);
			inventoryItem.addCart(this);
		}
	}

	public void removeInventoryItem(InventoryItem inventoryItem) {
		if (inventoryItems != null && inventoryItems.contains(inventoryItem)) {
			inventoryItems.remove(inventoryItem);
			inventoryItem.removeCart(this);
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCustomerAccountNumber(int customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

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

	public int getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", active=" + active + ", customerAccountNumber=" + customerAccountNumber
				+ ", survivalScore=" + survivalScore + "]";
	}
}
