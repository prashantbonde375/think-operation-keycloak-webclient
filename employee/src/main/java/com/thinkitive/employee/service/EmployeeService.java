package com.thinkitive.employee.service;

import com.thinkitive.employee.model.Employee;
import com.thinkitive.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedEmployee.getName());
                    existing.setEmail(updatedEmployee.getEmail());
                    existing.setDepartment(updatedEmployee.getDepartment());
                    existing.setSalary(updatedEmployee.getSalary());
                    return employeeRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
