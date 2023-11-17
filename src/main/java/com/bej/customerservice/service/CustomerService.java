package com.bej.customerservice.service;

import com.bej.customerservice.domain.Customer;
import com.bej.customerservice.exception.CustomerAlreadyExistsException;
import com.bej.customerservice.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomerDetail(Customer customer) throws CustomerAlreadyExistsException;
    boolean deleteCustomer(int id) throws CustomerNotFoundException;
    List<Customer> getAllCustomerDetail() throws Exception;
    List<Customer> getAllCustomersByCity(String city) throws CustomerNotFoundException;
}
