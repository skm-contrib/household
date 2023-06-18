package ua.store.domain.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.store.domain.factory.OrderFactory;
import ua.store.domain.jwt.JwtService;
import ua.store.domain.model.Order;
import ua.store.domain.model.User;
import ua.store.domain.repository.OrderProductRepository;
import ua.store.domain.repository.OrderRepository;
import ua.store.domain.repository.UserRepository;
import ua.store.domain.service.OrderService;
import ua.store.domain.service.PaymentService;
import ua.store.web.dto.OrderDTORequest;
import ua.store.web.dto.OrderDTOResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final PaymentService paymentService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final OrderProductRepository orderProductRepository;


    @Override
    public OrderDTOResponse makeOrder(OrderDTORequest order) {
        Order orderToSave = orderFactory.fromDto(order);
        paymentService.payment(orderToSave, order.getPayment());
        OrderDTOResponse orderRequest = orderFactory.toDto(orderRepository.save(orderToSave));
        orderProductRepository.saveAll(orderToSave.getProducts());
        return orderRequest;
    }

    @Override
    public List<OrderDTOResponse> findAll(HttpServletRequest request) {
        String userEmail = jwtService.extractUsername(request.getHeader("Authorization").substring(7));
        User owner = userRepository.findByEmail(userEmail).orElseThrow();
        return orderRepository.findAllByOwner(owner).stream().map(orderFactory::toDto).toList();
    }

    @Override
    public List<OrderDTOResponse> findAll() {
        return orderRepository.findAll().stream().map(orderFactory::toDto).toList();
    }

    @Override
    public String updateStatus(int order, int status) {
        orderRepository.updateStatus(order, status);
        return "Status updated";
    }
}
