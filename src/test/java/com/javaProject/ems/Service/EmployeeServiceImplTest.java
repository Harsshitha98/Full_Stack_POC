package com.javaProject.ems.Service;


import com.javaProject.ems.DTO.EmployeeDTO;
import com.javaProject.ems.Entity.Employee;
import com.javaProject.ems.Exception.ResourceNotFoundException;

import com.javaProject.ems.Repository.EmployeeRepository;
import com.javaProject.ems.Service.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee_WithNullNames() {
        EmployeeDTO employeeDTO = new EmployeeDTO(null, null, null, null, "email@example.com", "1234567890");

        Employee savedEmployee = new Employee(1L, "User", "User", "Name", "email@example.com", "1234567890");
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        EmployeeDTO result = employeeService.createEmployee(employeeDTO);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("User");
        assertThat(result.getLastName()).isEqualTo("Name");
    }

    @Test
    public void testCreateEmployee_WithValidData() {
        EmployeeDTO employeeDTO = new EmployeeDTO(null, "John Doe", "John", "Doe", "john.doe@example.com", "1234567890");

        Employee savedEmployee = new Employee(1L, "John Doe", "John", "Doe", "john.doe@example.com", "1234567890");
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        EmployeeDTO result = employeeService.createEmployee(employeeDTO);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("John");
        assertThat(result.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testGetAllEmployeeById_EmployeeExists() {
        Employee employee = new Employee(1L, "John Doe", "John", "Doe", "john.doe@example.com", "1234567890");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeDTO result = employeeService.getAllEmployeeById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("John Doe");
    }

    @Test
    public void testGetAllEmployeeById_EmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getAllEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Employee does not exist with given ID :1");
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee(1L, "John Doe", "John", "Doe", "john@example.com", "1234567890");
        Employee employee2 = new Employee(2L, "Jane Smith", "Jane", "Smith", "jane@example.com", "0987654321");
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals("Jane Smith", employees.get(1).getName());
    }

    @Test
    public void testUpdateEmployee_Success() {
        Employee existingEmployee = new Employee(1L, "John Doe", "John", "Doe", "john@example.com", "1234567890");
        EmployeeDTO updatedEmployeeDTO = new EmployeeDTO(1L, "John Smith", "John", "Smith", "john.smith@example.com", "0987654321");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(existingEmployee);

        EmployeeDTO result = employeeService.updateEmployee(1L, updatedEmployeeDTO);

        assertEquals("John Smith", result.getName());
        verify(employeeRepository).save(existingEmployee); // Verify save is called
    }

    @Test
    public void testUpdateEmployee_ResourceNotFound() {
        EmployeeDTO updatedEmployeeDTO = new EmployeeDTO(1L, "John Smith", "John", "Smith", "john.smith@example.com", "0987654321");

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.updateEmployee(1L, updatedEmployeeDTO);
        });
        assertEquals("Employee does not exist with given ID : 1", exception.getMessage());
    }

    @Test
    public void testDeleteEmployee_Success() {
        Employee existingEmployee = new Employee(1L, "John Doe", "John", "Doe", "john@example.com", "1234567890");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L); // Verify delete is called
    }

    @Test
    public void testDeleteEmployee_ResourceNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployee(1L);
        });
        assertEquals("Employee does not exist with given ID : 1", exception.getMessage());
    }
}