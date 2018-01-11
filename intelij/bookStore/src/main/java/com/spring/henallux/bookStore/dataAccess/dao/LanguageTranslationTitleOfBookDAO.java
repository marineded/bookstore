package com.spring.henallux.bookStore.dataAccess.dao;

import java.util.ArrayList;
import java.util.Collection;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.Model.LanguageTranslationTitleOfBook;
import com.spring.henallux.bookStore.dataAccess.entity.LanguageTranslationTitleOfBookEntity;
import com.spring.henallux.bookStore.dataAccess.repository.LanguageTranslationTitleOfBookRepository;
import com.spring.henallux.bookStore.dataAccess.util.ProviderConverter;

@Service
@Transactional
public class LanguageTranslationTitleOfBookDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ProviderConverter converter = new ProviderConverter();
    @Autowired
    private LanguageTranslationTitleOfBookRepository languageTranslationTitleOfBookRepository;
    public ArrayList<LanguageTranslationTitleOfBook> getTitleOfBookByCategory (Integer idCategory)
    {
        ArrayList<LanguageTranslationTitleOfBook> languageTranslationTitleOfBooksModel = new ArrayList<LanguageTranslationTitleOfBook>();
        Collection<LanguageTranslationTitleOfBookEntity> languageTranslationTitleOfBooksEntity;


        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.getNamedQuery("findTitleOfBookByCategory").setInteger("category", idCategory);
        languageTranslationTitleOfBooksEntity = query.list();

        for(LanguageTranslationTitleOfBookEntity languageTranslationTitleOfBookEntity : languageTranslationTitleOfBooksEntity)
        {
            LanguageTranslationTitleOfBook languageTranslationTitleOfBook = new LanguageTranslationTitleOfBook();
            languageTranslationTitleOfBook = converter.languageTranslationTitleOfBookEntityToLanguageTranslationTitleOfBookModel(languageTranslationTitleOfBookEntity);
            languageTranslationTitleOfBooksModel.add(languageTranslationTitleOfBook);
        }





        session.getTransaction().commit();
        return languageTranslationTitleOfBooksModel;
    }

    public LanguageTranslationTitleOfBook getTitleOfBookByIsbn (Integer idBook)
    {

        LanguageTranslationTitleOfBookEntity languageTranslationTitleOfBookEntity = new LanguageTranslationTitleOfBookEntity();
        Collection<LanguageTranslationTitleOfBookEntity> languageTranslationTitleOfBooksEntity;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.getNamedQuery("findTitleOfBookByIsbn").setInteger("bookId", idBook);
        languageTranslationTitleOfBooksEntity = query.list();

        languageTranslationTitleOfBookEntity = languageTranslationTitleOfBooksEntity.iterator().next();

        LanguageTranslationTitleOfBook languageTranslationTitleOfBookModel = new LanguageTranslationTitleOfBook();
        languageTranslationTitleOfBookModel = converter.languageTranslationTitleOfBookEntityToLanguageTranslationTitleOfBookModel(languageTranslationTitleOfBookEntity);
        return languageTranslationTitleOfBookModel;
    }

    public LanguageTranslationTitleOfBook getTitleOfBook(Integer idTranslation)
    {
        LanguageTranslationTitleOfBookEntity languageTranslationTitleOfBookEntity = new LanguageTranslationTitleOfBookEntity();
        languageTranslationTitleOfBookEntity = languageTranslationTitleOfBookRepository.findByLanguageTranslationTitleOfBookid(idTranslation);
        LanguageTranslationTitleOfBook languageTranslationTitleOfBookModel = new LanguageTranslationTitleOfBook();
        languageTranslationTitleOfBookModel = converter.languageTranslationTitleOfBookEntityToLanguageTranslationTitleOfBookModel(languageTranslationTitleOfBookEntity);
        return languageTranslationTitleOfBookModel;

    }


    public ArrayList<LanguageTranslationTitleOfBook> getTitleOfBookByLanguage(String language)
    {
        ArrayList<LanguageTranslationTitleOfBook> languageTranslationTitleOfBooksModel = new ArrayList<LanguageTranslationTitleOfBook>();
        Collection<LanguageTranslationTitleOfBookEntity> languageTranslationTitleOfBooksEntity;


        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.getNamedQuery("findTitleOfBookByLanguage").setString("language", language);
        languageTranslationTitleOfBooksEntity = query.list();

        for(LanguageTranslationTitleOfBookEntity languageTranslationTitleOfBookEntity : languageTranslationTitleOfBooksEntity)
        {
            LanguageTranslationTitleOfBook languageTranslationTitleOfBook = new LanguageTranslationTitleOfBook();
            languageTranslationTitleOfBook = converter.languageTranslationTitleOfBookEntityToLanguageTranslationTitleOfBookModel(languageTranslationTitleOfBookEntity);
            languageTranslationTitleOfBooksModel.add(languageTranslationTitleOfBook);
        }





        session.getTransaction().commit();

        return languageTranslationTitleOfBooksModel;
    }


}
