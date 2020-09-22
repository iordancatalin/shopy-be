package com.shopy.shopybe.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String emailAddress;

    @NotBlank
    @Size(min = 6)
    private String password;
}
