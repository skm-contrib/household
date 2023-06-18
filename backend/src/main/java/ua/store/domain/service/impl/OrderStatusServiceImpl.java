package ua.store.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.store.domain.factory.OrderStatusFactory;
import ua.store.domain.model.OrderStatus;
import ua.store.domain.repository.OrderStatusRepository;
import ua.store.domain.service.OrderStatusService;
import ua.store.web.dto.OrderStatusDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository repository;
    private final OrderStatusFactory factory;


    @Override
    public List<OrderStatusDTO> findAll() {
        return repository.findAll().stream().map(factory::toDto).toList();
    }

    @Override
    public OrderStatusDTO findById(int id) {
        return factory.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found OrderStatus with id: " + id)));
    }

    @Override
    public OrderStatusDTO save(OrderStatusDTO orderStatusDTO) {
        OrderStatus orderStatusToSave = factory.fromDto(orderStatusDTO);
        return factory.toDto(repository.save(orderStatusToSave));
    }

    @Override
    public OrderStatusDTO update(OrderStatusDTO orderStatusDTO, int id) {
        checkExists(id);

        OrderStatus orderStatusToUpdate = factory.fromDto(orderStatusDTO);
        orderStatusToUpdate.setId(id);
        return factory.toDto(repository.save(orderStatusToUpdate));
    }

    @Override
    public boolean delete(int id) {
        checkExists(id);

        repository.deleteById(id);
        return true;
    }

    private void checkExists(int id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Not found OrderStatus with id: " + id);
    }
}
