package com.epam.rd.autocode.assessment.appliances.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Appliance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{appliance.name.required}")
    private String name;

    @NotNull(message = "{appliance.category.required}")
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank(message = "{appliance.model.required}")
    private String model;

    @NotNull(message = "{appliance.manufacturer.required}")
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

    @NotNull(message = "{appliance.powerType.required}")
    @Enumerated(EnumType.STRING)
    private PowerType powerType;

    private String characteristic;
    private String description;

    @NotNull(message = "{appliance.power.required}")
    @Min(value = 1, message = "{appliance.power.min}")
    private Integer power;

    @NotNull(message = "{appliance.price.required}")
    @Digits(integer = 10, fraction = 2, message = "{appliance.price.format}")
    private BigDecimal price;
}