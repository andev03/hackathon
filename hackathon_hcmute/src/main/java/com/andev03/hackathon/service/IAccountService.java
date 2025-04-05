package com.andev03.hackathon.service;

import com.andev03.hackathon.dto.AccountDto;
import com.andev03.hackathon.dto.LoginRequest;

public interface IAccountService {

    void createAccount(AccountDto accountRequest);
    AccountDto loginAccount(LoginRequest loginRequest);
}
