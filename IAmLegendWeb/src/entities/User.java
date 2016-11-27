package data;

//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;

@Repository
public class User implements Comparator<User>{
	
	private Integer accountNumber;
	private Float accountBalance;
	//@NotNull
	//@Size(min=6, max=30)
	@Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message="Pattern.user.email")
	private String email;
	//@Size(min=6, message="Size.user.password")
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[\\w\\d]{6,12}$", message="Pattern.user.password")
	private String password;
	@Size(min=2, max=30, message="Size.user.firstName")
	private String firstName;
	private String middleName;
	@Size(min=2, max=30, message="Size.user.lastName")
	private String lastName;
	@DecimalMin("18")
	@DecimalMax("130")
	private Float age;
	@DecimalMin("56")
	@DecimalMax("100")
	private Float height;
	@DecimalMin("85")
	@DecimalMax("700")
	private Float weight;
	//@Pattern(regexp="\\d{5}(-\\d{4})?", message="Pattern.user.zipcode")
	private Integer zipcode;
	private String securityQuestion1;
	private String securityQuestion2;
	private UserAccessLevel accessLevel;
	private ArrayList<ShoppingCart> shoppingHistory;
	
	public User() {
		this.accessLevel = UserAccessLevel.GUEST;
	}
	
	public User(String e, String fn, String mn, String ln, String p, Float a, Float h, Float w, Integer z, UserAccessLevel accessLevel) {
		this.accountBalance = 0.0F;
		this.email = e;
		this.firstName = fn;
		this.lastName = ln;
		this.middleName = mn;
		this.password = p;
		this.age = a;
		this.height = h;
		this.weight = w;
		this.zipcode = z;
		this.accessLevel = accessLevel;
		this.shoppingHistory = null;
		this.accountNumber = Math.abs(this.hashCode());
	}
	
	public User(Integer acct, Float balance, String e, String fn, String mn, String ln, String p, Float a, Float h, Float w, Integer z, UserAccessLevel accessLevel){
		this(e, fn, mn, ln, p, a, h, w, z, accessLevel);
		this.accountNumber = acct;
		this.accountBalance = balance;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		if(accountNumber == null )this.accountNumber = Math.abs(this.hashCode());
		else this.accountNumber = accountNumber;
	}
	
	public Float getAccountBalance(){
		return accountBalance;
	}
	
	public void setAccountBalance(Float accountBalance){
		this.accountBalance = accountBalance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName(){
		return this.middleName;
	}
	
	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}
	
	public Float getHeight(){
		return this.height;
	}
	
	public void setHeight(Float height){
		this.height = height;
	}
	
	public Float getWeight(){
		return this.weight;
	}
	
	public void setWeight(Float weight){
		this.weight = weight;
	}
	
	public Integer getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
	public UserAccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(UserAccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public ArrayList<ShoppingCart> getShoppingHistory() {
		return this.shoppingHistory;
	}
	
	public void setShoppingCartHistory(ShoppingCart ShoppingCart) {
		this.shoppingHistory.add(ShoppingCart);
	}
	
	public boolean addShoppingCartToHistory(ShoppingCart ShoppingCart){
		return shoppingHistory.add(ShoppingCart);
	}
	
	public boolean deleteCartFromHistory(ShoppingCart cart){
		return shoppingHistory.remove(cart);
	}
	
	public User copyUser(User user){
		
		String email = new String(user.email);
		String fname = new String(user.firstName);
		String mname;
		if(user.middleName != null) {mname = new String(user.middleName);}
		else { mname = null; }
		String lname = new String(user.lastName);
		String password = new String(user.password);
		float balance = user.accountBalance;
		float age = user.age;
		float height = user.height;
		float weight = user.weight;
		int zipcode = user.zipcode;
		UserAccessLevel accessLevel = user.accessLevel;
		int accountNumber = user.accountNumber;
		
		return new User(accountNumber, balance, email, fname, mname, lname, password, age, height, weight, zipcode, accessLevel);
	}

	@Override
	public int compare(User u1, User u2) {
		if(u1.lastName.compareTo(u2.lastName) == 0)
			if(u1.firstName.compareTo(u2.firstName) == 0) return u1.accountNumber.compareTo(u2.accountNumber);
			else return u1.firstName.compareTo(u2.firstName);
		else return u1.lastName.compareTo(u2.lastName);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessLevel == null) ? 0 : accessLevel.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accessLevel != other.accessLevel)
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [accountNumber=" + accountNumber + ", email=" + email + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", age=" + age + ", zipcode=" + zipcode + ", accessLevel="
				+ accessLevel + ", Shopping History=" + shoppingHistory + "]";
	}
	

}