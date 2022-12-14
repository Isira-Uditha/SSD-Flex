package com.example.Flexserver.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Flexserver.repository.mapper.UserMapper;
import com.example.Flexserver.utils.GenerateKeys;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final GenerateKeys generateKeys ;

    @Value("${APPID}")
    private String appId = "123";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, GenerateKeys generateKeys, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.authenticationManager = authenticationManager;
        this.generateKeys = generateKeys;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username").replace(' ', '+');
        String password = request.getParameter("password").replace(' ', '+');

        //Decrypt the username and password
        String dyUsername = "";
        String dyPassword = "";
        try {
            dyUsername = generateKeys.decrypt(username);
            dyPassword =  generateKeys.decrypt(password);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        log.info("Username is: {}", dyUsername);log.info("Password is: {}", dyPassword);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dyUsername,dyPassword);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Map<String,String> token = new HashMap<>();
        token.put("access_token",access_token);
        token.put("refresh_token",refresh_token);

        com.example.Flexserver.domain.model.User newUser = this.getUserByUsername(user.getUsername());

        Map<String, String> object = new HashMap<>();

        object.put("id", String.valueOf(newUser.getId()));
        object.put("role", newUser.getRole());
        object.put("username", user.getUsername());
        object.put("access_token", access_token);
        object.put("refresh_token", refresh_token);
        object.put("status","200");

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,Accept, x-client-key, x-client-token, x-client-secret, Authorization");
        new ObjectMapper().writeValue(response.getOutputStream(),object);

    }

    private com.example.Flexserver.domain.model.User getUserByUsername(String username){

        String query = "SELECT * FROM user WHERE  username =:username";
        return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("username", username), new UserMapper());
    }
}
