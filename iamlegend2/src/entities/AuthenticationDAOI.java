package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

public class AuthenticationDAOI implements AuthenticationDAO{
	Map<Integer, User> users = new HashMap<>();
	
	
	@PostConstruct
	public void init() {
		User user = new User("admin@admin.com", "Michael", "Andrew", "Williams", "Password0", 22F, 71F, 175F,80111, UserAccessLevel.ADMIN);
		this.users.put(user.getAccountNumber(), user);
		user = new User("mawfia@gmail.com", "George", null, "Washington", "Password0", 22F, 71F, 175F,80111, UserAccessLevel.BASIC);
		user.setAccountBalance(1000F);
		this.users.put(user.getAccountNumber(), user);
	}
	
	@Override
	public User getUserAccount(User user){
		for(User u: users.values())
			if (u.getEmail().equals(user.getEmail()) && user.getPassword().equals(user.getPassword())) return u;
			
		return null;
	}
	
	@Override
	public User createAccount(User user) {
		if (!users.containsKey(user.getAccountNumber())) {
			user.setAccessLevel(UserAccessLevel.BASIC); user.setAccountNumber(null);
			users.put(user.getAccountNumber(), user);
			return user;		
		}
		return null;
	}
	
	@Override
	public Boolean validEmail(String email){
		if(email == null || email.length() < 6) return null;
		else for(User u: users.values()) if (u.getEmail().equals(email))  return true;
		
		return false;
	}
	
	@Override
	public Boolean validPassword(String password){
		if(password == null || password.length() < 6 || password.length() > 12) return null;
		else for(User u: users.values()) if (u.getPassword().equals(password))  return true;
		
		return false;
	}

	@Override
	public Boolean registerEmail(String email) {
		if(email == null || email.length() < 6) return false; 
		else for(User u : users.values()) if(u.getEmail().equals(email)) return null;
		
		return true;
	}
	
	@Override
	public Boolean registerPassword(String password){
		if(password == null || !password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[\\w\\d]{6,12}$")) return false;
		else return true;
	}
	
	@Override
	public User get(Integer accountNumber){
		return users.get(accountNumber);
	}
	
	public Boolean registerAge(Float age){
		if(age == null ) return null;
		else if(age < 0 || age > 130) return false;
		else return true;
	}
	
	public Boolean registerHeight(Float height){
		if(height == null) return null;
		else if(height < 56 || height > 100) return false;
		else return true;
	}
	
	public Boolean registerWeight(Float weight){
		if(weight == null) return null;
		else if(weight < 85 || weight > 700) return false;
		else return true;
	}
	
	public Boolean registerZipcode(Integer zipcode){
		if(zipcode == null) return null;
		else if(zipcode < 501 || zipcode > 99950) return false;
		else return true;
	}
	
	@Override
	public void displayUsers(){
		for(User u : users.values()) System.out.println(u);
	}
	
	@Override
	public Collection<User> values(){
		return  users.values();
	}
	
	@Override
	public Collection<Integer> keySet(){
		return users.keySet();
	}
	
	@Override
	public Integer[] keyList(){
		Integer[] keyArray = new Integer[users.size()];
		int i= 0;
		for(Integer u: users.keySet()) {keyArray[i]=u; i++;}
		return keyArray;
	}
	
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