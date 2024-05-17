package com.gian.shopifyAPI.service;

import com.gian.shopifyAPI.dto.ReqRes;
import com.gian.shopifyAPI.entity.OurUsers;
import com.gian.shopifyAPI.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationReq) {
        ReqRes res = new ReqRes();

        try {
            OurUsers ourUsers = new OurUsers();

            ourUsers.setEmail(registrationReq.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationReq.getPassword()));
            ourUsers.setRole(registrationReq.getRole());

            OurUsers ourUsersResult = ourUserRepo.save(ourUsers);

            if (ourUsersResult.getId() > 0) {
                res.setOurUsers(ourUsersResult);
                res.setMessage("User saved successfully");
                res.setStatusCode(200);
            }
        } catch (Exception exception) {
            res.setStatusCode(500);
            res.setError(exception.getMessage());
        }

        return res;
    }

    public ReqRes signIn(ReqRes signInRequest) {
        ReqRes res = new ReqRes();

        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
                    );

            var user = ourUserRepo.findByEmail(signInRequest.getEmail()).orElseThrow();

            System.out.println("USER: " + user);

            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            res.setStatusCode(200);
            res.setToken(jwt);
            res.setRefreshToken(refreshToken);
            res.setExpirationTime("24Hr");
            res.setMessage("Sign in successful.");
        } catch (Exception exception) {
            res.setStatusCode(500);
            res.setError(exception.getMessage());
        }

        return res;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReq) {
        ReqRes res = new ReqRes();
        String email = jwtUtils.extractUsername(refreshTokenReq.getToken());
        OurUsers users = ourUserRepo.findByEmail(email).orElseThrow();

        if (jwtUtils.isTokenValid(refreshTokenReq.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);

            res.setStatusCode(200);
            res.setToken(jwt);
            res.setRefreshToken(refreshTokenReq.getToken());
            res.setExpirationTime("24Hr");
            res.setMessage("Refresh successful.");
        }

        res.setStatusCode(500);

        return res;
    }
}
