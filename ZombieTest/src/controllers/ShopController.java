package controllers;

import java.util.List;

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
	public ModelAndView getItemInfo(@RequestParam(name="id") int id) {
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
	public ModelAndView addItemToCart(@RequestParam(name="id") int id,@RequestParam(name="quantity") int quantity) {
		ModelAndView mv = new ModelAndView();
		InventoryItem addedItem = zombieDAO.getInventoryItemById(id);
		mv.addObject("confirmation", zombieDAO.addItemToCart(addedItem, quantity));
		mv.addObject("cart", zombieDAO.fetchCart());
		mv.setViewName("confirmation.jsp");
		List<InventoryItem> inventoryItems = zombieDAO.fetchCart().getInventoryItems();
		for ( InventoryItem item : inventoryItems) {
			System.out.println("testies");
		}
		System.out.println("you have this many " + zombieDAO.fetchCart().getInventoryItems().size());
		return mv;
	}

	// @RequestMapping(path = "GetPropertyToModify.do", method =
	// RequestMethod.GET)
	// public ModelAndView queryPropertyByToModify(@RequestParam("streetNum")
	// Integer streetNum,
	// @RequestParam("nsew") String nsew, @RequestParam("streetName") String
	// streetName,
	// @RequestParam("unit") String unit, @RequestParam("city") String city,
	// @RequestParam("stateAbbr") String stateAbbr, @RequestParam("zip") String
	// zip) {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("searchProperties", zombieDAO
	// .getPropertyByAddress(new Address(streetNum, nsew, streetName, unit,
	// city, stateAbbr, zip)));
	// mv.setViewName("seeks.jsp");
	// return mv;
	// }
	//
	// @RequestMapping(path = "UpdateProperty.do", method = RequestMethod.GET)
	// public ModelAndView updateProperty(@RequestParam("choice") String choice,
	// @RequestParam("selectedPropertyKey") String selectedPropertyKey) {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("selectedPropertyKey", selectedPropertyKey);
	// mv.addObject("property",
	// zombieDAO.getPropertyByKeyNum(selectedPropertyKey));
	// // mv.addObject("sessionBedrooms",
	// // unlivableDAO.getPropertyByKeyNum(selectedPropertyKey).getBedrooms());
	// if (choice.equals("update")) {
	// mv.setViewName("update.jsp");
	// } else if (choice.equals("delete")) {
	// mv.setViewName("confirmation.jsp");
	// }
	// return mv;
	// }
	//
	// @RequestMapping(path = "DeleteProperty.do", method = RequestMethod.GET)
	// public ModelAndView deleteProperty(@RequestParam("choice") String choice,
	// @RequestParam("selectedPropertyKey") String selectedPropertyKey) {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("selectedPropertyKey", selectedPropertyKey);
	// if (choice.equals("delete")) {
	// zombieDAO.deleteProperty(selectedPropertyKey);
	// }
	// mv.setViewName("delete.jsp");
	// return mv;
	// }
	//
	// @RequestMapping(path = "ModifyProperty.do", method = RequestMethod.POST)
	// public ModelAndView modifyProperty(@ModelAttribute("property") Property
	// property) {
	// System.out.println("The bedroom current status" +
	// property.getBedrooms());
	//
	// for (Bedroom bedroom : property.getBedrooms()) {
	// System.out.println("Contents of bedroom: " + bedroom);
	// }
	// // property.setAddress(address);
	// // property.setBedrooms(bedrooms);
	// unlivableDAO.addProperty(property);
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("review.jsp");
	// mv.addObject("property", property);
	// return mv;
	// }
	//
	// @RequestMapping(path = "GetPropertyData.do", params = "keyNum", method =
	// RequestMethod.GET)
	// public ModelAndView queryPropertyByKeyNum(@RequestParam("keyNum") String
	// keyNum) {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("property", unlivableDAO.getPropertyByKeyNum(keyNum));
	// mv.setViewName("result.jsp");
	// return mv;
	// }
	//
	// @RequestMapping(path = "AddPropertyData.do", method = RequestMethod.POST)
	// public ModelAndView createNewProperty(Address address) {
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("add-details.jsp");
	// mv.addObject("address", address);
	// return mv;
	// }
	//
	// @RequestMapping(path = "AddPropertyDetails.do", method =
	// RequestMethod.POST)
	// public ModelAndView addPropertyDetails(Property property,
	// @ModelAttribute("address") Address address,
	// @RequestParam("addBedroom") String addBedroom) {
	// ModelAndView mv = new ModelAndView();
	// property.setAddress(address);
	// unlivableDAO.addProperty(property);
	// if (Boolean.parseBoolean(addBedroom)) {
	// property.populateBedrooms();
	// mv.setViewName("add-bedroom.jsp");
	// mv.addObject("sessionProperty", property);
	// } else {
	// mv.setViewName("review.jsp");
	// mv.addObject("property", property);
	// }
	// return mv;
	// }
	//
	// @RequestMapping(path = "AddPropertyBedroom.do", method =
	// RequestMethod.POST)
	// public ModelAndView addPropertyBedroom(@ModelAttribute("sessionProperty")
	// Property property,
	// ArrayList<Bedroom> bedrooms) {
	// property.setBedrooms(bedrooms);
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("review.jsp");
	// mv.addObject("property", property);
	// return mv;
	// }
	//
	// @RequestMapping(path = "CompareProperties.do", method =
	// RequestMethod.POST)
	// public ModelAndView compareProperties(List<String> keyToCompare) {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("property1",
	// unlivableDAO.getPropertyByKeyNum(keyToCompare.get(0)));
	// mv.addObject("property2",
	// unlivableDAO.getPropertyByKeyNum(keyToCompare.get(1)));
	// mv.setViewName("review.jsp");
	// return mv;
	// }
}