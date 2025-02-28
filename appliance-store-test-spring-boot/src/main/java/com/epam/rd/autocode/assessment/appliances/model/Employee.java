package com.epam.rd.autocode.assessment.appliances.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Employee extends User {
    private String department;

    public Employee() {

    }

    public Employee(Long id, String name, String email, String password, String department) {
        super(id, name, email, password);
        this.department = department;
    }
}
