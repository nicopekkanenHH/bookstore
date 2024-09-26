package com.bookstore.backend.web;

import com.bookstore.backend.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.bookstore.backend.domain.AppUser;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private final AppUserRepository repository;

    public MyUserDetailsService(AppUserRepository appUserRepository) {
        this.repository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }


        List<SimpleGrantedAuthority> authorities = appUser.getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            appUser.getUsername(),
                appUser.getPassword(),
                authorities
                );
    }
}
