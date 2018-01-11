package com.spring.henallux.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.henallux.Model.Book;
import com.spring.henallux.Model.CommandLine;
import com.spring.henallux.Model.ConnectionForm;
import com.spring.henallux.Model.Customer;
import com.spring.henallux.Model.InscriptionForm;
import com.spring.henallux.Model.LanguageTranslationWordingOfCategory;
import com.spring.henallux.Model.OrderCustomer;
import com.spring.henallux.Model.Promotion;
import com.spring.henallux.dataAccess.dao.CategoryDAO;
import com.spring.henallux.dataAccess.dao.CommandLineDAO;
import com.spring.henallux.dataAccess.dao.LanguageTranslationTitleOfBookDAO;
import com.spring.henallux.dataAccess.dao.OrderCustomerDAO;
import com.spring.henallux.dataAccess.dao.PromotionDAO;
import com.spring.henallux.service.OrderCustomerService;


@Controller
@RequestMapping(value="/cart")
@SessionAttributes({IndexController.CURRENTUSER, IndexController.CART})
public class CartController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private CategoryDAO categoryDAO = new CategoryDAO();
	@Autowired
	private LanguageTranslationTitleOfBookDAO languageTranslationTitleOfBookDAO = new LanguageTranslationTitleOfBookDAO();
	@Autowired
	private CommandLineDAO commandLineDAO = new CommandLineDAO();
	@Autowired
	private OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
	@Autowired
	private OrderCustomerService orderCustomerService = new OrderCustomerService();
	@Autowired
	private PromotionDAO promotionDAO  = new PromotionDAO();
	
	//afficher la page du panier
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model, Locale locale, @ModelAttribute(value="cart") HashMap<Integer, CommandLine> hashMapCart)
	{
		double totalPrice;
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions = promotionDAO.getCurrentPromotions(new Date());
		totalPrice = orderCustomerService.getTotalOrder(hashMapCart, promotions);
		model.addAttribute("totalPrice", totalPrice);
		

		model.addAttribute("booksPromo", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
		model.addAttribute("promotions", promotionDAO.getCurrentPromotions(new Date()));
		ArrayList<LanguageTranslationWordingOfCategory> allCategories = new ArrayList<LanguageTranslationWordingOfCategory>(categoryDAO.getAllCategories());
		ArrayList<LanguageTranslationWordingOfCategory> categoriesToDisplay = new ArrayList<LanguageTranslationWordingOfCategory>();
		model.addAttribute("connectionForm", new ConnectionForm());
		
		//afficher les cat�gorie
		for (LanguageTranslationWordingOfCategory languageTranslationWordingOfCategory : allCategories)
		{
			if(languageTranslationWordingOfCategory.getCurrentLanguage_id().getCurrentLanguage_id().equals(locale.toString()))
			{
				categoriesToDisplay.add(languageTranslationWordingOfCategory);
			}
		}
		System.out.println(categoriesToDisplay);
		model.addAttribute("categories", categoriesToDisplay);
		model.addAttribute("books", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
		
		//si panier existe si pas ajouter modele
		if(!model.containsAttribute("cart"))
		{
			HashMap<String , CommandLine> commandLine = new HashMap<String, CommandLine>();
			model.addAttribute("cart", commandLine);
		}
		
		return "integrated:cart"; 
	}
	// g�re le bouton delete du panier
	@RequestMapping(value = "/delete/{bookId}", method=RequestMethod.GET)
	public String displayProductOfCategory (@PathVariable("bookId") Integer bookId, Model model, @ModelAttribute(value="cart") HashMap<Integer, CommandLine> hashMapCart)
	{
		hashMapCart.remove(bookId);
		return "redirect:/cart";
	}
	
	//g�re l'envoie de la commande
	@RequestMapping(value="/send",method=RequestMethod.GET)
	public String confirmCommand (Model model, @ModelAttribute(value="cart") HashMap<Integer, CommandLine> hashMapCart, @ModelAttribute(value="currentUser") Customer customer)
	{
		OrderCustomer order = new OrderCustomer();
		order.setCustomer_id(customer);
		order.setOrderCustomerDate(new Date());
		
		order = orderCustomerDAO.addOrderCustomer(order);
		for (HashMap.Entry<Integer, CommandLine> commandLine : hashMapCart.entrySet()) {
			commandLine.getValue().setOrderCustomer(order);
			commandLineDAO.addCommandLine(commandLine.getValue());
		}
		hashMapCart.clear();
		return "redirect:/index";
	}
	//g�re la page confirmation conmmande 
	@RequestMapping(value="/confirm", method=RequestMethod.GET)
	public String confirm (Model model, Locale locale)
	{
		model.addAttribute("booksPromo", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
		model.addAttribute("promotions", promotionDAO.getCurrentPromotions(new Date()));
		ArrayList<LanguageTranslationWordingOfCategory> allCategories = new ArrayList<LanguageTranslationWordingOfCategory>(categoryDAO.getAllCategories());
		ArrayList<LanguageTranslationWordingOfCategory> categoriesToDisplay = new ArrayList<LanguageTranslationWordingOfCategory>();
		model.addAttribute("connectionForm", new ConnectionForm());
		model.addAttribute("MessageToDisplay", messageSource.getMessage("titleCart",null,locale));
		
		//afficher les cat�gories
		for (LanguageTranslationWordingOfCategory languageTranslationWordingOfCategory : allCategories)
		{
			if(languageTranslationWordingOfCategory.getCurrentLanguage_id().getCurrentLanguage_id().equals(locale.toString()))
			{
				categoriesToDisplay.add(languageTranslationWordingOfCategory);
			}
		}
		model.addAttribute("categories", categoriesToDisplay);
		model.addAttribute("books", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
		
		if(!model.containsAttribute("cart"))
		{
			HashMap<String , CommandLine> commandLine = new HashMap<String, CommandLine>();
			model.addAttribute("cart", commandLine);
		}
		
		return "integrated:confirmCommand";
	}
	
}
