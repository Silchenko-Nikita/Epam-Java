package com.epam.rd.autocode.assessment.appliances.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CombinedUserDetailsService implements UserDetailsService {

    private final ClientDetailsService clientDetailsService;
    private final EmployeeDetailsService employeeDetailsService;

    public CombinedUserDetailsService(ClientDetailsService clientDetailsService, EmployeeDetailsService employeeDetailsService) {
        this.clientDetailsService = clientDetailsService;
        this.employeeDetailsService = employeeDetailsService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return employeeDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return clientDetailsService.loadUserByUsername(username);
        }
    }
}
