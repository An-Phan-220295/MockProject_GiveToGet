package com.mockproject.givetoget.service.impl;

import com.mockproject.givetoget.entity.AccountEntity;
import com.mockproject.givetoget.repository.AccountRepository;
import com.mockproject.givetoget.request.LoginRequest;
import com.mockproject.givetoget.response.AuthenticationResponse;
import com.mockproject.givetoget.service.AccountService;
import com.mockproject.givetoget.utils.CodeMessage;
import com.mockproject.givetoget.utils.exception.BaseException;
import com.mockproject.givetoget.utils.exception.UserNotExistException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        AccountEntity account = accountRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotExistException("Account not exist"));

        boolean authenticated = account.getPassword().equals(loginRequest.getPassword());
        if (!authenticated) {
            throw new BaseException(CodeMessage.UNAUTHENTICATED);
        }
        return null;
    }
//    private String generateToken(int id, String email){
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        System.out.println(key);
//    }
}
