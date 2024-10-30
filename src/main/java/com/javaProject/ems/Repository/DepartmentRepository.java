package com.javaProject.ems.Repository;

import com.javaProject.ems.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
