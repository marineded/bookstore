package com.spring.henallux.bookStore.controller;

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

import com.spring.henallux.bookStore.Model.AddToCartForm;
import com.spring.henallux.bookStore.Model.Book;
import com.spring.henallux.bookStore.Model.CommandLine;
import com.spring.henallux.bookStore.Model.ConnectionForm;
import com.spring.henallux.bookStore.Model.InscriptionForm;
import com.spring.henallux.bookStore.Model.LanguageTranslationTitleOfBook;
import com.spring.henallux.bookStore.Model.LanguageTranslationWordingOfCategory;
import com.spring.henallux.bookStore.Model.Promotion;
import com.spring.henallux.bookStore.dataAccess.dao.CategoryDAO;
import com.spring.henallux.bookStore.dataAccess.dao.LanguageTranslationTitleOfBookDAO;
import com.spring.henallux.bookStore.dataAccess.dao.PromotionDAO;

@Controller
@RequestMapping(value="/bookDetails")
@SessionAttributes({IndexController.CURRENTUSER, IndexController.CART})
public class BookDetailsController {

    @Autowired
    private LanguageTranslationTitleOfBookDAO languageTranslationTitleOfBookDAO = new LanguageTranslationTitleOfBookDAO();

    @Autowired
    private CategoryDAO categoryDAO = new CategoryDAO();
    @Autowired
    private PromotionDAO promotionDAO = new PromotionDAO();

    @Autowired
    private MessageSource messageSource;

    //affiche livre et détails
    @RequestMapping(value = "/{book}", method=RequestMethod.GET)
    public String displayBookDetails (@PathVariable("book") Integer book_id, Model model, Locale locale)
    {
        ArrayList<Promotion> promotions = new ArrayList<Promotion>();
        Promotion promotion = new Promotion();
        LanguageTranslationTitleOfBook book = new LanguageTranslationTitleOfBook();
        book = languageTranslationTitleOfBookDAO.getTitleOfBookByIsbn(book_id);
        promotions = promotionDAO.getCurrentPromotions(new Date());
        promotion = null;
        model.addAttribute("booksPromo", languageTranslationTitleOfBookDAO.getTitleOfBookByLanguage(locale.toString()));
        model.addAttribute("promotions", promotionDAO.getCurrentPromotions(new Date()));
        for(Promotion promo : promotions)
        {
            if ((int)promo.getBook_id().getIsbn() == book.getBook_id().getIsbn()){

                promotion = promo;
            }
        }
        model.addAttribute("promotion", promotion);
        model.addAttribute("connectionForm", new ConnectionForm());
        model.addAttribute("addToCartForm", new AddToCartForm());
        model.addAttribute("book", book);

        //afficher les catégories
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
            model.addAttribute("cart", commandLine);
        }
        return "integrated:bookDetails";
    }

    //formulaire d'ajout au panier
    @RequestMapping(value="/send",method=RequestMethod.POST)
    public String getAddCartFormData (Model model, @ModelAttribute(value="addToCartForm") AddToCartForm form, @ModelAttribute(value="cart") HashMap<Integer, CommandLine> hashMapCommandLine)
    {

        Integer quantity;
        CommandLine lineToAdd = new CommandLine();
        if(hashMapCommandLine.containsKey(form.getIsbn()))
        {
            quantity = hashMapCommandLine.get(form.getIsbn()).getQuantity() + form.getNumberOfBook();
            lineToAdd.setBook(hashMapCommandLine.get(form.getIsbn()).getBook());

        }
        else
        {
            quantity = form.getNumberOfBook();
            lineToAdd.setBook(languageTranslationTitleOfBookDAO.getTitleOfBookByIsbn(form.getIsbn()).getBook_id());

        }


        lineToAdd.setQuantity(quantity);
        hashMapCommandLine.put(form.getIsbn(), lineToAdd);
        model.addAttribute("cart", hashMapCommandLine);


        return "redirect:/bookDetails/"+form.getIsbn();
    }

}
