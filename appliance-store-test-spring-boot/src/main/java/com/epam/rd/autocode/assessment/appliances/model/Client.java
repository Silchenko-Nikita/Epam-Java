package com.epam.rd.autocode.assessment.appliances.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Client extends User {
    private String card;

    public Client() {

    }

    public Client(Long id, String name, String email, String password, String card) {
        super(id, name, email, password);
        this.card = card;
    }
}
