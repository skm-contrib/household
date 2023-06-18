package ua.store.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.store.domain.model.Category;
import ua.store.domain.repository.CategoryRepository;
import ua.store.domain.service.CategoryService;
import ua.store.web.dto.CategoryDTO;
import ua.store.domain.factory.CategoryFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryFactory categoryFactory;

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(categoryFactory::toDto).toList();
    }

    @Override
    public CategoryDTO findById(int id) {
        return categoryFactory.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find Category with id: " + id)));
    }
    @Override
    public CategoryDTO save(CategoryDTO category) {
        Category categoryToSave = categoryFactory.fromDto(category);
        return categoryFactory.toDto(categoryRepository.save(categoryToSave));
    }

    @Override
    public CategoryDTO update(CategoryDTO category, int id) {
        checkExists(id);
        Category categoryToUpdate = categoryFactory.fromDto(category);
        categoryToUpdate.setId(id);
        return categoryFactory.toDto(categoryRepository.save(categoryToUpdate));
    }

    @Override
    public boolean delete(int id) {
        checkExists(id);

        categoryRepository.deleteById(id);
        return true;
    }

    private void checkExists(int id) {
        if (!categoryRepository.existsById(id))
            throw new EntityNotFoundException("Not found Category with id: " + id);
    }
}
