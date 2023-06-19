package ua.store.domain.service;

import jakarta.servlet.http.HttpServletRequest;
import ua.store.web.dto.OrderDTORequest;
import ua.store.web.dto.OrderDTOResponse;

import java.util.List;

public interface OrderService {

    OrderDTOResponse makeOrder(OrderDTORequest order);

    List<OrderDTOResponse> findAll(HttpServletRequest request);
    List<OrderDTOResponse> findAll();

    String updateStatus(int id, int status);
}
