package com.javaProject.ems.Mapper;

import com.javaProject.ems.DTO.EmployeeDTO;
import com.javaProject.ems.Entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO mapToEmployeeDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getMobile()
        );
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getMobile()
        );
    }
}
