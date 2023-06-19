package ua.store.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.store.domain.model.OrderProduct;
import ua.store.domain.repository.OrderRepository;
import ua.store.domain.repository.ProductRepository;
import ua.store.web.dto.OrderProductDTORequest;
import ua.store.web.dto.OrderProductDTOResponse;

@Component
@RequiredArgsConstructor
public class OrderProductFactory {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductFactory productFactory;

    public OrderProduct fromDto(OrderProductDTORequest productDTO) {
        return OrderProduct.builder()
                .count(productDTO.getCount())
                .product(productRepository.findById(productDTO.getProductId()).orElseThrow())
                .build();
    }

    public OrderProductDTOResponse toDto(OrderProduct orderProduct){
        return OrderProductDTOResponse.builder()
                .product(productFactory.toDto(orderProduct.getProduct()))
                .count(orderProduct.getCount())
                .build();
    }
}
