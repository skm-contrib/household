package ua.store.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.store.domain.factory.UserFactory;
import ua.store.domain.jwt.JwtService;
import ua.store.domain.model.User;
import ua.store.domain.repository.UserRepository;
import ua.store.domain.service.UserService;
import ua.store.web.dto.UserDTOResponse;
import ua.store.web.dto.auth.RegisterRequest;
import ua.store.web.dto.auth.UpdateUserDTO;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Override
    public UserDTOResponse getUser(HttpServletRequest request) {
        String userEmail = jwtService.extractUsername(request.getHeader("Authorization").substring(7));
        return userFactory.toDto(userRepository.findByEmail(userEmail).orElseThrow());
    }

    @Override
    public UserDTOResponse update(UpdateUserDTO request, int id) {
        checkExists(id);

        User userToSave = userFactory.fromDto(request);
        userToSave.setId(id);

        return userFactory.toDto(userRepository.save(userToSave));
    }

    private void checkExists(int id){
        if (!userRepository.existsById(id))
            throw new EntityNotFoundException("Not found user with id: " + id);
    }
}
