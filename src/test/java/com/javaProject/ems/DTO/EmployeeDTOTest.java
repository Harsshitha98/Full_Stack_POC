package com.javaProject.ems.DTO;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeDTOTest {

    @Test
    public void testAllArgsConstructor() {
        EmployeeDTO employee = new EmployeeDTO(1L, "John Doe", "John", "Doe", "john.doe@example.com", "1234567890");

        assertThat(employee.getId()).isEqualTo(1L);
        assertThat(employee.getName()).isEqualTo("John Doe");
        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getLastName()).isEqualTo("Doe");
        assertThat(employee.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(employee.getMobile()).isEqualTo("1234567890");
    }

    @Test
    public void testNoArgsConstructor() {
        EmployeeDTO employee = new EmployeeDTO();

        assertThat(employee.getId()).isNull();
        assertThat(employee.getName()).isNull();
        assertThat(employee.getFirstName()).isNull();
        assertThat(employee.getLastName()).isNull();
        assertThat(employee.getEmail()).isNull();
        assertThat(employee.getMobile()).isNull();
    }

    @Test
    public void testSettersAndGetters() {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setId(2L);
        employee.setName("Jane Doe");
        employee.setFirstName("Jane");
        employee.setLastName("Doe");
        employee.setEmail("jane.doe@example.com");
        employee.setMobile("0987654321");

        assertThat(employee.getId()).isEqualTo(2L);
        assertThat(employee.getName()).isEqualTo("Jane Doe");
        assertThat(employee.getFirstName()).isEqualTo("Jane");
        assertThat(employee.getLastName()).isEqualTo("Doe");
        assertThat(employee.getEmail()).isEqualTo("jane.doe@example.com");
        assertThat(employee.getMobile()).isEqualTo("0987654321");
    }
}


