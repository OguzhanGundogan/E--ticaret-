package com.oguzhan_gundogan.whitegoods_project.Service;

import com.oguzhan_gundogan.whitegoods_project.config.UserInfo;
import com.oguzhan_gundogan.whitegoods_project.entity.User;
import com.oguzhan_gundogan.whitegoods_project.Repository.CustomerRep;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class userinfoservice implements UserDetailsService {

    @Autowired
    private CustomerRep repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = repository.findByFirstName(username);

        return userInfo.map(UserInfo::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}