package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Employee;
import com.epam.rd.autocode.assessment.appliances.repository.EmployeeRepository;
import com.epam.rd.autocode.assessment.appliances.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee(1L, "Alice Johnson", "alice@example.com", "password1", "HR");
        Employee employee2 = new Employee(2L, "Bob Smith", "bob@example.com", "password2", "Finance");
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> result = employeeService.getAllEmployees();

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(employee1, employee2);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        Employee employee = new Employee(employeeId, "Alice Johnson", "alice@example.com", "password1", "HR");
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(employeeId);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Alice Johnson");
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee(null, "Alice Johnson", "alice@example.com", "password1", "HR");
        Employee savedEmployee = new Employee(1L, "Alice Johnson", "alice@example.com", "password1", "HR");
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);

        Employee result = employeeService.saveEmployee(employee);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testDeleteEmployeeById() {
        Long employeeId = 1L;
        doNothing().when(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployeeById(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}
