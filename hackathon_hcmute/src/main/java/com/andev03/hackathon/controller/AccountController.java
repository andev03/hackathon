package com.andev03.hackathon.controller;

import com.andev03.hackathon.dto.AccountDto;
import com.andev03.hackathon.dto.LoginRequest;
import com.andev03.hackathon.dto.ResponseDto;
import com.andev03.hackathon.service.IAccontService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {
    private IAccontService iAccontService;
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerAccount(@RequestBody AccountDto accountDto) {
        iAccontService.createAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Success"));
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginAccount(@RequestBody LoginRequest loginRequest) {
        iAccontService.loginAccount(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Success"));
    }

}
