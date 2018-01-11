package com.spring.henallux.bookStore.dataAccess.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.Model.Customer;
import com.spring.henallux.bookStore.dataAccess.entity.CustomerEntity;
import com.spring.henallux.bookStore.dataAccess.repository.CustomerRepository;
import com.spring.henallux.bookStore.dataAccess.util.ProviderConverter;
import com.spring.henallux.bookStore.exception.CustomerAlreadyExistException;
import com.spring.henallux.bookStore.exception.CustomerNotFoundException;

@Service
@Transactional
public class CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProviderConverter converter = new ProviderConverter();

    public Customer getCustomerByEmail(String email) throws CustomerNotFoundException
    {
        try
        {
            CustomerEntity customerEntity = customerRepository.findByEmail(email);


            Customer memberModel = converter.customerEntityToCustomerModel(customerEntity);
            return memberModel;
        }

        catch (Exception e)
        {
            throw new CustomerNotFoundException(e.getMessage());
        }
    }

    public void addCustomer(Customer customerModel) throws CustomerAlreadyExistException
    {

        CustomerEntity customerEntity = converter.customerModelToCustomerEntity(customerModel);

        CustomerEntity en = new CustomerEntity();
        en = customerRepository.findByEmail(customerEntity.getEmail());
        if (en == null)
            customerEntity = customerRepository.save(customerEntity);
        else
            throw new CustomerAlreadyExistException("Customer already exist");



    }

}