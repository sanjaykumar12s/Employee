package com.UST_PROJ.Employee.Repository;

import com.UST_PROJ.Employee.Model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
    Flux<Employee> findBySkillSet(String skillSet);
}
