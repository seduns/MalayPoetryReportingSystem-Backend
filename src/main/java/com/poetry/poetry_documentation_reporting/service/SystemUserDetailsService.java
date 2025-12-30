package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.model.enumoption.USER_ROLE;
import com.poetry.poetry_documentation_reporting.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SystemUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = authRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user not found with this email");
        }

        USER_ROLE role = user.get().getRole();

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorityList);

    }
}
