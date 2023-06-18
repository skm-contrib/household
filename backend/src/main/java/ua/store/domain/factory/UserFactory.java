package ua.store.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.store.domain.model.User;
import ua.store.domain.repository.UserRepository;
import ua.store.domain.service.UserService;
import ua.store.web.dto.auth.RegisterRequest;
import ua.store.web.dto.UserDTOResponse;
import ua.store.web.dto.auth.UpdateUserDTO;

@Component
@RequiredArgsConstructor
public class UserFactory {
    private final UserRepository userRepository;

    public UserDTOResponse toDto(User user) {
        return UserDTOResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User fromDto(RegisterRequest user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .phone(user.getPhone())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public User fromDto(UpdateUserDTO user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .phone(user.getPhone())
                .email(user.getEmail())
                .password(user.getPassword() == null ? userRepository.findById(user.getId()).get().getPassword() : user.getPassword())
                .role(userRepository.findById(user.getId()).get().getRole())
                .build();
    }
}
