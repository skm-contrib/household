package ua.store.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ua.store.domain.jwt.JwtService;
import ua.store.domain.model.Order;
import ua.store.domain.model.OrderProduct;
import ua.store.domain.model.OrderStatus;
import ua.store.domain.repository.*;
import ua.store.web.dto.OrderDTORequest;
import ua.store.web.dto.OrderDTOResponse;
import ua.store.web.dto.OrderProductDTORequest;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class OrderFactory {

    private final OrderStatusFactory orderStatusFactory;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderProductFactory orderProductFactory;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final JwtService jwtService;

    public OrderDTOResponse toDto(Order order) {
        return OrderDTOResponse.builder()
                .id(order.getId())
                .timeOfOrder(order.getTimeOfOrder())
                .status(orderStatusFactory.toDto(order.getStatus()))
                .products(order.getProducts().stream().map(orderProductFactory::toDto).toList())
                .orderOwner(userFactory.toDto(order.getOwner()))
                .build();
    }

    public Order fromDto(OrderDTORequest order) {
        Order orderFromDto = Order.builder()
                .id(order.getId())
                .timeOfOrder(new Date())
                .owner(userRepository.findByEmail(jwtService.extractUsername(order.getJwt())).orElseThrow())
                .status(orderStatusRepository.findByName("Замовлено"))
                .build();
        orderFromDto.setProducts(order.getProducts().stream()
                .map(product ->
                        OrderProduct.builder()
                                .product(productRepository.findById(product.getProductId()).orElseThrow())
                                .count(product.getCount())
                                .order(orderFromDto)
                                .build()).toList());

        return orderFromDto;
    }
}
