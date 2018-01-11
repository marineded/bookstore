package com.spring.henallux.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.henallux.Model.CommandLine;
import com.spring.henallux.Model.ConnectionForm;
import com.spring.henallux.Model.Customer;
import com.spring.henallux.Model.LanguageTranslationWordingOfCategory;
import com.spring.henallux.dataAccess.dao.CategoryDAO;
import com.spring.henallux.dataAccess.dao.CustomerDAO;
import com.spring.henallux.dataAccess.dao.LanguageTranslationTitleOfBookDAO;
import com.spring.henallux.dataAccess.dao.PromotionDAO;
import com.spring.henallux.dataAccess.util.ProviderEncoder;
import com.spring.henallux.exception.CustomerNotFoundException;

@Controller
@RequestMapping(value="/index")
@SessionAttributes({IndexController.CURRENTUSER, IndexController.CART})
public class IndexController {


	protected static final String CURRENTUSER = "currentUser";

	protected static final String CART = "cart";
	@Autowired
	private CustomerDAO customerDAO = new CustomerDAO();
	@Autowired
	private CategoryDAO categoryDAO = new CategoryDAO();
	@Autowired
	private LanguageTranslationTitleOfBookDAO languageTranslationTitleOfBookDAO = new LanguageTranslationTitleOfBookDAO();
	@Autowired
	private PromotionDAO promotionDAO = new PromotionDAO();
	@Autowired
	private ProviderEncoder encoder;
	
	@Autowired
	private MessageSource messageSource;
	

		//affiche la page d'accueil
		@RequestMapping(method=RequestMethod.GET)
		public String home(Model model, Locale locale)
		{
			model.addAttribute("booksPromo", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
			model.addAttribute("promotions", promotionDAO.getCurrentPromotions(new Date()));
			
			model.addAttribute("connectionForm", new ConnectionForm());
			ArrayList<LanguageTranslationWordingOfCategory> allCategories = new ArrayList<LanguageTranslationWordingOfCategory>(categoryDAO.getAllCategories());
			ArrayList<LanguageTranslationWordingOfCategory> categoriesToDisplay = new ArrayList<LanguageTranslationWordingOfCategory>();
			
			for (LanguageTranslationWordingOfCategory languageTranslationWordingOfCategory : allCategories)
			{
				if(languageTranslationWordingOfCategory.getCurrentLanguage_id().getCurrentLanguage_id().equals(locale.toString()))
				{
					categoriesToDisplay.add(languageTranslationWordingOfCategory);
				}
			}
			model.addAttribute("categories", categoriesToDisplay);
			
			if(!model.containsAttribute("cart"))
			{
				HashMap<String , CommandLine> commandLine = new HashMap<String, CommandLine>();
				model.addAttribute(CART, commandLine);
			}
			
			return "integrated:index";
		}
		//gère le bouton login
		@RequestMapping(value="/LogIn",method=RequestMethod.POST)
		public String attemptLogIn (Model model, @ModelAttribute(value="connectionForm") ConnectionForm formConnection, Locale locale)
		{
			String email = formConnection.getEmail();
			String password = formConnection.getPassword();
			
			try 
				{
					Customer customer1 = customerDAO.getCustomerByEmail(email);
			
					
					if(customer1.getPassword().equals(encoder.cryptWithMD5(password)))
					{
						HashMap<Integer, CommandLine> cartHashMap = new HashMap<Integer, CommandLine>();
						model.addAttribute(CURRENTUSER, customer1);
						
						return "redirect:/index";  // le client est connecté
					}
					else 
					{
						throw new CustomerNotFoundException("Wrong Password"); //mdp érronnné
					}
				}
			
				catch(CustomerNotFoundException e)
				{
					model.addAttribute("errorLogin",true);
					model.addAttribute("connectionForm", new ConnectionForm());
					model.addAttribute("MessageToDisplay", messageSource.getMessage("title",null,locale));
					ArrayList<LanguageTranslationWordingOfCategory> allCategories = new ArrayList<LanguageTranslationWordingOfCategory>(categoryDAO.getAllCategories());
					ArrayList<LanguageTranslationWordingOfCategory> categoriesToDisplay = new ArrayList<LanguageTranslationWordingOfCategory>();
					
					for (LanguageTranslationWordingOfCategory languageTranslationWordingOfCategory : allCategories)
					{
						if(languageTranslationWordingOfCategory.getCurrentLanguage_id().getCurrentLanguage_id().equals(locale.toString()))
						{
							categoriesToDisplay.add(languageTranslationWordingOfCategory);
						}
					}
					model.addAttribute("categories", categoriesToDisplay);
					
					if(!model.containsAttribute("cart"))
					{
						HashMap<String , CommandLine> commandLine = new HashMap<String, CommandLine>();
						model.addAttribute(CART, commandLine);
					}
					
					return "integrated:index";
				}
		
		}
		//gère le bouton déconnexion
		@RequestMapping(value="/logOut", method=RequestMethod.GET)
		public String disconnect(Model model, SessionStatus status){
			status.setComplete();
			return "redirect:/index";
		}
}
