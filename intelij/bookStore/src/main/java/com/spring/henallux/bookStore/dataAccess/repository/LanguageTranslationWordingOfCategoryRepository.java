package com.spring.henallux.bookStore.dataAccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.dataAccess.entity.LanguageTranslationWordingOfCategoryEntity;
@Repository
@Transactional
public interface LanguageTranslationWordingOfCategoryRepository extends JpaRepository<LanguageTranslationWordingOfCategoryEntity,Integer> {

}
