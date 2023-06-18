package ua.store.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class ProductDTORequest {
    private int id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotNull(message= "Category must be not empty")
    @Range(min = 0)
    private Float price;
    @NotNull(message= "Category must be not empty")
    private Integer categoryId;
    private boolean disabled;
    @NotBlank(message = "Image cannot be blank")
    private String imagePath;
}
