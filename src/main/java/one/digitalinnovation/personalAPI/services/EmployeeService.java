package one.digitalinnovation.personalAPI.services;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personalAPI.DTO.request.EmployeeDTO;
import one.digitalinnovation.personalAPI.DTO.response.MessageResponse;
import one.digitalinnovation.personalAPI.entity.Employee;
import one.digitalinnovation.personalAPI.exceptions.EmployeeNotFoundExcepetion;
import one.digitalinnovation.personalAPI.mapper.EmployeeMapper;
import one.digitalinnovation.personalAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    public MessageResponse createEmployee(EmployeeDTO employeeDTO) {

        Employee employeeToSave = employeeMapper.toModel(employeeDTO);

        Employee savedEmployee = employeeRepository.save(employeeToSave);

        MessageResponse messageResponse = createMessageResponse(savedEmployee.getId(), "Create employee with ID ");

        return messageResponse;

    }

    public List<EmployeeDTO> listAll() {
        List<Employee> allEmployee = employeeRepository.findAll();
        return allEmployee.stream().map(employeeMapper::toDTO).collect(Collectors.toList());

    }

    public EmployeeDTO catchEmployee(Long id) throws EmployeeNotFoundExcepetion {

        Employee employee = isExist(id);
        return employeeMapper.toDTO(employee);
    }

    public void delete(Long id) throws EmployeeNotFoundExcepetion {
        isExist(id);
        employeeRepository.deleteById(id);
    }

    public MessageResponse updateEmployee(Long id, EmployeeDTO employeeDTO) throws EmployeeNotFoundExcepetion {
        isExist(id);

        Employee employeeUpdate = employeeMapper.toModel(employeeDTO);

        Employee updateEmployee = employeeRepository.save(employeeUpdate);
        
        return createMessageResponse(updateEmployee.getId(), "Update employee by id ");

    }

    private EmployeeRepository employeeRepository;

    private MessageResponse createMessageResponse (Long id, String textMessage) {
        return MessageResponse.builder().message(textMessage + id).build();
    }

    private Employee isExist (Long id) throws EmployeeNotFoundExcepetion {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundExcepetion(id));
    }


}
