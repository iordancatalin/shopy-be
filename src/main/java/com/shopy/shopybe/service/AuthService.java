package com.shopy.shopybe.service;

import com.shopy.shopybe.exceptions.EmailAlreadyExistsException;
import com.shopy.shopybe.model.SignUpRequest;
import com.shopy.shopybe.repository.ApplicationRoleRepository;
import com.shopy.shopybe.repository.ApplicationUserRepository;
import com.shopy.shopybe.transformer.SignUpTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ApplicationUserRepository applicationUserRepository;
    private final SignUpTransformer signUpTransformer;
    private final ApplicationRoleRepository applicationRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest signUpRequest) {
        applicationUserRepository.findByEmailAddress(signUpRequest.getEmailAddress())
                .ifPresent(user -> { throw new EmailAlreadyExistsException(); });

        final var userRole = applicationRoleRepository.findById(1).orElse(null);
        final var applicationUser = signUpTransformer.apply(signUpRequest);

        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        applicationUser.setRole(userRole);
        applicationUser.setEnabled(false);

        // TODO: sent confirmation email

        applicationUserRepository.save(applicationUser);
    }
}
