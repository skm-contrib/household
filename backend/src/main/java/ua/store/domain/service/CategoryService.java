package ua.store.domain.service;

import ua.store.domain.model.Category;
import ua.store.web.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(int id);

    CategoryDTO save(CategoryDTO category);

    CategoryDTO update(CategoryDTO category, int id);

    boolean delete(int id);
}
