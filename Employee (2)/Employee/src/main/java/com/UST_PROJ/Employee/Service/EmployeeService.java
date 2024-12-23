package com.UST_PROJ.Employee.Service;

import com.UST_PROJ.Employee.Model.Employee;
import com.UST_PROJ.Employee.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<Employee> addEmployee(Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    public Mono<Employee> getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Flux<Employee> getEmployeesBySkill(String skillSet) {
        return employeeRepository.findBySkillSet(skillSet);
    }

    public Mono<Employee> updateEmployee(String employeeId, Employee updatedEmployee) {
        return employeeRepository.findById(employeeId)
                .flatMap(existingEmployee -> {
                    updatedEmployee.setId(existingEmployee.getId());
                    updatedEmployee.setUpdatedAt(LocalDateTime.now());
                    return employeeRepository.save(updatedEmployee);
                });
    }

    public Mono<Void> deleteEmployee(String employeeId) {
        return employeeRepository.deleteById(employeeId);
    }

    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
