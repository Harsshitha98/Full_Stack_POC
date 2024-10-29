package com.javaProject.ems.Mapper;



import com.javaProject.ems.DTO.EmployeeDTO;
import com.javaProject.ems.Entity.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeMapperTest {

    @Test
    public void testMapToEmployeeDTO() {
        Employee employee = new Employee(1L, "John Doe", "John", "Doe", "john.doe@example.com", "1234567890");

        EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(employee);

        assertThat(employeeDTO).isNotNull();
        assertThat(employeeDTO.getId()).isEqualTo(employee.getId());
        assertThat(employeeDTO.getName()).isEqualTo(employee.getName());
        assertThat(employeeDTO.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(employeeDTO.getLastName()).isEqualTo(employee.getLastName());
        assertThat(employeeDTO.getEmail()).isEqualTo(employee.getEmail());
        assertThat(employeeDTO.getMobile()).isEqualTo(employee.getMobile());
    }

    @Test
    public void testMapToEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO(2L, "Jane Doe", "Jane", "Doe", "jane.doe@example.com", "0987654321");

        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);

        assertThat(employee).isNotNull();
        assertThat(employee.getId()).isEqualTo(employeeDTO.getId());
        assertThat(employee.getName()).isEqualTo(employeeDTO.getName());
        assertThat(employee.getFirstName()).isEqualTo(employeeDTO.getFirstName());
        assertThat(employee.getLastName()).isEqualTo(employeeDTO.getLastName());
        assertThat(employee.getEmail()).isEqualTo(employeeDTO.getEmail());
        assertThat(employee.getMobile()).isEqualTo(employeeDTO.getMobile());
    }
}

