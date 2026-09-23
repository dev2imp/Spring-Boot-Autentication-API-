package org.sollingo.dto;

import org.sollingo.dto.error.AuthError;

public class AuthResponse {
    private boolean success;
    private String email;
    private String message;
    private AuthError errorCode;   // e.g. "INVALID_CREDENTIALS", "EMAIL_ALREADY_EXISTS"
    private String token;       // JWT, null on failure

    public AuthResponse() {}

    public AuthResponse(boolean success, String email, String message, AuthError errorCode, String token) {
        this.success = success;
        this.email = email;
        this.message = message;
        this.errorCode = errorCode;
        this.token = token;
    }

    public static AuthResponse success(String email, String token) {
        return new AuthResponse(true, email, "Success", null, token);
    }
    public static AuthResponse failure(String email, String message, AuthError errorCode) {
        return new AuthResponse(false, email, message, errorCode, null);
    }
    public boolean isSuccess() { return success; }
    public String getEmail() { return email; }
    public String getMessage() { return message; }
    public AuthError getErrorCode() { return errorCode; }
    public String getToken() { return token; }

    public void setSuccess(boolean success) { this.success = success; }
    public void setEmail(String email) { this.email = email; }
    public void setMessage(String message) { this.message = message; }
    public void setErrorCode(AuthError errorCode) { this.errorCode = errorCode; }
    public void setToken(String token) { this.token = token; }
}

