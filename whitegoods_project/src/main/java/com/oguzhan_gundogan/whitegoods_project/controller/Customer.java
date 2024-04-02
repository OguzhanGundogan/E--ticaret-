package com.oguzhan_gundogan.whitegoods_project.controller;

import com.oguzhan_gundogan.whitegoods_project.dto.Authrequest;
import com.oguzhan_gundogan.whitegoods_project.dto.Login;
import com.oguzhan_gundogan.whitegoods_project.entity.User;
import com.oguzhan_gundogan.whitegoods_project.Service.customerservice;
import com.oguzhan_gundogan.whitegoods_project.Service.jwtservice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
public class Customer {

    @Autowired
    private customerservice customerService;

    @Autowired
    private jwtservice jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addCustomer")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(customerService.addCustomer(user), HttpStatus.OK);
    }

    @PostMapping("/getToken")
    public ResponseEntity<Login> authenticateAndGetToken(@RequestBody Authrequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.generateToken(authentication), HttpStatus.OK);
        }

        throw new UsernameNotFoundException("invalid user details.");
    }

    @GetMapping("/log")
    public void logging() {
        log.info("testtttttt");
    }
}
