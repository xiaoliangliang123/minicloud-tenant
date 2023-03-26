package com.minicloud.busi.login.controller;

import com.minicloud.busi.login.service.TenantLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/tenant")
public class TenantLoginController {


    @Autowired
    private TenantLoginService tenantLoginService;

    @PostMapping("/login")
    public ResponseEntity tanentLogin(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        return ResponseEntity.of(Optional.of(tenantLoginService.login(username, password)));
    }
}
