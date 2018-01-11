package com.spring.henallux.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.henallux.Model.CommandLine;
import com.spring.henallux.Model.ConnectionForm;
import com.spring.henallux.Model.Customer;
import com.spring.henallux.Model.InscriptionForm;
import com.spring.henallux.Model.LanguageTranslationWordingOfCategory;
import com.spring.henallux.dataAccess.dao.CategoryDAO;
import com.spring.henallux.dataAccess.dao.CustomerDAO;
import com.spring.henallux.dataAccess.dao.LanguageTranslationTitleOfBookDAO;
import com.spring.henallux.dataAccess.dao.PromotionDAO;
import com.spring.henallux.dataAccess.util.ProviderEncoder;
import com.spring.henallux.exception.CustomerAlreadyExistException;

@Controller
@RequestMapping(value="/inscription")
@SessionAttributes({IndexController.CURRENTUSER})
public class InscriptionController {

	@Autowired
	private CustomerDAO customerDAO = new CustomerDAO();
	@Autowired
	private CategoryDAO categoryDAO = new CategoryDAO();
	@Autowired
	private LanguageTranslationTitleOfBookDAO languageTranslationTitleOfBookDAO = new LanguageTranslationTitleOfBookDAO();
	@Autowired
	private PromotionDAO promotionDAO = new PromotionDAO();
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ProviderEncoder encoder;
	//affiche la pg d'inscription
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model, Locale locale)
	{
		model.addAttribute("booksPromo", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
		model.addAttribute("promotions", promotionDAO.getCurrentPromotions(new Date()));
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
		model.addAttribute("connectionForm", new ConnectionForm());
		model.addAttribute("inscriptionForm", new InscriptionForm());
		model.addAttribute("MessageToDisplay", messageSource.getMessage("titleInscription",null,locale));
		return "integrated:inscription";
		
		
	}
	//envoie le formulaire d'inscription
	@RequestMapping(value="/send",method=RequestMethod.POST)
	public String getSignUpFormData (Model model,@Valid @ModelAttribute(value="inscriptionForm") InscriptionForm formInscription, final BindingResult errors, Locale locale)
	{
		boolean confirmPasswordError = false;
		if(!formInscription.getConfirmPassword().equals(formInscription.getPassword()))
			confirmPasswordError = true;
		if (errors.hasErrors() || confirmPasswordError)
		{
			if(errors.hasFieldErrors("email"))
				model.addAttribute("errorEmail",true);
			if(errors.hasFieldErrors("name"))
				model.addAttribute("errorName", true);
			if(errors.hasFieldErrors("firstName"))
				model.addAttribute("errorFirstName",true);
			if(errors.hasFieldErrors("phoneNumber"))
				model.addAttribute("errorPhoneNumber",true);
			if(errors.hasFieldErrors("birthDate"))
				model.addAttribute("errorBirthDate",true);
			if(errors.hasFieldErrors("street"))
				model.addAttribute("errorStreet",true);
			if(errors.hasFieldErrors("streetNumber"))
				model.addAttribute("errorStreetNumber",true);
			if(errors.hasFieldErrors("postalCode"))
				model.addAttribute("errorPostalCode",true);
			if(errors.hasFieldErrors("city"))
				model.addAttribute("errorCity",true);
			if(errors.hasFieldErrors("country"))
				model.addAttribute("errorCountry",true);
			if(errors.hasFieldErrors("password"))
				model.addAttribute("errorPassword",true);
			if(errors.hasFieldErrors("confirmPassword") || confirmPasswordError)
				model.addAttribute("errorConfirmPassword",true);
			
			
			model.addAttribute("connectionForm", new ConnectionForm());
			model.addAttribute("inscriptionForm", formInscription);
			model.addAttribute("MessageToDisplay", messageSource.getMessage("titleInscription",null,locale));
			model.addAttribute("booksPromo", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
			model.addAttribute("promotions", promotionDAO.getCurrentPromotions(new Date()));
			
			if(!model.containsAttribute("cart"))
			{
				HashMap<String , CommandLine> commandLine = new HashMap<String, CommandLine>();
				model.addAttribute("cart", commandLine);
			}
			
			return "integrated:inscription";
			
		}
		else
		{
		try
		{
			
			Customer customerToAdd = new Customer();
			customerToAdd.setBirthDate(formInscription.getBirthDate());
			customerToAdd.setCity(formInscription.getCity());
			customerToAdd.setCountry(formInscription.getCountry());
			customerToAdd.setEmail(formInscription.getEmail());
			customerToAdd.setFirstName(formInscription.getFirstName());
			customerToAdd.setName(formInscription.getName());
			customerToAdd.setPassword(encoder.cryptWithMD5(formInscription.getPassword()));
			customerToAdd.setPhoneNumber(formInscription.getPhoneNumber());
			customerToAdd.setPostalCode(formInscription.getPostalCode());
			customerToAdd.setStreet(formInscription.getStreet());
			customerToAdd.setStreetNumber(formInscription.getStreetNumber());
			
			customerDAO.addCustomer(customerToAdd);
			
			return "redirect:/index";
		}
		catch (CustomerAlreadyExistException e)
		{
			model.addAttribute("userAlreadyExist",true);
			model.addAttribute("connectionForm", new ConnectionForm());
			model.addAttribute("inscriptionForm", formInscription);
			model.addAttribute("MessageToDisplay", messageSource.getMessage("titleInscription",null,locale));
			model.addAttribute("booksPromo", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
			model.addAttribute("promotions", promotionDAO.getCurrentPromotions(new Date()));
			
			if(!model.containsAttribute("cart"))
			{
				HashMap<String , CommandLine> commandLine = new HashMap<String, CommandLine>();
				model.addAttribute("cart", commandLine);
			}
			return "integrated:inscription";
		}
		}
		
	}
}
