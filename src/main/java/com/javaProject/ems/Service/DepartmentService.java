package com.javaProject.ems.Service;

import com.javaProject.ems.DTO.DepartmentDTO;


import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getAllDepartmentById(Long departmentId);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO updatedDepartment);

    void deleteDepartment(Long departmentId);

}
