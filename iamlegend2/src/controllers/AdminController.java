package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import entities.AuthenticationDAO;
import entities.Cart;
import entities.User;
import entities.UserAccessLevel;

@Controller
@SessionAttributes({"Cart", "user", "admin"})
public class AdminController {

	@Autowired
	public AuthenticationDAO users;
	
	@Autowired
	public Cart Cart;
	
	@ModelAttribute("user")
	public User verify(){
		User user = new User();
		return user;
	}
	
	@ModelAttribute("Cart")
	public Cart initCart(){
		//ShoppingCart = new ShoppingCart();
		return Cart;
	}
	
	@ModelAttribute("admin")
	public User initAdmin(){
		System.out.println("test");
			User admin = new User();
			return admin;
	}
	
	 
	 @RequestMapping("admin.do")
	 public ModelAndView adminPage(@ModelAttribute("user") User user, @RequestParam(name="operation", required=false) Integer operation, @RequestParam(name="select", required=false) String select,
			 						@RequestParam(name="search", required=false) String search, @RequestParam(name="sort", required=false) Integer sort, @RequestParam(name="input", required=false) Integer input,
			 						@RequestParam(name="navigation", required=false) Integer navigation, @ModelAttribute("admin") User admin, @RequestParam(name="acct", required=false) Integer acct){
		 ModelAndView mv = new ModelAndView();


		 if(user.getAccessLevel().equals(UserAccessLevel.ADMIN) && admin.getFirstName() == null) {
			 admin = user.copyUser(user);
			 //System.out.println("1acct="+acct + "\nuser="+user+ "\nadmin="+admin);
			 user = new User();
		 }
		 else if(acct != null) { 
			mv.addObject("admin", admin);
		 	mv.addObject("user",  user = users.get(acct));
		 	mv.setViewName("adminUpdate.do");
		 	//System.out.println("2acct="+acct + "\nuser="+user+ "\nadmin="+admin + "\n");
		 	return mv;
		 }
		 //System.out.println("3acct="+acct + "\nuser="+user+ "\nadmin="+admin);

		 mv.addObject("admin", admin);
		 mv.addObject("users",  users);
		 mv.addObject("keyList",  users.keyList());
		 //mv.addObject("user", user);
		 //System.out.println("account="+acct + " operation=" + operation + " navigation=" + navigation + " select=" + select + "sort= " + " search=" + search + " " + " input=" + input);
		 mv.setViewName("admin.jsp");
		 
		 return mv;
	 }
	 
	 @RequestMapping("adminUpdate.do")
	 public ModelAndView updateAccountPage(@Valid User user, Errors errors, @RequestParam(name="update", required=false) Integer update, @ModelAttribute("admin") User admin){
		 ModelAndView mv = new ModelAndView();
		 

		 if(update != null){
			if(update < 0) mv.addObject("update", Math.abs(update));
			else if(errors.getFieldErrorCount() != 0) { mv.addObject("update", update); }
			else if(update > 0) mv.addObject("update", 0);
		}
		 
		 mv.addObject("user", user); 
		 mv.addObject("admin", admin.copyUser(users.getUserAccount(admin)));
		 mv.setViewName("adminProfile.jsp");
		 return mv;
	 }
}
