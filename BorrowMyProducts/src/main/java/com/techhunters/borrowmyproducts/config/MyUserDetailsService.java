package com.techhunters.borrowmyproducts.config;

import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Username:" + username);
        UserDTO theUser = userService.findByEmail(username);

        log.info("theUser" + theUser);

        if (theUser == null)
            throw new UsernameNotFoundException("user : 404 ");

        return new UserPrincipal(theUser);
    }
}