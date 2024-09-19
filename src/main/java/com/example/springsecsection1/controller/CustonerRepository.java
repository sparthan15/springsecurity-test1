package com.example.springsecsection1.controller;

import com.example.springsecsection1.controller.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustonerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
