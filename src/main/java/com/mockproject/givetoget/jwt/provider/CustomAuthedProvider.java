package com.mockproject.givetoget.jwt.provider;

import com.mockproject.givetoget.entity.AccountEntity;
import com.mockproject.givetoget.repository.AccountRepository;
import com.mockproject.givetoget.utils.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthedProvider implements AuthenticationProvider {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        AccountEntity account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotExistException("Account not exist"));

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        List<GrantedAuthority> role = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(account.getRole().getName());
        role.add(grantedAuthority);

        return new UsernamePasswordAuthenticationToken(email, null, role);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
