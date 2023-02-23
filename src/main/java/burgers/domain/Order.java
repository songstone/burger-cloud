package burgers.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private Long id;
    private LocalDateTime placedAt;

    @NotBlank(message = "Name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card Number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
        message = "Must be formatted MM/yy")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CCV")
    private String ccCVV;

    private List<Burger> burgers = new ArrayList<>();

    public void addDesign(Burger design) {
        this.burgers.add(design);
    }
}