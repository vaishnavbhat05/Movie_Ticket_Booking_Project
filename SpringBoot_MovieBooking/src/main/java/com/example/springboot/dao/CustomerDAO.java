package com.example.springboot.dao;

import com.example.springboot.model.Customer;
import com.example.springboot.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomerDAO {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    public Customer registerCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    public Customer loginCustomer(String phoneNumber) {
        String query = "SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber";
        TypedQuery<Customer> typedQuery = entityManager.createQuery(query, Customer.class);
        typedQuery.setParameter("phoneNumber", phoneNumber);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Customer getCustomerDetails(Long customerId) {
        return entityManager.find(Customer.class, customerId);
    }
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer findById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElse(null);
    }
}

