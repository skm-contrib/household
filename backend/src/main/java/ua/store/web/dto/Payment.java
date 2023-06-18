package ua.store.web.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class Payment {

    @NotBlank(message = "card number cannot be empty")
    @Pattern(regexp="^\\d{16}$", message = "Card number is invalid")
    private String cardNumber;
    @NotBlank(message = "cvv cannot be empty")
    @Pattern(regexp="^\\d{3}$", message = "Cvv is invalid")
    private String cvv;
    @NotNull(message = "Expiration date cannot be empty")
    @Future(message = "Card has expired")
    private Date expirationDate;


}
