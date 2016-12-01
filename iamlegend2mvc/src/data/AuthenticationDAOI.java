package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import entities.User;
import entities.UserAccessLevel;
import entities.Cart;
import entities.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AuthenticationDAOI implements AuthenticationDAO{
//	Map<Integer, User> users = new HashMap<>();
	Map<Integer, Customer> customers = new HashMap<>();


	@PersistenceContext
	private EntityManager em;
	
	
	
	
	@PostConstruct
	public void init() {
//		User user = new User("admin@admin.com", "Michael", "Andrew", "Williams", "Password0", 22F, 71F, 175F,80111, UserAccessLevel.ADMIN);
//		this.users.put(user.getAccountNumber(), user);
//		user = new User("mawfia@gmail.com", "George", null, "Washington", "Password0", 22F, 71F, 175F,80111, UserAccessLevel.BASIC);
//		user.setAccountBalance(1000F);
//		this.users.put(user.getAccountNumber(), user);
		
		List<Customer> c = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
		for(Customer c1: c) customers.put(c1.getAccountNumber(), c1);
	}
	
	@Override
	public Map<Integer, Customer> synchronizeDatabase(){
		
//		for(Customer c : customers.values()) em.merge(c);
		customers = new HashMap<Integer, Customer>();
		List<Customer> database = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
		for(Customer c : database) customers.put(c.getAccountNumber(), c);
		
		return customers;
	}
	
	@Override
	public Customer getCustomer(Customer customer){
		
		Customer c = null;
		try{
				if((c = em.createQuery("SELECT c FROM Customer c WHERE c.email = ?1 and c.password = ?2", Customer.class).
				setParameter(1, customer.getEmail()).
				setParameter(2, customer.getPassword()).
				getSingleResult()) != null) return c;
		}
		catch(NoResultException NRE){
			System.err.println(NRE);
			return c;
		}
		
		return c;
	}
	
	public Map<Integer, Customer> JPQLQuery(){
//		String query = "SELECT c FROM "+table+" c WHERE c." +query1+ "= '" + query2  + "'";
		String query = "select c from Customer c order by c.id";
		List<Customer> cust = em.createQuery(query, Customer.class).getResultList();
		for (Customer customer : cust) {
			System.out.println(customer.getEmail());
		}
		Map<Integer, Customer> customers = new HashMap<>();
		for(Customer c : cust) customers.put(c.getAccountNumber(), c);

		return null;
	}
	
	@Override
	public Customer getCustomerAccount(Customer customer){
		for(Customer c: customers.values())
			if (c.getEmail().equals(customer.getEmail()) && customer.getPassword().equals(customer.getPassword())) return c;
			
		return null;
	}
	
	public Customer updateAccount(Customer c){

		em.merge(c);

		return em.find(Customer.class, c.getAccountNumber());
	}
	
	@Override
	public Customer get(Integer accountNumber) throws NoResultException{
		
		Customer c = null;
		try{
				if( (c = em.find(Customer.class, accountNumber)) !=  null) return c;
			}
			catch(NoResultException NRE){
				System.err.println(NRE);
				return c;
			}
		
		return customers.get(accountNumber); //  Update to return c;
	}
	
	@Override
	public Customer createAccount(Customer customer){
		
//		Customer c = em.createQuery("SELECT c FROM Customer c WHERE c.id = ?1", Customer.class).setParameter(1,customer.getAccountNumber()).getSingleResult();
		//Customer c = null;
//		try{
//				if( (c = em.find(Customer.class, customer.getAccountNumber())) !=  null) return c;
//			}
//			catch(NoResultException NRE){
//				System.err.println(NRE);
//			}
		
		if(em.contains(em.find(Customer.class, customer.getAccountNumber()))) return null;
		customer.setAccessLevel(UserAccessLevel.BASIC);
		customers.put(customer.getAccountNumber(), customer); 
		em.persist(customer);
		return customer;
	}
	
	
	@Override
	public boolean removeAccount(Customer customer){
		
		em.remove(em.find(Customer.class, customer.getAccountNumber()));
		
		if(em.contains(customer)) return false;
		return true;
	}
 
	
	@Override
	public Boolean validEmail(String email){
		if(email == null || email.length() < 6) return null;
		else 
			try{
				if(em.createQuery("SELECT c FROM Customer c WHERE c.email = ?1", Customer.class).setParameter(1, email.toLowerCase()).getSingleResult() != null) return true;
			}
			catch(NoResultException NRE){
				System.err.println(NRE);
				return false;
			}
		
		return false;
	}
	
	@Override
	public Boolean validPassword(String password){
		if(password == null || password.length() < 6 || password.length() > 12) return null;
		else 
			try{
				if(em.createQuery("SELECT c FROM Customer c WHERE c.password = ?1", Customer.class).setParameter(1, password).getResultList() != null) return true;
			}
			catch(NoResultException NRE){
				System.err.println(NRE);
				return false;
			}
		
		return false;
	}

	@Override
	public Boolean registerEmail(String email) {
		List<Customer> c = null;
		if(email == null || email.length() < 6) return false; 
		else try{
					c = em.createQuery("SELECT c FROM Customer c WHERE c.email = ?1", Customer.class).setParameter(1, email).getResultList();
			
				}
				catch(NoResultException NRE){
						System.err.println(NRE);
						return true;
				}
		if( c.size() > 0) return null;
//			for(User u : users.values()) if(u.getEmail().equals(email)) return null;
			
		return true;
	}
	
	@Override
	public Boolean registerPassword(String password){
		if(password == null || !password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[\\w\\d]{6,12}$")) return false;
		else return true;
	}
	

	
	@Override
	public Boolean registerAge(Float age){
		if(age == null ) return null;
		else if(age < 0 || age > 130) return false;
		else return true;
	}
	
	@Override
	public Boolean registerHeight(Float height){
		if(height == null) return null;
		else if(height < 56 || height > 100) return false;
		else return true;
	}
	
	@Override
	public Boolean registerWeight(Float weight){
		if(weight == null) return null;
		else if(weight < 85 || weight > 700) return false;
		else return true;
	}
	
	@Override
	public Boolean registerZipcode(Integer zipcode){
		if(zipcode == null) return null;
		else if(zipcode < 501 || zipcode > 99950) return false;
		else return true;
	}
	
	@Override
	public void displayCustomers(){
		for(Customer c : customers.values()) System.out.println(c);
	}
	
	@Override
	public Collection<Customer> values(){
		return  customers.values();
	}
	
	@Override
	public Collection<Integer> keySet(){
		return customers.keySet();
	}
	
	@Override
	public Integer[] keyList(){
		Integer[] keyArray = new Integer[customers.size()];
		int i= 0;
		for(Integer c: customers.keySet()) { keyArray[i]=c; i++; }
		return keyArray;
	}
	
	@Override
	public String[] evaluateZipcode(String zipcode){
		String APIURL = "http://api.zip-codes.com/ZipCodesAPI.svc/1.0/GetZipCodeDetails/"+zipcode+"?key=DEMOAPIKEY";  // If demo key is disabled use registered key: NU0O1RG3Q3BA620QHF95
		String filter = "(CountiesArea\": \")(\\d+).*[\\r?\\n]{1,}\\W+(\"ZipCodePopulation\": \")(\\d+).+([\\r?\\n].*){1,}\"Latitude\": \"([\\d.-]+).*[\\r?\\n]{1,}.*(\"Longitude\": \")"+
						"([\\d.-]+).*[\\r?\\n]{1,}.*(\"Elevation\": \")([\\d.]+).*([\\r?\\n].*){1,}[\\r?\\n]{1,}.+\"State\": \"(\\w{2}).*([\\r?\\n]{1,}.*){1,20}\"City\": \"([\\w -]+)";
		URLConnection conn = null;
		Reader reader = null;
		String geoData[] = new String[7];
		String line = null;
		geoData[0] = "Area (sqft): ";
		geoData[1] = "Area Population: ";
		geoData[2] = "Latitute: ";
		geoData[3] = "Longitute: ";
		geoData[4] = "Elevation (ft): ";
		geoData[5] = "State: ";
		geoData[6] = "City/Town: ";
		
		//For reading an API stream
		try{
			URL url = new URL(APIURL);
			conn = url.openConnection();
			reader = new InputStreamReader(conn.getInputStream());
			char[] charInput = new char[conn.getContentLength()];
			reader.read(charInput);
			line = new String(charInput);
			//System.out.println(line);
		}
		catch(IOException ioe1){
			System.err.println(ioe1);
		}
		finally{
				try {
						reader.close();
					} catch (IOException ioe2) {
						System.err.println(ioe2);
					}
		}
		
		Pattern p = Pattern.compile(filter);
		Matcher m = p.matcher(line);
		//if(m.find()) { System.out.println(m.group(14));}
		if(m.find()) for (int i =0, j = 2; i < geoData.length; i++, j+=2) { geoData[i] = new String (geoData[i] + m.group(j)); }
		//for(String g : geoData) System.out.println(g);}
	
		return geoData;
	}
	
}