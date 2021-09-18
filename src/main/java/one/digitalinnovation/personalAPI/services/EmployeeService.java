package one.digitalinnovation.personalAPI.services;

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
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public MessageResponse createEmployee(EmployeeDTO employeeDTO) {

        Employee employeeToSave = employeeMapper.toModel(employeeDTO);

        Employee savedEmployee = employeeRepository.save(employeeToSave);

        MessageResponse messageResponse = MessageResponse.builder().message("Create employee with ID " + savedEmployee.getId()).build();

        return messageResponse;

    }

    public List<EmployeeDTO> listAll() {
        List<Employee> allEmployee = employeeRepository.findAll();
        return allEmployee.stream().map(employeeMapper::toDTO).collect(Collectors.toList());

    }

    private Employee isExist (Long id) throws EmployeeNotFoundExcepetion {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundExcepetion(id));
    }


    public EmployeeDTO catchEmployee(Long id) throws EmployeeNotFoundExcepetion {

        Employee employee = isExist(id);
        return employeeMapper.toDTO(employee);
    }


    public void delete(Long id) throws EmployeeNotFoundExcepetion {
        Employee employee = isExist(id);
        employeeRepository.deleteById(id);
    }
}
