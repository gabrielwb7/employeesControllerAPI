package one.digitalinnovation.personalAPI.services;

import one.digitalinnovation.personalAPI.DTO.MessageResponse;
import one.digitalinnovation.personalAPI.entity.Employee;
import one.digitalinnovation.personalAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public MessageResponse createEmployee(Employee employee) {

        Employee savedEmployee = employeeRepository.save(employee);
        return MessageResponse.builder().message("Create employee with ID " + savedEmployee.getId()).build();

    }
}
