package entities;
import java.util.Collection;

public interface AuthenticationDAO {
	public User createAccount(User user);
	public Boolean registerEmail(String email);
	public Boolean registerPassword(String password);
	public User getUserAccount(User user);
	public Boolean validEmail(String email);
	public Boolean validPassword(String password);
	public void displayUsers();
	public Collection<User> values();
	public Collection<Integer> keySet();
	public Integer[] keyList();
	public User get(Integer accountNumber);
	public Boolean registerAge(Float age);
	public Boolean registerHeight(Float height);
	public Boolean registerZipcode(Integer zipcode);
	public Boolean registerWeight(Float weight);

}