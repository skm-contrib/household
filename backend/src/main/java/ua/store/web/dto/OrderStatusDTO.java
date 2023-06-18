package ua.store.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusDTO {
    private int id;
    @NotBlank(message = "Name of status cannot be blank")
    private String name;
    @NotBlank(message = "Color of status cannot be blank")
    private String color;
}
