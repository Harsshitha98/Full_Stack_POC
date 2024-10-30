package com.javaProject.ems.Controller;


import com.javaProject.ems.DTO.DepartmentDTO;
import com.javaProject.ems.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {

        DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);
        System.out.println("Received Employee DTO: " + departmentDTO);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDTO> getAllDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentDTO departmentDTO = departmentService.getAllDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDTO);

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable("id") Long departmentId,
                                                       @RequestBody DepartmentDTO updateDepartment) {
        DepartmentDTO departmentsDTO = departmentService.updateDepartment(departmentId, updateDepartment);
        return ResponseEntity.ok(departmentsDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department deleted successfully!");
    }

}
