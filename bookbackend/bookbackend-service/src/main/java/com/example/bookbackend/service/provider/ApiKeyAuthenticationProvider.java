package com.example.bookbackend.service.provider;

import com.example.bookbackend.service.entity.ApiKeyEntity;
import com.example.bookbackend.service.model.ApiKeyAuthenticationToken;
import com.example.bookbackend.service.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    private final ApiKeyRepository repo;

    @Autowired
    public ApiKeyAuthenticationProvider(ApiKeyRepository repo) {
        this.repo = repo;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = (String) authentication.getPrincipal();

        if(ObjectUtils.isEmpty(apiKey)) {
            throw new InsufficientAuthenticationException("No API Key in request");
        }
        else {
            Optional<ApiKeyEntity> apiKeyOptional = this.repo.findByKey(apiKey);

            if(apiKeyOptional.isPresent()) {
                return new ApiKeyAuthenticationToken(apiKey, true);
            }
            throw new BadCredentialsException("API Key is invalid");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
