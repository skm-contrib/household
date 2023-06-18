package ua.store.domain.service;

import jakarta.servlet.http.HttpServletRequest;
import ua.store.web.dto.UserDTOResponse;
import ua.store.web.dto.auth.RegisterRequest;
import ua.store.web.dto.auth.UpdateUserDTO;


public interface UserService {

    UserDTOResponse getUser(HttpServletRequest request);

    UserDTOResponse update(UpdateUserDTO request, int id);
}
