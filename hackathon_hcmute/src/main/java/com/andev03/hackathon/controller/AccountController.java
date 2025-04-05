package com.andev03.hackathon.controller;

import com.andev03.hackathon.dto.AccountDto;
import com.andev03.hackathon.dto.LoginRequest;
import com.andev03.hackathon.dto.ResponseDto;
import com.andev03.hackathon.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {
    private IAccountService iAccountService;
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerAccount(@RequestBody AccountDto accountDto) {
        iAccountService.createAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("200", "Success"));
    }
    @PostMapping("/login")
    public ResponseEntity<AccountDto> loginAccount(@RequestBody LoginRequest loginRequest) {
        AccountDto accountDto = iAccountService.loginAccount(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

}
