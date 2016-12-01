package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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


import entities.Customer;
import entities.User;
import entities.UserAccessLevel;
import data.AuthenticationDAO;
import entities.Cart;

@Controller
@SessionAttributes({"cart", "customer"})
public class LoginController {
	
	
	@Autowired
	public AuthenticationDAO customers;
	
//	@Autowired
//	public Cart Cart;
	
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
	
	
	  @RequestMapping("refresh.do")
	  public ModelAndView refresh(){
		  //System.out.println("In hanger refresh");
		  ModelAndView mv = new ModelAndView();
			Customer u = new Customer();
			mv.addObject("customer", u);
		mv.setViewName("login.jsp");
		return mv;
	}
	

	  @RequestMapping(path="login.do", method=RequestMethod.POST)
	  public ModelAndView login(@Valid Customer customer, Errors errors) {
	    ModelAndView mv = new ModelAndView();
	    //customers.JPQLQuery();
	    //if(customer.getAccessLevel() == CustomerAccessLevel.LOGOUT) {mv.setViewName("login.jsp"); return mv;}
	    Boolean validEmail = customers.validEmail(customer.getEmail());
	    Boolean validPassword = customers.validPassword(customer.getPassword());
	    //customers.getCustomer(customer);
	    // Determine if there are any errors.
	    //mv.addObject("Cart", Cart);
	    if (errors.getErrorCount() != 0 || validEmail == null || validPassword == null || !validEmail  || !validPassword) {
	      // If there are any errors, return the login form.
	    	//if(!validEmail) mv.addObject("email", "Invalid email: \"" + customer.getEmail() + "\" entered.");
	    	if( (validEmail == null || !validEmail) && (validPassword !=null && validPassword)) errors.rejectValue("email", "login.combination");
	    	else if(validEmail == null || !validEmail) errors.rejectValue("email", "login.email");
	    	
	    	if( (validPassword == null || !validPassword) && (validEmail !=null && validEmail)) errors.rejectValue("password", "login.combination");
	    	else if(validPassword == null || !validPassword) errors.rejectValue("password", "login.password");
	    	//if(!validPassword) mv.addObject("password", "Invalid password entered.");
	      mv.setViewName("login.jsp");
	      return mv;
	    }
	    
	    mv.addObject("customers", customers);
	    mv.addObject("customer", customers.getCustomer(customer));
	    // If no errors, send the customer forward to the profile view.
	    mv.setViewName("profile.jsp");
	    return mv;
	  }
	  
	  @RequestMapping(path={"logout.do"})
	  public ModelAndView logout(@ModelAttribute("customer") Customer customer, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		  ModelAndView mv = new ModelAndView();
			  
			   response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
			   response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
			   response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
			   response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
			   if (customer.getAccessLevel().equals(UserAccessLevel.LOGOUT)) {
			      request.setAttribute("Error", "Session has ended.  Please login.");
			      RequestDispatcher rd = request.getRequestDispatcher("redirect:/index.html");
			      try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			   }
			   
		  Customer reset = new Customer();
		  reset.setAccessLevel(UserAccessLevel.LOGOUT);
		  mv.addObject("reset", reset);
		  session.setAttribute("customer", reset);
		  session.removeAttribute("customer");
		  customer = new Customer();
		  session.invalidate();

		  mv.setViewName("redirect:/index.html");
		  return mv;
	  }
	  
		 @RequestMapping(path="register.do")
		  public ModelAndView register(@Valid Customer customer, Errors errors) {
		    ModelAndView mv = new ModelAndView();
		    //if(customer.getAccessLevel() == CustomerAccessLevel.LOGOUT) {mv.setViewName("login.jsp"); return mv;}
		    //mv.addObject("Cart", Cart);

		    Boolean registerEmail = customers.registerEmail(customer.getEmail());
		    Boolean registerPassword = customers.registerPassword(customer.getPassword());
		    Boolean registerAge = customers.registerAge(customer.getAge());
		    Boolean registerHeight = customers.registerHeight(customer.getHeight());
		    Boolean registerWeight = customers.registerWeight(customer.getWeight());
		    Boolean registerZipcode = customers.registerZipcode(customer.getZipcode());
		    
		    if(customer.getEmail() == null) {
		    	mv.addObject("register", "register.do"); mv.setViewName("register.jsp");
		    	//System.out.println(customer);

		    }
		    else if(errors.getFieldErrorCount() != 0 || registerEmail == null    || !registerEmail 
		    										 || registerPassword == null || !registerPassword 
		    										 || registerAge == null      || !registerAge 
		    										 || registerHeight == null   || !registerHeight 
		    										 || registerWeight == null   || !registerWeight 
		    										 || registerZipcode == null  || !registerZipcode) { 
		    	mv.addObject("register", "register.do"); mv.setViewName("register.jsp");
		    	if(registerEmail == null) errors.rejectValue("email", "Email.customer.email");		    	
		    	if(registerPassword ==  null || !registerPassword) errors.rejectValue("password", "Password.customer.password");
		    	if(registerAge == null) errors.rejectValue("age", "Age.customer.age");
		    	if(registerHeight == null) errors.rejectValue("height", "Height.customer.age");
		    	if(registerWeight == null) errors.rejectValue("weight", "Weight.customer.weight");
		    	if(registerZipcode == null) errors.rejectValue("zipcode", "Zipcode.customer.zipcode");
		    	else if(!registerZipcode) errors.rejectValue("zipcode", "Pattern.customer.zipcode");
		    	//System.out.println("In error section");
		    	return mv; 
		    }
		    else if(customers.createAccount(customer) != null) { 
		    		mv.setViewName("profile.jsp"); mv.addObject("Result", "Successfully Registered.");
		    		//customers.displayCustomers();
		    		//customers.createAccount(new Customer(customer.getEmail(), customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getPassword(), customer.getAge(), customer.getHeight(), customer.getWeight(), customer.getZipcode(), UserAccessLevel.BASIC));
		    }
		    else { 
		    	mv.addObject("register", "register.do"); mv.addObject("Result", "Unsuccessful Registration.");
		    	System.out.println("in unsuccessful");
		    }
		    // Prime the model with an empty customer object so that
		    // the form can populate it with values
		    mv.addObject("customer", customer);
		    return mv;
		  }
		 
		 @RequestMapping("update.do")
		 public ModelAndView updateAccountPage(@Valid Customer customer, Errors errors, @RequestParam(name="update", required=false) Integer update){
			 ModelAndView mv = new ModelAndView();
	
			 if(update != null){
				if(update < 0) mv.addObject("update", Math.abs(update));
				else if(errors.getFieldErrorCount() != 0) { mv.addObject("update", update); }
				else if(update > 0) { 
					customer = customers.updateAccount(customer);
					mv.addObject("update", 0); }
			 }
			 
			 mv.addObject("customer", customer); 
			 mv.setViewName("profile.jsp");
			 return mv;
		 }
}
