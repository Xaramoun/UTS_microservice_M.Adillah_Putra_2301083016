package com.uts.microservice.customer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.microservice.customer_service.model.Customer;
import com.uts.microservice.customer_service.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Ambil semua customer
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Ambil customer berdasarkan ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Membuat customer baru
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update customer berdasarkan ID
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer tidak ditemukan dengan id " + id));

        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        customer.setAddress(customerDetails.getAddress()); 
        customer.setDateOfBirth(customerDetails.getDateOfBirth()); 

        return customerRepository.save(customer);
    }

    // Menghapus customer berdasarkan ID
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer tidak ditemukan dengan id " + id));
        customerRepository.delete(customer);
    }
}
