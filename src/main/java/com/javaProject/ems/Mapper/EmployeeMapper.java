package com.javaProject.ems.Mapper;

import com.javaProject.ems.DTO.EmployeeDTO;
import com.javaProject.ems.Entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        System.out.println(employee);
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getId()
        );
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO);
        Employee employee = new Employee();
        employee.setId(employee.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        return employee;
    }
}
