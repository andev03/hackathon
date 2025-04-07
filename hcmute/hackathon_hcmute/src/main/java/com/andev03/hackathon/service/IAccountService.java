package com.andev03.hackathon.service;

import com.andev03.hackathon.dto.AccountDto;
import com.andev03.hackathon.dto.LoginRequest;
import com.andev03.hackathon.pojo.Account;

public interface IAccountService {

    void createAccount(AccountDto accountRequest);
    AccountDto loginAccount(LoginRequest loginRequest);
    Account findAccountByUsername(String username);
}
