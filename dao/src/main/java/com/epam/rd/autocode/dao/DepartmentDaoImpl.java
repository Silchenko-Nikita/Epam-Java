package com.epam.rd.autocode.dao;

import com.epam.rd.autocode.domain.Department;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {
    private static final String GET_ONE = "SELECT * FROM DEPARTMENT WHERE ID = ?;";
    private static final String GET_ALL = "SELECT * FROM DEPARTMENT;";
    private static final String INSERT = "INSERT INTO DEPARTMENT (ID, NAME, LOCATION) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE DEPARTMENT SET NAME = ?, LOCATION = ? WHERE ID = ?;";
    private static final String DELETE = "DELETE FROM DEPARTMENT WHERE ID = ?;";

    private final Connection connection;

    public DepartmentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Department> getById(BigInteger id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE)) {
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRowToDepartment(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching department by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                departments.add(mapRowToDepartment(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all departments", e);
        }
        return departments;
    }

    @Override
    public Department save(Department department) {
        if (getById(department.getId()).isPresent()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                preparedStatement.setString(1, department.getName());
                preparedStatement.setString(2, department.getLocation());
                preparedStatement.setString(3, department.getId().toString());
                preparedStatement.execute();
                return department;
            } catch (SQLException e) {
                throw new RuntimeException("Error saving department", e);
            }
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, department.getId().toString());
            preparedStatement.setString(2, department.getName());
            preparedStatement.setString(3, department.getLocation());
            preparedStatement.execute();
            return department;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving department", e);
        }
    }

    @Override
    public void delete(Department department) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, department.getId().toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting department", e);
        }
    }

    private Department mapRowToDepartment(ResultSet resultSet) throws SQLException {
        BigInteger id = new BigInteger(resultSet.getString("id"));
        String name = resultSet.getString("name");
        String location = resultSet.getString("location");
        return new Department(id, name, location);
    }
}
