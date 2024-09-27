package com.bookstore.backend.web;

import com.bookstore.backend.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import com.bookstore.backend.domain.AppUser;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, appUser.getPassword(), 
        		AuthorityUtils.createAuthorityList(appUser.getRole()));
        return user;
    }   

 
}
