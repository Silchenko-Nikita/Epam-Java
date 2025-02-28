package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet) {
        try {
            BigInteger id = new BigInteger(resultSet.getString("id"));
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String middleName = resultSet.getString("middleName");
            FullName fullName = new FullName(firstName, lastName, middleName);
            Position position = Position.valueOf(resultSet.getString("position"));
            LocalDate hired = resultSet.getDate("hiredate").toLocalDate();
            BigDecimal salary = resultSet.getBigDecimal("salary");

            return new Employee(id, fullName, position, hired, salary);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
