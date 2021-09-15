package one.digitalinnovation.personalAPI.controller;

import one.digitalinnovation.personalAPI.DTO.MessageResponse;
import one.digitalinnovation.personalAPI.entity.Employee;
import one.digitalinnovation.personalAPI.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }
}
