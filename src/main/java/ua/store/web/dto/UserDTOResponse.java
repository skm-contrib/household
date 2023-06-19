package ua.store.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.store.domain.model.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOResponse {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String email;
    private Role role;
}
