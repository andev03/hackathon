package com.andev03.hackathon.service;

import com.andev03.hackathon.dto.AccountDto;
import com.andev03.hackathon.dto.LoginRequest;

public interface IAccontService {

    void createAccount(AccountDto accountRequest);
    void loginAccount(LoginRequest loginRequest);
}
