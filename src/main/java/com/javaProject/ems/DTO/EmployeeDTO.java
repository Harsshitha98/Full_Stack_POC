package com.javaProject.ems.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;

}
