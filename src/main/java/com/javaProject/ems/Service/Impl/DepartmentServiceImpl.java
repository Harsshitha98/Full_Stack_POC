package com.javaProject.ems.Service.Impl;

import com.javaProject.ems.DTO.DepartmentDTO;
import com.javaProject.ems.Entity.Department;
import com.javaProject.ems.Entity.Employee;
import com.javaProject.ems.Exception.ResourceNotFoundException;
import com.javaProject.ems.Mapper.DepartmentMapper;
import com.javaProject.ems.Repository.DepartmentRepository;
import com.javaProject.ems.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    
    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

        if (departmentDTO.getDepartmentName() == null || departmentDTO.getDepartmentDesc() == null) {
            departmentDTO.setDepartmentName("Admin");
            departmentDTO.setDepartmentDesc("This is default admin department");
        }

        Department department = DepartmentMapper.mapToDepartment(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getAllDepartmentById(Long departmentId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department does not exist with given ID :" + departmentId));

        return DepartmentMapper.mapToDepartmentDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDTO(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO updatedDepartment) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department does not exist with given ID : " + departmentId));

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDesc(updatedDepartment.getDepartmentDesc());

        Department updatedDepartmentDTO = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDTO(updatedDepartmentDTO);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department does not exist with given ID : " +departmentId));
        departmentRepository.deleteById(departmentId);
    }
}


