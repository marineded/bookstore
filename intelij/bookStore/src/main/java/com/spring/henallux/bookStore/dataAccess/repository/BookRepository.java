package com.spring.henallux.bookStore.dataAccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.dataAccess.entity.BookEntity;
@Repository
@Transactional
public interface BookRepository extends JpaRepository<BookEntity,Integer>{

}
