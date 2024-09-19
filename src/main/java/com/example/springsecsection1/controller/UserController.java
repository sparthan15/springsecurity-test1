package com.example.springsecsection1.controller;

import com.example.springsecsection1.controller.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final CustonerRepository custonerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Customer> registerUser(@RequestBody Customer customer) {
        try {
            var hashedPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashedPwd);
            var savedCustomer = custonerRepository.save(customer);
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
}
