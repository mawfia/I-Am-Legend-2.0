package data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import entities.Customer;
import entities.Customer;

public interface AuthenticationDAO {
	public Customer createAccount(Customer customer);
//	public User createAccount(User user);
	public Boolean registerEmail(String email);
	public Boolean registerPassword(String password);
	public Customer getCustomerAccount(Customer customer);
	public Boolean validEmail(String email);
	public Boolean validPassword(String password);
	public void displayCustomers();
	public Collection<Customer> values();
	public Collection<Integer> keySet();
	public Integer[] keyList();
	public Customer get(Integer accountNumber);
	public Boolean registerAge(Float age);
	public Boolean registerHeight(Float height);
	public Boolean registerZipcode(Integer zipcode);
	public Boolean registerWeight(Float weight);
	public Customer updateAccount(Customer c);
	public Customer getCustomer(Customer customer);
	String[] evaluateZipcode(String zipcode);
	public Map<Integer, Customer> JPQLQuery();
	public Map<Integer, Customer> synchronizeDatabase();
	boolean removeAccount(Customer customer);

}