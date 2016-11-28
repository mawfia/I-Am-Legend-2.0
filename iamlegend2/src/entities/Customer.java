package entities;

<<<<<<< HEAD:iamlegend2/src/entities/Customer.java
public class Customer implements Comparator<Customer>{
	
	iamprivate int id;
=======
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Customer {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "account_balance")
>>>>>>> 53983a3ac093b469213e87a192ed810d4ee4c398:IAmLegendWeb/src/entities/Customer.java
	private int accountBalance;
	private String email;
	private String password;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	private String age;
	private int height;
	private int weight;
	@Column(name = "access_level")
	private String accessLevel;
	@Column(name = "order_status")
	private String orderStatus;
	@Column(name = "security_question_1")
	private String securityQuestion1;
	@Column(name = "security_question_2")
	private String securityQuestion2;
<<<<<<< HEAD:iamlegend2/src/entities/Customer.java
	private int zipcode;
	
	public int getId() {
		return id;
	}
	
=======
	@OneToMany(mappedBy = "customer")
	List<Cart> carts;

	public void addCart(Cart cart) {
		if (carts == null) {
			carts = new ArrayList<>();
		}
		if (!carts.contains(cart)) {
			carts.add(cart);
			cart.setCustomer(this);
		}
	}

	public void removeCart(Cart cart) {
		cart.setCustomer(null);
		if (carts != null) {
			carts.remove(cart);
		}
	}

>>>>>>> 53983a3ac093b469213e87a192ed810d4ee4c398:IAmLegendWeb/src/entities/Customer.java
	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}
<<<<<<< HEAD:iamlegend2/src/entities/Customer.java
=======

	public int getId() {
		return id;
	}

>>>>>>> 53983a3ac093b469213e87a192ed810d4ee4c398:IAmLegendWeb/src/entities/Customer.java
	@Override
	public String toString() {
		return "Customer [id=" + id + ", accountBalance=" + accountBalance + ", email=" + email + ", password="
				+ password + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", age=" + age + ", zipcode=" + zipcode + ", height=" + height + ", weight=" + weight
				+ ", accessLevel=" + accessLevel + ", orderStatus=" + orderStatus + ", securityQuestion1="
				+ securityQuestion1 + ", securityQuestion2=" + securityQuestion2 + "]";
	}

}
