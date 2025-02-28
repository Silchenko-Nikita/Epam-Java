package com.epam.rd.autocode.dao;

import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {

    private static final String GET_BY_ID = "SELECT * FROM EMPLOYEE WHERE ID = ?;";
    private static final String GET_BY_DEPARTMENT = "SELECT * FROM EMPLOYEE WHERE DEPARTMENT = ?;";
    private static final String GET_BY_MANAGER = "SELECT * FROM EMPLOYEE WHERE MANAGER = ?;";
    private static final String GET_ALL = "SELECT * FROM EMPLOYEE;";
    private static final String INSERT = "INSERT INTO EMPLOYEE (ID, FIRSTNAME, LASTNAME, MIDDLENAME, POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE EMPLOYEE SET FIRSTNAME = ?, LASTNAME = ?, MIDDLENAME = ?, POSITION = ?, MANAGER = ?, HIREDATE = ?, SALARY = ?, DEPARTMENT = ? WHERE ID = ?;";
    private static final String DELETE = "DELETE FROM EMPLOYEE WHERE ID = ?;";

    private final Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Employee> getByDepartment(Department department) {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DEPARTMENT)) {
            preparedStatement.setString(1, department.getId().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(mapRowToEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching employees by department", e);
        }
        return employees;
    }

    @Override
    public List<Employee> getByManager(Employee manager) {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_MANAGER)) {
            preparedStatement.setString(1, manager.getId().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(mapRowToEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching employees by manager", e);
        }
        return employees;
    }

    @Override
    public Optional<Employee> getById(BigInteger id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRowToEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching employee by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                employees.add(mapRowToEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all employees", e);
        }
        return employees;
    }

    @Override
    public Employee save(Employee employee) {
        if (getById(employee.getId()).isPresent()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                preparedStatement.setString(1, employee.getFullName().getFirstName());
                preparedStatement.setString(2, employee.getFullName().getLastName());
                preparedStatement.setString(3, employee.getFullName().getMiddleName());
                preparedStatement.setString(4, employee.getPosition().name());
                preparedStatement.setString(5, employee.getManagerId().toString());
                preparedStatement.setDate(6, Date.valueOf(employee.getHired()));
                preparedStatement.setBigDecimal(7, employee.getSalary());
                preparedStatement.setString(8, employee.getDepartmentId().toString());
                preparedStatement.setString(9, employee.getId().toString());
                preparedStatement.execute();
                return employee;
            } catch (SQLException e) {
                throw new RuntimeException("Error updating employee", e);
            }
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, employee.getId().toString());
            preparedStatement.setString(2, employee.getFullName().getFirstName());
            preparedStatement.setString(3, employee.getFullName().getLastName());
            preparedStatement.setString(4, employee.getFullName().getMiddleName());
            preparedStatement.setString(5, employee.getPosition().name());
            preparedStatement.setString(6, employee.getManagerId().toString());
            preparedStatement.setDate(7, Date.valueOf(employee.getHired()));
            preparedStatement.setBigDecimal(8, employee.getSalary());
            preparedStatement.setString(9, employee.getDepartmentId().toString());
            preparedStatement.execute();
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving employee", e);
        }
    }

    @Override
    public void delete(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, employee.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    private Employee mapRowToEmployee(ResultSet resultSet) throws SQLException {
        BigInteger id = new BigInteger(resultSet.getString("ID"));
        FullName fullName = new FullName(resultSet.getString("FIRSTNAME"),
                                        resultSet.getString("LASTNAME"),
                                        resultSet.getString("MIDDLENAME"));
        Position position = Position.valueOf(resultSet.getString("POSITION"));
        LocalDate hired = resultSet.getDate("HIREDATE").toLocalDate();
        BigDecimal salary = resultSet.getBigDecimal("SALARY");

        BigInteger departmentId =  resultSet.getString("DEPARTMENT") != null
                            ? new BigInteger(resultSet.getString("DEPARTMENT"))
                            : new BigInteger("0");

        BigInteger managerId = resultSet.getString("MANAGER") != null
                ? new BigInteger(resultSet.getString("MANAGER"))
                : new BigInteger("0");

        return new Employee(id, fullName, position, hired, salary, managerId, departmentId);
    }
}

