package com.epam.rd.autotasks.springemployeecatalog.repository;

import com.epam.rd.autotasks.springemployeecatalog.domain.Department;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.FullName;
import com.epam.rd.autotasks.springemployeecatalog.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Employee> employeeRowMapperWithoutManager = (rs, rowNum) -> {
        Long id = rs.getLong("ID");
        FullName fullName = new FullName(
                rs.getString("FIRSTNAME"),
                rs.getString("LASTNAME"),
                rs.getString("MIDDLENAME")
        );
        Position position = Position.valueOf(rs.getString("POSITION").toUpperCase());
        LocalDate hiredDate = rs.getDate("HIREDATE").toLocalDate();
        BigDecimal salary = rs.getBigDecimal("SALARY");

        Employee manager = null;

        Department department = rs.getObject("DEPARTMENT") != null
                ? new Department(
                rs.getLong("DEPARTMENT"),
                rs.getString("DEPT_NAME"),
                rs.getString("DEPT_LOCATION"))
                : null;

        return new Employee(id, fullName, position, hiredDate, salary, manager, department);
    };

    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("ID");
        FullName fullName = new FullName(
                rs.getString("FIRSTNAME"),
                rs.getString("LASTNAME"),
                rs.getString("MIDDLENAME")
        );
        Position position = Position.valueOf(rs.getString("POSITION").toUpperCase());
        LocalDate hiredDate = rs.getDate("HIREDATE").toLocalDate();
        BigDecimal salary = rs.getBigDecimal("SALARY");

        Employee manager = rs.getObject("MANAGER") != null
                ? findByIdWithoutManager(rs.getLong("MANAGER"))
                : null;

        Department department = rs.getObject("DEPARTMENT") != null
                ? new Department(
                rs.getLong("DEPARTMENT"),
                rs.getString("DEPT_NAME"),
                rs.getString("DEPT_LOCATION"))
                : null;

        return new Employee(id, fullName, position, hiredDate, salary, manager, department);
    };

    private final RowMapper<Employee> employeeRowMapperFullChain = (rs, rowNum) -> {
        Long id = rs.getLong("ID");

        FullName fullName = new FullName(
                rs.getString("FIRSTNAME"),
                rs.getString("LASTNAME"),
                rs.getString("MIDDLENAME")
        );
        Position position = Position.valueOf(rs.getString("POSITION").toUpperCase());
        LocalDate hiredDate = rs.getDate("HIREDATE").toLocalDate();
        BigDecimal salary = rs.getBigDecimal("SALARY");

        Employee manager = rs.getObject("MANAGER") != null
                ? findById(rs.getLong("MANAGER"), true)
                : null;

        Department department = rs.getObject("DEPARTMENT") != null
                ? new Department(
                rs.getLong("DEPARTMENT"),
                rs.getString("DEPT_NAME"),
                rs.getString("DEPT_LOCATION"))
                : null;

        return new Employee(id, fullName, position, hiredDate, salary, manager, department);
    };

    public Page<Employee> findAll(Pageable pageable) {
        String sql = buildPagedQuery(
                "SELECT e.*, d.NAME AS DEPT_NAME, d.LOCATION AS DEPT_LOCATION " +
                        "FROM EMPLOYEE e " +
                        "LEFT JOIN DEPARTMENT d ON e.DEPARTMENT = d.ID",
                pageable
        );

        List<Employee> employees = jdbcTemplate.query(sql, employeeRowMapper);
        int total = countAllEmployees();

        return new PageImpl<>(employees, pageable, total);
    }

    public Employee findById(Long id, boolean fullChain) {
        String sql = "SELECT e.*, d.NAME AS DEPT_NAME, d.LOCATION AS DEPT_LOCATION " +
                "FROM EMPLOYEE e " +
                "LEFT JOIN DEPARTMENT d ON e.DEPARTMENT = d.ID " +
                "WHERE e.ID = ?";
        if (fullChain) return jdbcTemplate.queryForObject(sql, employeeRowMapperFullChain, id);
        else return jdbcTemplate.queryForObject(sql, employeeRowMapper, id);
    }

    public Employee findByIdWithoutManager(Long id) {
        String sql = "SELECT e.*, d.NAME AS DEPT_NAME, d.LOCATION AS DEPT_LOCATION " +
                "FROM EMPLOYEE e " +
                "LEFT JOIN DEPARTMENT d ON e.DEPARTMENT = d.ID " +
                "WHERE e.ID = ?";
        return jdbcTemplate.queryForObject(sql, employeeRowMapperWithoutManager, id);
    }

    public Page<Employee> findByManagerId(Long managerId, Pageable pageable) {
        String sql = buildPagedQuery(
                "SELECT e.*, d.NAME AS DEPT_NAME, d.LOCATION AS DEPT_LOCATION " +
                        "FROM EMPLOYEE e " +
                        "LEFT JOIN DEPARTMENT d ON e.DEPARTMENT = d.ID " +
                        "WHERE e.MANAGER = ?",
                pageable
        );

        List<Employee> employees = jdbcTemplate.query(sql, employeeRowMapper, managerId);
        int total = countEmployeesByManager(managerId);

        return new PageImpl<>(employees, pageable, total);
    }

    public Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable) {
        String sql = buildPagedQuery(
                "SELECT e.*, d.NAME AS DEPT_NAME, d.LOCATION AS DEPT_LOCATION " +
                        "FROM EMPLOYEE e " +
                        "LEFT JOIN DEPARTMENT d ON e.DEPARTMENT = d.ID " +
                        "WHERE e.DEPARTMENT = ?",
                pageable
        );

        List<Employee> employees = jdbcTemplate.query(sql, employeeRowMapper, departmentId);
        int total = countEmployeesByDepartment(departmentId);

        return new PageImpl<>(employees, pageable, total);
    }

    public Page<Employee> findByDepartmentName(String departmentName, Pageable pageable) {
        String sql = buildPagedQuery(
                "SELECT e.*, d.NAME AS DEPT_NAME, d.LOCATION AS DEPT_LOCATION " +
                        "FROM EMPLOYEE e " +
                        "LEFT JOIN DEPARTMENT d ON e.DEPARTMENT = d.ID " +
                        "WHERE d.NAME = ?",
                pageable
        );

        List<Employee> employees = jdbcTemplate.query(sql, employeeRowMapper, departmentName);
        int total = countEmployeesByDepartmentName(departmentName);

        return new PageImpl<>(employees, pageable, total);
    }

    private int countEmployeesByDepartmentName(String departmentName) {
        String sql = "SELECT COUNT(*) FROM EMPLOYEE e " +
                "LEFT JOIN DEPARTMENT d ON e.DEPARTMENT = d.ID " +
                "WHERE d.NAME = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, departmentName);
    }

    private int countAllEmployees() {
        String sql = "SELECT COUNT(*) FROM EMPLOYEE";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private int countEmployeesByManager(Long managerId) {
        String sql = "SELECT COUNT(*) FROM EMPLOYEE WHERE MANAGER = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, managerId);
    }

    private int countEmployeesByDepartment(Long departmentId) {
        String sql = "SELECT COUNT(*) FROM EMPLOYEE WHERE DEPARTMENT = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, departmentId);
    }

    private String buildPagedQuery(String baseQuery, Pageable pageable) {
        String sortColumn = pageable.getSort().stream()
                .findFirst()
                .map(order -> order.getProperty())
                .orElse("LASTNAME");

        if (sortColumn.equals("hired")) {
            sortColumn = "HIREDATE";
        }

        return baseQuery +
                " ORDER BY " + sortColumn + " ASC " +
                " LIMIT " + pageable.getPageSize() +
                " OFFSET " + pageable.getOffset();
    }
}
