package ru.gtatarnikov.tacocloud.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "taco_order")
public class Order implements Serializable {
    private static final Long SERIAL_VERSION_UID = 1L;

    @Id
    @SequenceGenerator(name = "taco_order_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taco_order_id_generator")
    private Long id;

    @Column(name = "placed_at")
    private Date placedAt;

    @Column(name = "delivery_name")
    @NotBlank(message = "Delivery Name is required")
    private String deliveryName;

    @Column(name = "delivery_street")
    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @Column(name = "delivery_city")
    @NotBlank(message = "City is required")
    private String deliveryCity;

    @Column(name = "delivery_state")
    @NotBlank(message = "State is required")
    private String deliveryState;

    @Column(name = "delivery_zip")
    @NotBlank(message = "Zip Code is required")
    private String deliveryZip;

    @Column(name = "cc_number")
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Column(name = "cc_expiration")
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Column(name = "cc_cvv")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> taco = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addTaco(Taco taco) {
        this.taco.add(taco);
    }
}