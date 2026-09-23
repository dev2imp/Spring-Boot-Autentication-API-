package org.sollingo.services;

import org.sollingo.dto.AuthResponse;
import org.sollingo.dto.error.AuthError;
import org.sollingo.entity.UserEntity;
import org.sollingo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public AuthResponse login(String email, String password) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return AuthResponse.failure(email, "Invalid email or password", AuthError.INVALID_CREDENTIALS);
        }
        if(! passwordEncoder.matches(password,user.get().getPasswordHash())){
            return AuthResponse.failure(email, "Invalid email or password", AuthError.INVALID_CREDENTIALS);
        }
        String token = "temp-token";
        return AuthResponse.success(email, token);
    }
    public AuthResponse register(String email, String password) {
        if(userRepository.findByEmail(email).isPresent()){
            return AuthResponse.failure(email, "There is a user with this email, try another or log in", AuthError.EMAIL_ALREADY_EXISTS);

        }
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        userRepository.save(user);

        String token = "temp-token";
        return AuthResponse.success(email, token);
    }

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }
}
