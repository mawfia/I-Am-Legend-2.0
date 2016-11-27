package entities;

public class Customer {
	private int id;
	private int accountBalance;
	private String email;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String age;
	private int zipcode;
	private int height;
	private int weight;
	private String accessLevel;
	private String orderStatus;
	private String securityQuestion1;
	private String securityQuestion2;
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
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", accountBalance=" + accountBalance + ", email=" + email + ", password="
				+ password + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", age=" + age + ", zipcode=" + zipcode + ", height=" + height + ", weight=" + weight
				+ ", accessLevel=" + accessLevel + ", orderStatus=" + orderStatus + ", securityQuestion1="
				+ securityQuestion1 + ", securityQuestion2=" + securityQuestion2 + "]";
	}
	
}
