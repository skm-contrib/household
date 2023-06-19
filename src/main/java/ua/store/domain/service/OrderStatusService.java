package ua.store.domain.service;

import ua.store.web.dto.OrderStatusDTO;

import java.util.List;

public interface OrderStatusService {

    List<OrderStatusDTO> findAll();

    OrderStatusDTO findById(int id);

    OrderStatusDTO save(OrderStatusDTO orderStatusDTO);

    OrderStatusDTO update(OrderStatusDTO orderStatusDTO, int id);

    boolean delete(int id);
}
