package one.digitalinnovation.personalAPI.controller;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private EmployeeService employeeService;

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

    @PutMapping("/{id}")
    public MessageResponse updateEmployee (@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) throws EmployeeNotFoundExcepetion {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundExcepetion {

        employeeService.delete(id);

    }
}
