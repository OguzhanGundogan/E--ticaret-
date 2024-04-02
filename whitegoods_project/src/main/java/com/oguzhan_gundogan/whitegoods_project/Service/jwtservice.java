package com.oguzhan_gundogan.whitegoods_project.Service;

import  com.oguzhan_gundogan.whitegoods_project.dto.Login;
import  com.oguzhan_gundogan.whitegoods_project.entity.User;
import  com.oguzhan_gundogan.whitegoods_project.Repository.CustomerRep;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;

import javax.swing.text.html.Option;

@Service
@RequiredArgsConstructor
public class jwtservice {

    private final CustomerRep customerRepository;

    public static final String SECRET = "404D635166546A576E5A7234753778214125442A472D4B6150645267556B5870";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Login generateToken(Authentication authentication) {

        Login loginDto = new Login();
        Optional<User> customerOptional = customerRepository.findByFirstName(authentication.getName());

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", authentication.getAuthorities());
        claims.put("name", authentication.getName());
        if(customerOptional.isPresent()) {
            loginDto.setCustomerId(customerOptional.get().getId());
        }
        loginDto.setToken(createToken(claims, authentication.getName()));
        return loginDto;
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}