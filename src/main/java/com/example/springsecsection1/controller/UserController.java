package com.example.springsecsection1.controller;

import com.example.springsecsection1.controller.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustonerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerUser(@RequestBody Customer customer) {
        try {
            var hashedPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashedPwd);
            var savedCustomer = customerRepository.save(customer);
            if (customer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(savedCustomer);
            } else {
                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer =
                customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }
}
