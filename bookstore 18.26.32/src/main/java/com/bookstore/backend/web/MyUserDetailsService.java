package backend.com.bookstore.backend.web;

import backend.com.bookstore.backend.domain.AppUser;
import backend.com.bookstore.backend.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    @Autowired
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
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            appUser.getUsername(),
                appUser.getPassword(),
                authorities
                );
    }
}
