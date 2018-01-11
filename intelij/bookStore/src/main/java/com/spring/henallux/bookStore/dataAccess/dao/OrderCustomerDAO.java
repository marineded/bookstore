package com.spring.henallux.bookStore.dataAccess.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.spring.henallux.bookStore.Model.OrderCustomer;
import com.spring.henallux.bookStore.dataAccess.entity.OrderCustomerEntity;
import com.spring.henallux.bookStore.dataAccess.repository.OrderCustomerRepository;
import com.spring.henallux.bookStore.dataAccess.util.ProviderConverter;

@Service
@Transactional
public class OrderCustomerDAO {

    @Autowired
    private OrderCustomerRepository orderCustomerRepository;
    @Autowired
    private ProviderConverter converter = new ProviderConverter();

    public OrderCustomer addOrderCustomer(OrderCustomer orderCustomerModel)
    {

        OrderCustomerEntity orderCustomerEntity = converter.orderCustomerModelToOrderCustomerEntity(orderCustomerModel);
        orderCustomerEntity = orderCustomerRepository.save(orderCustomerEntity);
        orderCustomerModel = converter.orderCustomerEntityToOrderCustomerModel(orderCustomerEntity);
        return orderCustomerModel;
    }
}
