package ua.store.web.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private int id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    private String surname;
    @NotBlank(message = "Patronymic cannot be blank")
    private String patronymic;
    @NotBlank(message = "Phone cannot be blank")
    private String phone;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
