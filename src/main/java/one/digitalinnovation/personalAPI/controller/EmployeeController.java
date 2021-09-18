package one.digitalinnovation.personalAPI.controller;

import one.digitalinnovation.personalAPI.DTO.request.EmployeeDTO;
import one.digitalinnovation.personalAPI.DTO.response.MessageResponse;
import one.digitalinnovation.personalAPI.exceptions.EmployeeNotFoundExcepetion;
import one.digitalinnovation.personalAPI.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public MessageResponse createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> listAll() {
        return employeeService.listAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO catchEmployee (@PathVariable Long id) throws EmployeeNotFoundExcepetion {
        return employeeService.catchEmployee(id);
    }
}
