package com.example.examspringjpa.controller;

import com.example.examspringjpa.dto.EmployeeDTO;
import com.example.examspringjpa.dto.EmployeeProfileDTO;
import com.example.examspringjpa.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public HttpStatus createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.createEmployee(employeeDTO);
        return HttpStatus.OK;
    }

    //GET /employees?page=0&size=10
    @GetMapping("/employees")
    public List<EmployeeDTO> findAllEmployees(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "size") Integer size) {
        return employeeService.findAllEmployees(page, size);
    }

    @GetMapping("/profiles")
    public List<EmployeeProfileDTO> findAllEmployeeProfiles(@RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "size") Integer size) {
        return employeeService.findAllEmployeeProfiles(page, size);
    }

    //POST /employees/{employee_id}/profile
    @PostMapping("/employees/{employee_id}/profile")
    public HttpStatus creatEmployeeProfileWithEmployee(@Valid @RequestBody EmployeeProfileDTO employeeDTO, @PathVariable Long employee_id) {
        employeeService.createEmployeeProfileWithEmployee(employeeDTO,employee_id);
        return HttpStatus.OK;
    }

    // updateEmployeeEmailById
    @PutMapping("/employee/{employee_id}")
    public HttpStatus updateEmployeeEmailById(@PathVariable Long employee_id, @RequestParam(value = "new_emaiL") String new_emaiL) {
        employeeService.updateEmployeeEmailById(new_emaiL, employee_id);
        return HttpStatus.OK;
    }
    // findAllEmployees
    @GetMapping("/employee/findAll")
    public List<EmployeeDTO> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    // findEmployeeById
    @GetMapping("/employee/{id}")
    public EmployeeDTO findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    // findEmployeeProfileById
    @GetMapping("/employeeProfile/{id}")
    public EmployeeProfileDTO findEmployeeProfileById(@PathVariable Long id) {
        return employeeService.findEmployeeProfileById(id);
    }

    // createEmployeeProfile
    @PostMapping("/employeeProfile")
    public HttpStatus createEmployeeProfile(@Valid @RequestBody EmployeeProfileDTO employeeDTO) {
        employeeService.createEmployeeProfile(employeeDTO);
        return HttpStatus.OK;
    }

    @DeleteMapping("/employee/{id}")
    public HttpStatus deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/profile/{id}")
    public HttpStatus deleteProfile(@PathVariable Long id) {
        employeeService.deleteProfile(id);
        return HttpStatus.OK;
    }
}
