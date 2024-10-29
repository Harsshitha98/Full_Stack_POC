package com.javaProject.ems.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaProject.ems.DTO.EmployeeDTO;
import com.javaProject.ems.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @BeforeEach
    public void setUp1() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "John Doe", "John", "Doe", "john.doe@example.com", "1234567890");

        when(employeeService.createEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"firstName\":\"John\", \"lastName\":\"Doe\", \"email\":\"john.doe@example.com\", \"mobile\":\"1234567890\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "John Doe", "John", "Doe", "john.doe@example.com", "1234567890");

        when(employeeService.getAllEmployeeById(1L)).thenReturn(employeeDTO);

        mockMvc.perform(get("/api/employees/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(new EmployeeDTO(1L, "John Doe", "John", "Doe", "john@example.com", "1234567890"));

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(employees)));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        EmployeeDTO updatedEmployee = new EmployeeDTO(1L, "John Smith", "John", "Smith", "john.smith@example.com", "0987654321");

        when(employeeService.updateEmployee(any(Long.class), any(EmployeeDTO.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/api/employees/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedEmployee)));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/api/employees/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee deleted successfully!"));
    }
}

