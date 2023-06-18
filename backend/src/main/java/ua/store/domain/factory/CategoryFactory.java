package ua.store.domain.factory;

import org.springframework.stereotype.Component;
import ua.store.domain.model.Category;
import ua.store.web.dto.CategoryDTO;


@Component
public class CategoryFactory {

    public Category fromDto(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
    }

    public CategoryDTO toDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
