package ua.store.web.restcontroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.store.domain.service.OrderStatusService;
import ua.store.web.dto.OrderStatusDTO;

import java.util.List;

@RestController
@RequestMapping("/order_statuses")
@RequiredArgsConstructor
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    @GetMapping
    public ResponseEntity<List<OrderStatusDTO>> getAllStatuses() {
        return ResponseEntity.ok(orderStatusService.findAll());
    }

    @PostMapping
    public ResponseEntity<OrderStatusDTO> addOrderStatus(@RequestBody @Valid OrderStatusDTO orderStatusDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderStatusService.save(orderStatusDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderStatusDTO> updateOrderStatus(@RequestBody @Valid OrderStatusDTO orderStatusDTO, @PathVariable int id) {
        return ResponseEntity.ok(orderStatusService.update(orderStatusDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderStatus(@PathVariable int id) {
        return ResponseEntity.ok(orderStatusService.delete(id) ? "Order Status with id: " + id + " deleted" : "Order status not deleted");
    }
}
