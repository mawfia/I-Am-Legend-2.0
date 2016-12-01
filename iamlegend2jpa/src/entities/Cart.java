package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cart {
	// +-------------------------+------------+------+-----+---------+----------------+
	// | Field | Type | Null | Key | Default | Extra |
	// +-------------------------+------------+------+-----+---------+----------------+
	// | id | int(11) | NO | PRI | NULL | auto_increment |
	// | active | tinyint(1) | NO | | NULL | |
	// | customer_account_number | int(11) | NO | PRI | NULL | |
	// | survival_score | float | NO | | NULL | |
	// | customer_id | int(11) | NO | MUL | NULL | |
	// +-------------------------+------------+------+-----+---------+----------------+

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean active;
//	@Column(name = "customer_account_number")
//	private int customerAccountNumber;
	@Column(name = "survival_score")
	private int survivalScore;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "customer_account_number")
	private Customer customer;
	@OneToMany(mappedBy = "cart", cascade=CascadeType.ALL)
	List<CartItem> cartItems;

	public void addCartItem(CartItem cartItem) {
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}
		if (!cartItems.contains(cartItem)) {
			cartItems.add(cartItem);
			cartItem.setCart(this);
		}
	}

	public void removeCart(CartItem cartItem) {
		cartItem.setCart(null);
		if (cartItems != null) {
			cartItems.remove(cartItem);
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

//	public void setCustomerAccountNumber(int customerAccountNumber) {
//		this.customerAccountNumber = customerAccountNumber;
//	}

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

//	public int getCustomerAccountNumber() {
//		return customerAccountNumber;
//	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", active=" + active + ", customerAccountNumber=" + customer.getLastName()  
				+ ", survivalScore=" + survivalScore + "]";
	}
}