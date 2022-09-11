package ru.gothmog.ws.auth.core.service;

import ru.gothmog.ws.auth.rest.dto.auth.UserDto;
import ru.gothmog.ws.auth.rest.payload.request.SignupRequest;

public interface UserSignUpService {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    UserDto createUserForRegistration(SignupRequest signupRequest);
}
