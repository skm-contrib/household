package ua.store.domain.service;

import ua.store.web.dto.auth.AuthenticationRequest;
import ua.store.web.dto.auth.AuthenticationResponse;
import ua.store.web.dto.auth.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
