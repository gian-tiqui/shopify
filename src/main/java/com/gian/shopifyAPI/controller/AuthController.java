package com.gian.shopifyAPI.controller;

import com.gian.shopifyAPI.dto.ReqRes;
import com.gian.shopifyAPI.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpReq) {
        return ResponseEntity.ok(authService.signUp(signUpReq));
    }

    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInReq) {
        return ResponseEntity.ok(authService.signIn(signInReq));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenReq) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenReq));
    }
}
