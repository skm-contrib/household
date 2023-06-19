package ua.store.domain.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.store.domain.factory.ProductFactory;
import ua.store.domain.model.Product;
import ua.store.domain.repository.ProductRepository;
import ua.store.domain.service.ImageService;
import ua.store.domain.service.ProductService;
import ua.store.web.dto.ProductDTORequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductFactory factory;
    private final ImageService imageService;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Product> findAll(Pageable pageable, Integer categoryId, String search) {
        return repository.findAll(pageable, search, categoryId).stream().toList();
    }

    @Override
    public List<Product> findAll(Pageable pageable, String search) {
        return null;//repository.findAll(pageable, search, 0).stream().toList();
    }


    @Override
    public Product findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cant find Product with id: " + id));
    }

    @Override
    public Product save(ProductDTORequest product) {
        Product productToSave = factory.fromDto(product);

        return repository.save(productToSave);
    }

    @Override
    public boolean deleteById(int id) {
        checkExists(id);

        repository.deleteById(id);
        return true;
    }

    @Override
    public Product update(ProductDTORequest product, int id) {
        checkExists(id);
        Product productToUpdate = factory.fromDto(product);
        productToUpdate.setId(id);

        return repository.save(productToUpdate);
    }

    @Override
    public boolean disable(int id) {
        checkExists(id);

        Product product = repository.findById(id).orElseThrow();
        product.setDisabled(true);
        repository.save(product);
        return true;
    }

    @Override
    public long count() {
        return repository.count();
    }

    private void checkExists(int id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Cant find Product with id: " + id);
    }
}
