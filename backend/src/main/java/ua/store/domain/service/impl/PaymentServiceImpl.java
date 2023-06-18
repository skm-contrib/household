package ua.store.domain.service.impl;

import com.liqpay.LiqPay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.store.domain.model.Order;
import ua.store.domain.model.OrderProduct;
import ua.store.domain.service.PaymentService;
import ua.store.web.dto.Payment;
import ua.store.web.exception.PaymentRejectedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${payment.valid-card}")
    private String validCard;

    @Value("${payment.invalid-card}")
    private String invalidCard;

    @Override
    public String payment(Order order, Payment payment){
        Map<String, String> params = new HashMap<>();

        String orderAmount = amount(order.getProducts());
        params.put("amount", orderAmount);
        params.put("currency", "UAH");
        params.put("sandbox", "1");

        if (payment.getCardNumber().equals(invalidCard))
            throw new PaymentRejectedException("There are not enough funds on the balance sheet\n");

        return "Payment is successful on amount - " + orderAmount;
    }

    private String amount(List<OrderProduct> products) {
        float amount = 0;

        for (OrderProduct orderProduct : products) {
            amount += orderProduct.getProduct().getPrice() * orderProduct.getCount();
        }
        return String.valueOf(amount);
    }
}
