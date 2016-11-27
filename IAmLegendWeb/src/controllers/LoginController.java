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


import entities.User;
import entities.UserAccessLevel;
import entities.AuthenticationDAOI;
import entities.Item;
import entities.ShoppingCart;

@Controller
@SessionAttributes({"ShoppingCart", "user"})
public class LoginController {
	
	@Autowired
	public AuthenticationDAOI users;
	
	@Autowired
	public ShoppingCart ShoppingCart;
	
	@ModelAttribute("user")
	public User verify(){
		User user = new User();
		return user;
	}
	
	@ModelAttribute("ShoppingCart")
	public ShoppingCart initCart(){
		//ShoppingCart = new ShoppingCart();
		return ShoppingCart;
	}
	
	
	  @RequestMapping("refresh.do")
	  public ModelAndView refresh(){
		  //System.out.println("In hanger refresh");
		  ModelAndView mv = new ModelAndView();
			User u = new User();
			mv.addObject("user", u);
		mv.setViewName("login.jsp");
		return mv;
	}
	

	  @RequestMapping(path="login.do", method=RequestMethod.POST)
	  public ModelAndView login(@Valid User user, Errors errors, @ModelAttribute("ShoppingCart") ShoppingCart ShoppingCart, @ModelAttribute("admin") User admin) {
	    ModelAndView mv = new ModelAndView();
	    //if(user.getAccessLevel() == UserAccessLevel.LOGOUT) {mv.setViewName("login.jsp"); return mv;}
//	    System.out.println("in do login");
	    Boolean validEmail = users.validEmail(user.getEmail());
	    Boolean validPassword = users.validPassword(user.getPassword());
	    // Determine if there are any errors.
	    mv.addObject("ShoppingCart", ShoppingCart);
	    if (errors.getErrorCount() != 0 || validEmail == null || validPassword == null || !validEmail  || !validPassword) {
	      // If there are any errors, return the login form.
	    	//if(!validEmail) mv.addObject("email", "Invalid email: \"" + user.getEmail() + "\" entered.");
	    	if( (validEmail == null || !validEmail) && validPassword) errors.rejectValue("email", "login.combination");
	    	else if(validEmail == null || !validEmail) errors.rejectValue("email", "login.email");
	    	
	    	if( (validPassword == null || !validPassword) && validEmail) errors.rejectValue("password", "login.combination");
	    	else if(validPassword == null || !validPassword) errors.rejectValue("password", "login.password");
	    	//if(!validPassword) mv.addObject("password", "Invalid password entered.");
	      mv.setViewName("login.jsp");
	      return mv;
	    }
	    
	    mv.addObject("users", users);
	    mv.addObject("user", users.getUserAccount(user));
	    // If no errors, send the user forward to the profile view.
	    mv.setViewName("profile.jsp");
	    return mv;
	  }
	  
	  @RequestMapping(path={"logout.do"})
	  public ModelAndView logout(@ModelAttribute("user") User user, @ModelAttribute("admin") User admin, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		  ModelAndView mv = new ModelAndView();
			  
			   response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
			   response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
			   response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
			   response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
			   if (user.getAccessLevel().equals(UserAccessLevel.LOGOUT)) {
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
			   
		  User reset = new User();
		  reset.setAccessLevel(UserAccessLevel.LOGOUT);
		  mv.addObject("reset", reset);
		  session.setAttribute("user", reset);
		  session.removeAttribute("user");
		  user = new User();
		  session.invalidate();

		  mv.setViewName("redirect:/index.html");
		  return mv;
	  }
	  
		 @RequestMapping(path="register.do")
		  public ModelAndView register(@Valid User user, Errors errors, @ModelAttribute("ShoppingCart") ShoppingCart ShoppingCart) {
		    ModelAndView mv = new ModelAndView();
		    //if(user.getAccessLevel() == UserAccessLevel.LOGOUT) {mv.setViewName("login.jsp"); return mv;}
		    mv.addObject("ShoppingCart", ShoppingCart);

		    Boolean registerEmail = users.registerEmail(user.getEmail());
		    Boolean registerPassword = users.registerPassword(user.getPassword());
		    Boolean registerAge = users.registerAge(user.getAge());
		    Boolean registerHeight = users.registerHeight(user.getHeight());
		    Boolean registerWeight = users.registerWeight(user.getWeight());
		    Boolean registerZipcode = users.registerZipcode(user.getZipcode());
		    
		    if(user.getEmail() == null) {
		    	mv.setViewName("register.jsp");
		    	System.out.println(user);

		    }
		    else if(errors.getFieldErrorCount() != 0 || registerEmail == null    || !registerEmail 
		    										 || registerPassword == null || !registerPassword 
		    										 || registerAge == null      || !registerAge 
		    										 || registerHeight == null   || !registerHeight 
		    										 || registerWeight == null   || !registerWeight 
		    										 || registerZipcode == null  || !registerZipcode) { 
		    	mv.setViewName("register.jsp");
		    	if(registerEmail == null) errors.rejectValue("email", "Email.user.email");		    	
		    	if(registerPassword ==  null || !registerPassword) errors.rejectValue("password", "Password.user.password");
		    	if(registerAge == null) errors.rejectValue("age", "Age.user.age");
		    	if(registerHeight == null) errors.rejectValue("height", "Height.user.age");
		    	if(registerWeight == null) errors.rejectValue("weight", "Weight.user.weight");
		    	if(registerZipcode == null) errors.rejectValue("zipcode", "Zipcode.user.zipcode");
		    	else if(!registerZipcode) errors.rejectValue("zipcode", "Pattern.user.zipcode");
		    	System.out.println("In error section");
		    	return mv; 
		    }
		    else if(users.createAccount(user) != null) { 
		    		mv.setViewName("profile.jsp"); mv.addObject("Result", "Successfully Registered.");
		    		users.displayUsers();
		    }
		    else { 
		    	mv.setViewName("register.jsp"); mv.addObject("Result", "Unsuccessful Registration.");
		    	System.out.println("in unsuccessful");
		    }
		    // Prime the model with an empty user object so that
		    // the form can populate it with values
		    mv.addObject("user", user);
		    return mv;
		  }
		 
		 @RequestMapping("update.do")
		 public ModelAndView updateAccountPage(@Valid User user, Errors errors, @RequestParam(name="update", required=false) Integer update){
			 ModelAndView mv = new ModelAndView();
			 mv.addObject("user", user); 
	
			 if(update != null){
				if(update < 0) mv.addObject("update", Math.abs(update));
				else if(errors.getFieldErrorCount() != 0) { mv.addObject("update", update); }
				else if(update > 0) mv.addObject("update", 0);
			}
			 
			 mv.setViewName("profile.jsp");
			 return mv;
		 }
}
