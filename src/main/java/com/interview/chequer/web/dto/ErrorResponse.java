package com.interview.chequer.web.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;

    public ErrorResponse(RuntimeException e) {
        this.message = e.getMessage();
    }
}
