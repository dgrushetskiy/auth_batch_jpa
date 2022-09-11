package ru.gothmog.ws.auth.rest.facade.auth.impl;

import ru.gothmog.ws.auth.rest.dto.auth.UserDto;
import ru.gothmog.ws.auth.rest.facade.Facade;
import ru.gothmog.ws.auth.rest.facade.auth.AuthFacade;
import ru.gothmog.ws.auth.rest.payload.request.SignupRequest;
import ru.gothmog.ws.auth.rest.payload.response.JwtResponse;

import java.util.Optional;

@Facade
public class AuthFacadeImpl implements AuthFacade {
    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public JwtResponse getJwtResponse(String usernameOrEmail, String password) {
        return null;
    }

    @Override
    public Optional<UserDto> createUserRegistration(SignupRequest signUpRequest) {
        return Optional.empty();
    }
}
