package ua.store.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ua.store.domain.model.OrderStatus;
import ua.store.domain.repository.OrderStatusRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final OrderStatusRepository orderStatusRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!orderStatusRepository.existsByName("Замовлено"))
            orderStatusRepository.save(OrderStatus.builder().color("yellow").name("Замовлено").build());
    }
}