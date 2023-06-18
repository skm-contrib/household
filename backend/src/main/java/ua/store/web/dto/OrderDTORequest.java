package ua.store.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTORequest {
    private int id;
    @NotEmpty(message = "Order products cannot be empty")
    private List<OrderProductDTORequest> products;
    @Valid
    private Payment payment;
    @NotEmpty(message = "Jwt cannot be empty")
    private String jwt;
}
