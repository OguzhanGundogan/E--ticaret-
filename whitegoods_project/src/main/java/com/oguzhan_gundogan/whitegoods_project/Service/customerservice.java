package com.oguzhan_gundogan.whitegoods_project.Service;

import com.oguzhan_gundogan.whitegoods_project.entity.User;
import com.oguzhan_gundogan.whitegoods_project.Repository.CustomerRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class customerservice {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRep customerRepository;

    public User addCustomer(User customer) {
        if(Objects.isNull(customer.getRoles())) {
            customer.setRoles("ROLE_USER");
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }
}
