package com.javaProject.ems.Mapper;

import com.javaProject.ems.DTO.DepartmentDTO;
import com.javaProject.ems.Entity.Department;

public class DepartmentMapper {

    public static DepartmentDTO mapToDepartmentDTO(Department department) {
        System.out.println(department);
        return new DepartmentDTO(department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDesc());
    }

    public static Department mapToDepartment(DepartmentDTO departmentDTO) {
        System.out.println(departmentDTO);
        return new Department(departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDesc());
    }
}
