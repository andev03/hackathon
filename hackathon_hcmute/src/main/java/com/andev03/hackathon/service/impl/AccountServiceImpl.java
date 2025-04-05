package com.andev03.hackathon.service.impl;

import com.andev03.hackathon.dto.AccountDto;
import com.andev03.hackathon.dto.LoginRequest;
import com.andev03.hackathon.exception.AccountAlreadyExist;
import com.andev03.hackathon.pojo.Account;
import com.andev03.hackathon.repository.AccountRepository;
import com.andev03.hackathon.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    @Override
    public void createAccount(AccountDto accountRequest) {
        boolean result = true;
        Optional<Account> account = accountRepository.findByUsername(accountRequest.getUsername());
        if (account.isPresent()) {
            throw new AccountAlreadyExist("Account already exist");
        }
        Account newAccount = new Account();
        newAccount.setFullname(accountRequest.getFullname());
        newAccount.setPassword(accountRequest.getPassword());
        newAccount.setUsername(accountRequest.getUsername());
        accountRepository.save(newAccount);
    }

    @Override
    public AccountDto loginAccount(LoginRequest loginRequest) {
        Optional<Account> account = accountRepository.findByUsername(loginRequest.getUsername());
        if (account.isEmpty()) {
            throw new AccountAlreadyExist("Wrong username or password");
        }
        if (!account.get().getPassword().equals(loginRequest.getPassword())) {
            throw new AccountAlreadyExist("Wrong username or password");
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setPassword(account.get().getPassword());
        accountDto.setFullname(account.get().getFullname());
        accountDto.setUsername(account.get().getUsername());
        return accountDto;
    }
}
