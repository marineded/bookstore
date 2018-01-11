package com.spring.henallux.bookStore.dataAccess.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.Model.LanguageTranslationWordingOfCategory;
import com.spring.henallux.bookStore.dataAccess.entity.LanguageTranslationWordingOfCategoryEntity;
import com.spring.henallux.bookStore.dataAccess.repository.LanguageTranslationWordingOfCategoryRepository;
import com.spring.henallux.bookStore.dataAccess.util.ProviderConverter;

@Service
@Transactional
public class CategoryDAO {

    @Autowired
    LanguageTranslationWordingOfCategoryRepository languageTranslationWordingOfCategoryRepository;

    private ProviderConverter converter = new ProviderConverter();

    public ArrayList<LanguageTranslationWordingOfCategory> getAllCategories ()
    {
        ArrayList<LanguageTranslationWordingOfCategory> categories = new ArrayList<LanguageTranslationWordingOfCategory>();
        List <LanguageTranslationWordingOfCategoryEntity> traductionCategoryEntities = languageTranslationWordingOfCategoryRepository.findAll();
        for(LanguageTranslationWordingOfCategoryEntity languageTranslationWordingOfCategoryEntity : traductionCategoryEntities){

            categories.add(converter.languageTranslationWordingOfCategoryEntiyToLanguageTranslationWordingOfCategoryModel(languageTranslationWordingOfCategoryEntity));
        }
        return categories;
    }









}
