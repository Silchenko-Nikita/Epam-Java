package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class EmployeeSetMapper implements SetMapper<Set<Employee>> {
    private class MyEmployee {
        private final BigInteger id;
        private final FullName fullName;
        private final Position position;
        private final LocalDate hired;
        private final BigDecimal salary;
        private final BigInteger managerId;

        @JsonCreator
        public MyEmployee(final BigInteger id,
                          final FullName fullName,
                          final Position position,
                          final LocalDate hired,
                          final BigDecimal salary,
                          final BigInteger managerId) {
            this.id = id;
            this.fullName = fullName;
            this.position = position;
            this.hired = hired;
            this.salary = salary.setScale(5, RoundingMode.HALF_UP);
            this.managerId = managerId;
        }
    }

    Employee getEmployeeFromMyEmployee(MyEmployee myEmployee, Map<BigInteger, MyEmployee> employeeCache) {
        if (myEmployee == null) return null;

        Employee manager = null;
        if (myEmployee.managerId != null) {
            manager = getEmployeeFromMyEmployee(employeeCache.get(myEmployee.managerId), employeeCache);
        }

        return new Employee(myEmployee.id, myEmployee.fullName, myEmployee.position, myEmployee.hired, myEmployee.salary, manager);
    }

    @Override
    public Set<Employee> mapSet(ResultSet resultSet) {
        try {
            Set<MyEmployee> myEmployees = new HashSet<>();
            Set<Employee> employees = new HashSet<>();
            Map<BigInteger, MyEmployee> employeeCache = new HashMap<>();
            Map<MyEmployee, BigInteger> managersIds = new HashMap<>();

            while (resultSet.next()) {
                BigInteger id = new BigInteger(resultSet.getString("id"));
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String middleName = resultSet.getString("middleName");
                FullName fullName = new FullName(firstName, lastName, middleName);
                Position position = Position.valueOf(resultSet.getString("position"));
                LocalDate hired = resultSet.getDate("hiredate").toLocalDate();
                BigDecimal salary = resultSet.getBigDecimal("salary");

                BigInteger managerId = resultSet.getString("manager") != null
                        ? new BigInteger(resultSet.getString("manager"))
                        : null;


                MyEmployee myEmployee = new MyEmployee(id, fullName, position, hired, salary, managerId);

                if (managerId != null) {
                    managersIds.put(myEmployee, managerId);
                }

                myEmployees.add(myEmployee);
                employeeCache.put(id, myEmployee);
            }

            for (MyEmployee myEmployee: myEmployees) {
                employees.add(getEmployeeFromMyEmployee(myEmployee, employeeCache));
            }

            return employees;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping ResultSet to Set<Employee>", e);
        }
    }
}