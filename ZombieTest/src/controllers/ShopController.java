package controllers;

import java.util.Collections;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.ZombieDAO;
import entities.Cart;
import entities.InventoryItem;

@Controller
@SessionAttributes({ "cart" })
public class ShopController {
	@Autowired
	private ZombieDAO zombieDAO;

	@ModelAttribute("cart")
	public Cart initCart() {
		return new Cart();
	}

	@ModelAttribute("frontpageitems")
	public List<InventoryItem> initFrontpageItems() {
		return zombieDAO.getRandomItems();
	}

	@RequestMapping(path = "LandingPage.do", method = RequestMethod.GET)
	public ModelAndView queryLandingPageInventoryItems() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItems", zombieDAO.getRandomItems());
		mv.addObject("inventoryItem", new InventoryItem());
		mv.setViewName("index.jsp");
		return mv;
	}

	@RequestMapping(path = "ViewWeapons.do", method = RequestMethod.GET)
	public ModelAndView queryAllWeapons() {
		InventoryItem weapons = new InventoryItem();
		weapons.setCategory("WEAPON");
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItems", zombieDAO.getInvetoryItemsBySearch(weapons));
		mv.addObject("inventoryItem", new InventoryItem());
		mv.setViewName("result.jsp");
		return mv;
	}

	@RequestMapping(path = "ViewAmmo.do", method = RequestMethod.GET)
	public ModelAndView queryAllAmmo() {
		InventoryItem ammo = new InventoryItem();
		ammo.setCategory("AMMO");
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItems", zombieDAO.getInvetoryItemsBySearch(ammo));
		mv.addObject("inventoryItem", new InventoryItem());
		mv.setViewName("result.jsp");
		return mv;
	}

	@RequestMapping(path = "ViewOptics.do", method = RequestMethod.GET)
	public ModelAndView queryAllOptics() {
		InventoryItem optic = new InventoryItem();
		optic.setCategory("OPTIC");
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItems", zombieDAO.getInvetoryItemsBySearch(optic));
		mv.addObject("inventoryItem", new InventoryItem());
		mv.setViewName("result.jsp");
		return mv;
	}

	@RequestMapping(path = "ViewNutrition.do", method = RequestMethod.GET)
	public ModelAndView queryAllNutrition() {
		InventoryItem nutrition = new InventoryItem();
		nutrition.setCategory("NUTRITION");
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItems", zombieDAO.getInvetoryItemsBySearch(nutrition));
		mv.addObject("inventoryItem", new InventoryItem());
		mv.setViewName("result.jsp");
		return mv;
	}

	@RequestMapping(path = "ViewEquipment.do", method = RequestMethod.GET)
	public ModelAndView queryAllEquipment() {
		InventoryItem equipment = new InventoryItem();
		equipment.setCategory("EQUIPMENT");
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItems", zombieDAO.getInvetoryItemsBySearch(equipment));
		mv.addObject("inventoryItem", new InventoryItem());
		mv.setViewName("result.jsp");
		return mv;
	}

	@RequestMapping(path = "ItemSearch.do", method = RequestMethod.POST)
	public ModelAndView queryInvetoryItemsByCategory(InventoryItem inventoryItem) {
		List<InventoryItem> filteredInventoryItems = zombieDAO.getInvetoryItemsBySearch(inventoryItem);
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItems", filteredInventoryItems);
		mv.setViewName("result.jsp");
		return mv;
	}

	@RequestMapping(path = "GetItemInfo.do", method = RequestMethod.GET)
	public ModelAndView getItemInfo(@RequestParam(name = "id") int id) {
		InventoryItem cat = new InventoryItem();
		InventoryItem picked = zombieDAO.getInventoryItemById(id);
		cat.setCategory(picked.getCategory());
		System.out.println(id);
		List<InventoryItem> filteredInventoryItems = zombieDAO.getInvetoryItemsBySearch(cat);
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItems", filteredInventoryItems);
		mv.addObject("inventoryItem", picked);
		mv.setViewName("itemInfo.jsp");
		return mv;
	}

	@RequestMapping(path = "AddItemToCart.do", method = RequestMethod.POST)
	public ModelAndView addItemToCart(@RequestParam(name = "id") int id,
			@RequestParam(name = "quantity") int quantity) {
		ModelAndView mv = new ModelAndView();
		InventoryItem addedItem = zombieDAO.getInventoryItemById(id);
		mv.addObject("confirmation", zombieDAO.addItemToCart(addedItem, quantity));
		mv.addObject("inventoryItem", addedItem);
		InventoryItem cat = new InventoryItem();
		cat.setCategory(addedItem.getCategory());
		mv.addObject("inventoryItems", zombieDAO.getInvetoryItemsBySearch(cat));
		mv.setViewName("confirmation.jsp");
		List<InventoryItem> inventoryItems = zombieDAO.fetchCart().getInventoryItems();
		int count = 1;
		for (InventoryItem inventoryItem : inventoryItems) {
			System.out.println("CART ITEM " + count + ": " + inventoryItem);
			count++;
		}
		return mv;
	}

	@RequestMapping(path = "ViewCart.do", method = RequestMethod.GET)
	public ModelAndView viewCart() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventoryItem", new InventoryItem());
		try {
			mv.addObject("cart", zombieDAO.fetchCart().getCartItems());
		} catch (Exception e) {
			mv.addObject("cart", "none");
		}
		List<InventoryItem> shuffledItems = zombieDAO.getInvetoryItemsBySearch(new InventoryItem());
		Collections.shuffle(shuffledItems);
		mv.addObject("inventoryItems", shuffledItems);
		mv.setViewName("cart.jsp");
		return mv;
	}
}