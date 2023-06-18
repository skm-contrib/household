package ua.store.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.store.domain.model.Product;
import ua.store.domain.repository.CategoryRepository;
import ua.store.web.dto.ProductDTORequest;
import ua.store.web.dto.ProductDTOResponse;

@Service
@RequiredArgsConstructor
public class ProductFactory {

    private final CategoryRepository categoryRepository;
    private final CategoryFactory categoryFactory;
    private final ReviewFactory reviewFactory;


    public Product fromDto(ProductDTORequest productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .category(categoryRepository.findById(productDTO.getCategoryId()).orElseThrow())
                .imagePath(productDTO.getImagePath())
                .disabled(productDTO.isDisabled())
                .build();
    }

    public ProductDTOResponse toDto(Product product) {
        return ProductDTOResponse.builder()
                .id(product.getId())
                .category(categoryFactory.toDto(product.getCategory()))
                .description(product.getDescription())
                .disabled(product.isDisabled())
                .imagePath(product.getImagePath())
                .price(product.getPrice())
                .name(product.getName())
                .reviews(product.getReviews().stream().map(reviewFactory::toDto).toList())
                .build();
    }
}
