package ru.gothmog.ws.auth.rest.facade.auth;

import ru.gothmog.ws.auth.rest.dto.auth.UserDto;
import ru.gothmog.ws.auth.rest.payload.request.SignupRequest;
import ru.gothmog.ws.auth.rest.payload.response.JwtResponse;

import java.util.Optional;

public interface AuthFacade {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    JwtResponse getJwtResponse(String usernameOrEmail, String password);

    Optional<UserDto> createUserRegistration(SignupRequest signUpRequest);
}
