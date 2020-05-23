package com.svceindore.minor.service;

import com.svceindore.minor.model.User;
import com.svceindore.minor.repositories.UserRepository;
import com.svceindore.minor.utils.Constants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MongoUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MongoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        userRepository.insert(new User(
//                "admin@gmail.com",
//                new BCryptPasswordEncoder().encode("12345"),
//                true,
//                Constants.ROLE_ADMIN
//        ));
//        userRepository.insert(new User(
//                "vijay@gmail.com",
//                new BCryptPasswordEncoder().encode("12345"),
//                true,
//                Constants.ROLE_USER
//        ));

        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (user.getAuthority().equalsIgnoreCase(Constants.ROLE_ADMIN)){
            System.out.println(email+ " logged as "+Constants.ROLE_ADMIN);
            authorities.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));
        }else {
            System.out.println(email+ " logged as "+Constants.ROLE_USER);
            authorities.add(new SimpleGrantedAuthority(Constants.ROLE_USER));
        }

        return new UserDetails(){

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return user.isEnable();
            }
        };
    }


}