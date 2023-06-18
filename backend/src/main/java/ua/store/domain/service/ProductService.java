package ua.store.domain.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.store.domain.model.Product;
import ua.store.web.dto.ProductDTORequest;

import java.util.List;


@Service
public interface ProductService {

    List<Product> findAll();

    List<Product> findAll(Pageable pageable, Integer categoryId, String search);
    List<Product> findAll(Pageable pageable, String search);

    Product findById(int id);

    Product save(ProductDTORequest product);

    boolean deleteById(int id);

    Product update(ProductDTORequest product, int id);

    boolean disable(int id);

    long count();
}
