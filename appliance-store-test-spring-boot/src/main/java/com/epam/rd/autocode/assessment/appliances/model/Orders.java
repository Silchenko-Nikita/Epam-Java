package com.epam.rd.autocode.assessment.appliances.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;

    @OneToMany
    @JoinColumn(name = "orders", referencedColumnName = "id")
    private Set<OrderRow> orderRowSet;

    private Boolean approved;

    public int getAmount() {
        return orderRowSet.size();
    }

    public Set<OrderRow> getOrderRowSet() {
        return orderRowSet;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
