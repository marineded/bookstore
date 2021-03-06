package com.spring.henallux.bookStore.dataAccess.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.dataAccess.entity.OrderCustomerEntity;


@Repository
@Transactional
public interface OrderCustomerRepository extends JpaRepository<OrderCustomerEntity,Integer>{

}
