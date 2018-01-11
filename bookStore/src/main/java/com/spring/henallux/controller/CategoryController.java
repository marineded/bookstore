package com.spring.henallux.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.henallux.Model.CommandLine;
import com.spring.henallux.Model.ConnectionForm;
import com.spring.henallux.Model.LanguageTranslationTitleOfBook;
import com.spring.henallux.Model.LanguageTranslationWordingOfCategory;
import com.spring.henallux.dataAccess.dao.BookDAO;
import com.spring.henallux.dataAccess.dao.CategoryDAO;
import com.spring.henallux.dataAccess.dao.LanguageTranslationTitleOfBookDAO;
import com.spring.henallux.dataAccess.dao.PromotionDAO;

@Controller
@RequestMapping(value="/category")
@SessionAttributes({IndexController.CURRENTUSER})
public class CategoryController {

	@Autowired
	private CategoryDAO categoryDAO = new CategoryDAO();
	
	@Autowired
	private LanguageTranslationTitleOfBookDAO languageTranslationTitleOfBookDAO = new LanguageTranslationTitleOfBookDAO();
	
	@Autowired
	private BookDAO bookDAO = new BookDAO();
	@Autowired
	private PromotionDAO promotionDAO = new PromotionDAO();
	@Autowired
	private MessageSource messageSource;
	
	//affiche liste produits catégorie
	@RequestMapping(value = "/{category}", method=RequestMethod.GET)
	public String displayProductOfCategory (@PathVariable("category") Integer category_id, Model model, Locale locale)
	{
		model.addAttribute("booksPromo", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
		model.addAttribute("promotions", promotionDAO.getCurrentPromotions(new Date()));
		ArrayList<LanguageTranslationTitleOfBook> allBooks = new ArrayList<LanguageTranslationTitleOfBook>(languageTranslationTitleOfBookDAO.getTitleOfBookByCategory(category_id));
		ArrayList<LanguageTranslationTitleOfBook> booksToDisplay = new ArrayList<LanguageTranslationTitleOfBook>();
		
		for (LanguageTranslationTitleOfBook languageTranslationTitleOfBook : allBooks)
		{
			if(languageTranslationTitleOfBook.getCurrentLanguage_id().getCurrentLanguage_id().equals(locale.toString()))
			{
				booksToDisplay.add(languageTranslationTitleOfBook);
			}
		}
		ArrayList<LanguageTranslationWordingOfCategory> allCategories = new ArrayList<LanguageTranslationWordingOfCategory>(categoryDAO.getAllCategories());
		ArrayList<LanguageTranslationWordingOfCategory> categoriesToDisplay = new ArrayList<LanguageTranslationWordingOfCategory>();
		model.addAttribute("connectionForm", new ConnectionForm());
		
		for (LanguageTranslationWordingOfCategory languageTranslationWordingOfCategory : allCategories)
		{
			if(languageTranslationWordingOfCategory.getCurrentLanguage_id().getCurrentLanguage_id().equals(locale.toString()))
			{
				categoriesToDisplay.add(languageTranslationWordingOfCategory);
			}
		}
		
		model.addAttribute("books", booksToDisplay);
		model.addAttribute("categories", categoriesToDisplay);
		
		if(!model.containsAttribute("cart"))
		{
			HashMap<String , CommandLine> commandLine = new HashMap<String, CommandLine>();
			model.addAttribute("cart", commandLine);
		}
		return "integrated:book";
	}
}
