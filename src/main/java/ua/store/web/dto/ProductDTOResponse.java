package ua.store.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOResponse {
    private int id;
    private String name;
    private String description;
    private float price;
    private CategoryDTO category;
    private String imagePath;
    private boolean disabled;
    private List<ReviewDTOResponse> reviews;
}
