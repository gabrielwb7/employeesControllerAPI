package one.digitalinnovation.personalAPI.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundExcepetion extends Throwable {

    public EmployeeNotFoundExcepetion(Long id) {
        super("Employee not found with ID " + id);

    }
}
