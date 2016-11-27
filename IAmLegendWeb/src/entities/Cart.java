package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cart {
//	+-------------------------+------------+------+-----+---------+----------------+
//	| Field                   | Type       | Null | Key | Default | Extra          |
//	+-------------------------+------------+------+-----+---------+----------------+
//	| id                      | int(11)    | NO   | PRI | NULL    | auto_increment |
//	| active                  | tinyint(1) | NO   |     | NULL    |                |
//	| customer_account_number | int(11)    | NO   | PRI | NULL    |                |
//	| survival_score          | float      | NO   |     | NULL    |                |
//	| customer_id             | int(11)    | NO   | MUL | NULL    |                |
//	+-------------------------+------------+------+-----+---------+----------------+

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
