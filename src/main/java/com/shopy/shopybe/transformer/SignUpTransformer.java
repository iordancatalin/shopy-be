package com.shopy.shopybe.transformer;

import com.shopy.shopybe.entity.ApplicationUser;
import com.shopy.shopybe.model.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SignUpTransformer implements Function<SignUpRequest, ApplicationUser> {

    @Override
    public ApplicationUser apply(SignUpRequest signUpRequest) {
        return ApplicationUser.builder()
                .username(signUpRequest.getUsername())
                .emailAddress(signUpRequest.getEmailAddress())
                .password(signUpRequest.getPassword())
                .build();
    }
}
