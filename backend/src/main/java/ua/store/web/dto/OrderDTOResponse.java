package ua.store.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOResponse {
    private int id;
    private List<OrderProductDTOResponse> products;
    private Date timeOfOrder;
    private OrderStatusDTO status;
    private UserDTOResponse orderOwner;
}
