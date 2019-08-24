package com.basaki.repository;

import com.basaki.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeRepository {
    private Map<Long, Employee> employeeMap = new HashMap<>();

    public List<Employee> findAll() {
        return employeeMap.values().stream().collect(Collectors.toList());
    }

    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(employeeMap.get(id));
    }

    public Employee save(Employee employee) {
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public void delete(Employee employee) {
        employeeMap.remove(employee.getId());
    }
}
