package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.AuthenticationDAO;
import data.AuthenticationDAOI;
import entities.Cart;
import entities.Customer;
import entities.User;
import entities.UserAccessLevel;
import entities.Customer;

@Controller
@SessionAttributes({"cart", "customer", "admin"})
public class AdminController {

	@Autowired
	public AuthenticationDAO customers;
	
	
	@ModelAttribute("customer")
	public Customer verify(){
		Customer customer = new Customer();
		return customer;
	}
	
	@ModelAttribute("cart")
	public Cart initCart(){
		Cart cart = new Cart();
		return cart;
	}
	
	@ModelAttribute("admin")
	public Customer initAdmin(){
			Customer admin = new Customer();
			return admin;
	}
	
	 
	 @RequestMapping("admin.do")
	 public ModelAndView adminPage(@ModelAttribute("customer") Customer customer, @RequestParam(name="operation", required=false) Integer operation, @RequestParam(name="select", required=false) String select,
			 						@RequestParam(name="search", required=false) String search, @RequestParam(name="sort", required=false) Integer sort, @RequestParam(name="input", required=false) Integer input,
			 						@RequestParam(name="navigation", required=false) Integer navigation, @ModelAttribute("admin") Customer admin, @RequestParam(name="acct", required=false) Integer acct){
		 ModelAndView mv = new ModelAndView();

		 if(customer.getAccessLevel().equals(UserAccessLevel.ADMIN) && admin.getFirstName() == null) {
			 admin = customer.copyUser(customer);
//			 System.out.println("1acct="+acct + "\ncustomer="+customer+ "\nadmin="+admin);
			 customer = new Customer();
		 }		
		 else if(navigation != null && navigation == -2){
			 mv.addObject("customer", admin.copyUser(admin));
			 admin = new Customer();
			 mv.setViewName("profile.jsp");
//			 System.out.println("3acct="+acct + "\ncustomer="+customer+ "\nadmin="+admin);
			 return mv;
			 
		 }
		 else if(operation != null){
			 mv.addObject("admin", admin);			 
			 switch(operation){
				 case 1: mv.addObject("customer",  customer = customers.get(acct)); mv.setViewName("adminProfile.jsp"); return mv;
				 case 2: mv.addObject("register", "adminRegister.do"); mv.addObject("user", admin.getAccountNumber()); mv.setViewName("register.jsp"); mv.addObject("customer", new Customer()); 
				 System.out.println(admin);
				 return mv;
				 case 3: customers.removeAccount(customers.get(acct));
				default: break;
			 }
//					 	System.out.println("2acct="+acct + "\ncustomer="+customer+ "\nadmin="+admin + "\n");
			 
		 }
			 
		 
		 mv.addObject("admin", admin);
		 mv.addObject("customers",  customers.synchronizeDatabase());
		 mv.addObject("keyList",  customers.keyList());
		 mv.addObject("customer", customer);
//		 System.out.println("4acct="+acct + " operation=" + operation + " navigation=" + navigation + " select=" + select + " sort= " + sort + " search=" + search + " " + " input=" + input);
		 mv.setViewName("admin.jsp");
		 
		 return mv;
	 }
	 
	 @RequestMapping("adminUpdate.do")
	 public ModelAndView updateAccountPage(@Valid Customer customer, Errors errors, @RequestParam(name="update", required=false) Integer update, @ModelAttribute("admin") Customer admin){
		 ModelAndView mv = new ModelAndView();
		 
		 if(update != null){
			if(update < 0) mv.addObject("update", Math.abs(update));
			else if(errors.getFieldErrorCount() != 0) { mv.addObject("update", update); }
			else if(update > 0){ 
				customer = customers.updateAccount(customer);
				mv.addObject("update", 0); }
		 }

		 mv.addObject("customer", customer); 
		 mv.addObject("admin", admin.copyUser(customers.get(admin.getAccountNumber())));
		 mv.addObject("customers",  customers.synchronizeDatabase());
		 mv.addObject("keyList",  customers.keyList());
		 mv.setViewName("adminProfile.jsp");
		 return mv;
	 }
	 
	 @RequestMapping(path="adminRegister.do")
	 public ModelAndView AccountRegistration(@Valid Customer customer, Errors errors, Customer admin, @RequestParam(name="user", required=false) Integer user){
		 ModelAndView mv = new ModelAndView();
		 
		Boolean registerEmail = customers.registerEmail(customer.getEmail());
		Boolean registerPassword = customers.registerPassword(customer.getPassword());
		Boolean registerAge = customers.registerAge(customer.getAge());
		Boolean registerHeight = customers.registerHeight(customer.getHeight());
		Boolean registerWeight = customers.registerWeight(customer.getWeight());
		Boolean registerZipcode = customers.registerZipcode(customer.getZipcode());
		    
				if(errors.getFieldErrorCount() != 0 || registerEmail == null    || !registerEmail 
		    										 || registerPassword == null || !registerPassword 
		    										 || registerAge == null      || !registerAge 
		    										 || registerHeight == null   || !registerHeight 
		    										 || registerWeight == null   || !registerWeight 
		    										 || registerZipcode == null  || !registerZipcode) { 
		    	mv.addObject("register", "adminRegister.do"); mv.addObject("user", user); mv.setViewName("register.jsp");
		    	if(registerEmail == null) errors.rejectValue("email", "Email.customer.email");		    	
		    	if(registerPassword ==  null || !registerPassword) errors.rejectValue("password", "Password.customer.password");
		    	if(registerAge == null) errors.rejectValue("age", "Age.customer.age");
		    	if(registerHeight == null) errors.rejectValue("height", "Height.customer.age");
		    	if(registerWeight == null) errors.rejectValue("weight", "Weight.customer.weight");
		    	if(registerZipcode == null) errors.rejectValue("zipcode", "Zipcode.customer.zipcode");
		    	else if(!registerZipcode) errors.rejectValue("zipcode", "Pattern.customer.zipcode");
		    	return mv; 
		    }
		    else if(customers.createAccount(customer) != null) { 
		    		mv.addObject("Result", "Successfully Registered.");
		    		//customers.createAccount(new Customer(customer.getEmail(), customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getPassword(), customer.getAge(), customer.getHeight(), customer.getWeight(), customer.getZipcode(), UserAccessLevel.BASIC));
		    }
		    else { 
		    	mv.addObject("register", "adminRegister.do"); mv.addObject("Result", "Unsuccessful Registration.");
		    	System.out.println("in unsuccessful");
		    }
		 
		 mv.addObject("admin", admin.copyUser(customers.get(user)));
		 mv.addObject("customers",  customers.synchronizeDatabase());
		 mv.addObject("keyList",  customers.keyList());
		 System.out.println("In admin register");
		 mv.setViewName("admin.jsp");
		 
		 return mv;
		 
	 }
}
