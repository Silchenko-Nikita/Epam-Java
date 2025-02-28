package com.epam.rd.autocode.assessment.appliances.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "appliance", referencedColumnName = "id")
    private Appliance appliance;
    private Long number;
    private BigDecimal amount;

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
