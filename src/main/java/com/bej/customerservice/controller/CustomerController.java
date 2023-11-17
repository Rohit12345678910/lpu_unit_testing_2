package com.bej.customerservice.controller;

import com.bej.customerservice.domain.Customer;
import com.bej.customerservice.service.CustomerService;
import com.bej.customerservice.exception.CustomerAlreadyExistsException;
import com.bej.customerservice.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/")
public class CustomerController {

    private ResponseEntity responseEntity;
    private CustomerService customerService;


    @Autowired
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("customer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
        try {
            customerService.saveCustomerDetail(customer);
            responseEntity = new ResponseEntity(customer , HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException e) {
            throw new CustomerAlreadyExistsException();
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @DeleteMapping("customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") int customerId) throws CustomerNotFoundException {

        try {
            customerService.deleteCustomer(customerId);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            throw new CustomerNotFoundException();
        }
        catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("customers")
    public ResponseEntity<?> getAllCustomer(){
        try{
            responseEntity = new ResponseEntity(customerService.getAllCustomerDetail(), HttpStatus.OK);
        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
    @GetMapping("customers/{city}")
    public ResponseEntity<?> getAllCustomerByCity(@PathVariable String city){

        try{
            responseEntity = new ResponseEntity(customerService.getAllCustomersByCity(city), HttpStatus.OK);

        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

}
