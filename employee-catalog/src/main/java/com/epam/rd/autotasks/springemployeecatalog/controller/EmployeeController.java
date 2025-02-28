package com.epam.rd.autotasks.springemployeecatalog.controller;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2147483647") int size,
            @RequestParam(defaultValue = "lastName") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return employeeService.getAllEmployees(pageable).getContent();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean full_chain) {
        return employeeService.getEmployeeById(id, full_chain);
    }

    @GetMapping("/by_manager/{managerId}")
    public List<Employee> getEmployeesByManagerId(
            @PathVariable Long managerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2147483647") int size,
            @RequestParam(defaultValue = "lastName") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return employeeService.getEmployeesByManagerId(managerId, pageable).getContent();
    }

    @GetMapping("/by_department/{department}")
    public List<Employee> getEmployeesByDepartment(
            @PathVariable String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2147483647") int size,
            @RequestParam(defaultValue = "lastName") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        try {
            Long departmentId = Long.parseLong(department);
            return employeeService.getEmployeesByDepartmentId(departmentId, pageable).getContent();
        } catch (NumberFormatException e) {
            return employeeService.getEmployeesByDepartmentName(department, pageable).getContent();
        }
    }
}