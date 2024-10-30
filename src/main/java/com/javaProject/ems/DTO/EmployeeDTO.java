package com.javaProject.ems.DTO;

import com.javaProject.ems.Entity.Department;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;

}
