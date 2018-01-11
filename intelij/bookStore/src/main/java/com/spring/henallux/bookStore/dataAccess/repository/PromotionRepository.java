package com.spring.henallux.bookStore.dataAccess.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.dataAccess.entity.PromotionEntity;


@Repository
@Transactional
public interface PromotionRepository  extends JpaRepository<PromotionEntity,Integer>{

}
