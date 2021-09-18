package one.digitalinnovation.personalAPI.repository;

import one.digitalinnovation.personalAPI.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
