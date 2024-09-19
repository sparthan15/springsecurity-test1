package com.example.springsecsection1.config;

import com.example.springsecsection1.controller.CustonerRepository;
import com.example.springsecsection1.controller.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EazyBankUserDetailService implements UserDetailsService {
    private final CustonerRepository custonerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer =
                custonerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(
                        "User details not found for the user " + username));
        return User.withUsername(customer.getEmail())
                .authorities(customer.getRole())
                .password(customer.getPwd())
                .build();
    }
}
