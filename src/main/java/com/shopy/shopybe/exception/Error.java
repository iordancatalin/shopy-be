package com.shopy.shopybe.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Error {

    private final LocalDateTime timestamp;
    private final ErrorCode errorCode;
    private final String errorMessage;
}
