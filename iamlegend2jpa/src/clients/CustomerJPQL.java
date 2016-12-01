package clients;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import entities.Customer;

public class CustomerJPQL {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("iamlegend2");
		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = em.getTransaction();

		
		Customer cust = em.find(Customer.class, 774171271);
//		Cart cart = em.find(Cart.class, 2);
//		cart.setActive(true);
//		cart.setSurvivalScore(99);
//		cust.setAge(35F);
//		cust.setOrderStatus("Pending");
//		System.out.println(cart);
		System.out.println(cust);
		
		//Customer customer = new Customer("mawfia@gmail.com", "George", null, "Washington", "Password0", 22F, 71F, 175F,94102, UserAccessLevel.BASIC);
		
		//em.getTransaction().begin();
		//Customer cust = em.find(Customer.class, 1503781774);
//		tx.begin();
		//em.remove(cust);
		//em.persist(customer);
		//em.getTransaction().commit();
		
		//tx.commit();
		em.close();
		emf.close();
	}

}
