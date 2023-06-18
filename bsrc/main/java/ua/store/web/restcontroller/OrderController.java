package ua.store.web.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.store.domain.service.OrderService;
import ua.store.web.dto.OrderDTORequest;
import ua.store.web.dto.OrderDTOResponse;
import ua.store.web.dto.Payment;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTOResponse> order(@RequestBody @Valid OrderDTORequest order) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.makeOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTOResponse>> getAllUserOrders(HttpServletRequest request) {
        return ResponseEntity.ok(orderService.findAll(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTOResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PutMapping("/{id}/status={status}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable int id, @PathVariable int status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }


}
