package com.bookstore.backend.web;

import com.bookstore.backend.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.bookstore.backend.domain.AppUser;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository repository;


   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appuser = repository.findByUsername(username);
        if (appuser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(appuser.getUsername(), appuser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + appuser.getRole())));
    }

 
}
