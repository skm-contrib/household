package ua.store.domain.service;

import ua.store.domain.model.Order;
import ua.store.web.dto.Payment;

import java.util.Map;

public interface PaymentService {

    String payment(Order order, Payment payment) ;

}
