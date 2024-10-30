package com.javaProject.ems.Service.Impl;

import com.javaProject.ems.DTO.EmployeeDTO;
import com.javaProject.ems.Entity.Department;
import com.javaProject.ems.Entity.Employee;
import com.javaProject.ems.Exception.ResourceNotFoundException;
import com.javaProject.ems.Mapper.EmployeeMapper;
import com.javaProject.ems.Repository.DepartmentRepository;
import com.javaProject.ems.Repository.EmployeeRepository;
import com.javaProject.ems.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        if (employeeDTO.getFirstName() == null || employeeDTO.getLastName() == null) {
            employeeDTO.setFirstName("User");
            employeeDTO.setLastName("Name");
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);

        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department does not exist with ID:" + employeeDTO.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }


    @Override
    public EmployeeDTO getAllEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with given ID :" + employeeId));

        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updatedEmployee) {
        Employee employee  = employeeRepository.findById(employeeId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee does not exist with given ID : " +employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department does not exist with ID:" + updatedEmployee.getDepartmentId()));
        employee.setDepartment(department);

        Employee updatedEmployeeDTO = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(updatedEmployeeDTO);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee does not exist with given ID : " +employeeId));
        employeeRepository.deleteById(employeeId);

    }

}
