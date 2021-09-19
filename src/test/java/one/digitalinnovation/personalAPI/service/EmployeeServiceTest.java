package one.digitalinnovation.personalAPI.service;

import one.digitalinnovation.personalAPI.DTO.request.EmployeeDTO;
import one.digitalinnovation.personalAPI.DTO.response.MessageResponse;
import one.digitalinnovation.personalAPI.entity.Employee;
import one.digitalinnovation.personalAPI.repository.EmployeeRepository;
import one.digitalinnovation.personalAPI.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static one.digitalinnovation.personalAPI.utils.EmployeeUtils.createFakeDTO;
import static one.digitalinnovation.personalAPI.utils.EmployeeUtils.createFakeEntity;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGivenEmployeeDTOThenReturnSuccessSavedMessage() {
        EmployeeDTO employeeDTO = createFakeDTO();
        Employee expectedSavedEmployee = createFakeEntity();

        when(employeeRepository.save(any(Employee.class))).thenReturn(expectedSavedEmployee);

        MessageResponse expectedMessageSuccess = createExpectedMessageResponse(expectedSavedEmployee.getId());
        MessageResponse successMessage = employeeService.createEmployee(employeeDTO);

        assertEquals(expectedMessageSuccess, successMessage);
    }


    private MessageResponse createExpectedMessageResponse(Long id) {
        return MessageResponse.builder()
                .message("Create employee with ID " + id)
                .build();
    }
}