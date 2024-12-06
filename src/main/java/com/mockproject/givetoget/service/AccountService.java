package com.mockproject.givetoget.service;

import com.mockproject.givetoget.request.LoginRequest;
import com.mockproject.givetoget.response.AuthenticationResponse;

public interface AccountService {
    AuthenticationResponse authenticate(LoginRequest loginRequest);
}
