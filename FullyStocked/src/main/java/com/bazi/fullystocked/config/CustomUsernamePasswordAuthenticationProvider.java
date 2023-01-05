package com.bazi.fullystocked.config;

import com.bazi.fullystocked.Models.Exceptions.InvalidUserCredentialsException;
import com.bazi.fullystocked.Models.Exceptions.UserNotFoundException;
import com.bazi.fullystocked.Services.AuthService;
import com.bazi.fullystocked.Services.WorkersService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordAuthenticationProvider(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        if("".equals(username) || "".equals(password))
        {
            throw new UserNotFoundException(username);
        }
        UserDetails userDetails=this.authService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password, userDetails.getPassword()))
        {
            throw new InvalidUserCredentialsException();
        }
        return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
