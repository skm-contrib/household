package ua.store.domain.factory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.store.domain.model.OrderStatus;
import ua.store.web.dto.OrderStatusDTO;

@Component
public class OrderStatusFactory {

    public OrderStatus fromDto(OrderStatusDTO order) {
        return OrderStatus.builder()
                .id(order.getId())
                .name(order.getName())
                .color(order.getColor())
                .build();
    }

    public OrderStatusDTO toDto(OrderStatus order) {
        return OrderStatusDTO.builder()
                .id(order.getId())
                .name(order.getName())
                .color(order.getColor())
                .build();
    }

}
