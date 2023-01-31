package ru.oldjew.tacos.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class TacoOrder implements Serializable {

    private final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="placed_at")
    private Date placedAt;

    @Column(name ="delivery_State")
    @NotBlank(message = "Delivery state is required")
    private String deliveryState;

    @Column(name ="delivery_City")
    @NotBlank(message = "Delivery City is required")
    private String deliveryCity;

    @Column(name ="delivery_Street")
    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @Column(name ="delivery_Zip")
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @Column(name ="delivery_Name")
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @Column(name ="cc_Number")
    @CreditCardNumber(message = "Not valid credit card number")
    private String ccNumber;

    @Column(name ="cc_Expiration")
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])[2-9][0-9]$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Column(name ="cc_CVV")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addTaco(Taco taco){
        tacos.add(taco);
    }
}
