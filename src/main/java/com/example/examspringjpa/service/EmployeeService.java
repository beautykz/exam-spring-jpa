package com.example.examspringjpa.service;

import com.example.examspringjpa.dto.EmployeeDTO;
import com.example.examspringjpa.dto.EmployeeProfileDTO;
import com.example.examspringjpa.exception.ResourceCanNotBeDeleteException;
import com.example.examspringjpa.exception.ResourceNotFoundException;
import com.example.examspringjpa.model.Employee;
import com.example.examspringjpa.model.EmployeeProfile;
import com.example.examspringjpa.repository.EmployeeProfileRepository;
import com.example.examspringjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeProfileRepository employeeProfileRepository;

    public void createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmail(employeeDTO.getEmail());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());

        employeeRepository.save(employee);
    }

    public void updateEmployeeEmailById(String email, Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            throw new ResourceNotFoundException();
        }
        Employee employee1 = employee.get();
        employee1.setEmail(email);
        employeeRepository.save(employee1);
    }

    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();


        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setCreated_at(employee.getCreated_at());
            employeeDTO.setUpdated_at(employee.getUpdated_at());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    public List<EmployeeDTO> findAllEmployees(Integer page, Integer size) {
        Page<Employee> employees =  employeeRepository.findAll(PageRequest.of(page, size));

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setCreated_at(employee.getCreated_at());
            employeeDTO.setUpdated_at(employee.getUpdated_at());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    public List<EmployeeProfileDTO> findAllEmployeeProfiles(Integer page, Integer size) {
        Page<EmployeeProfile> employeeProfiles = employeeProfileRepository.findAll(PageRequest.of(page, size));
        List<EmployeeProfileDTO> employeeProfileDTOS = new ArrayList<>();
        for (EmployeeProfile employeeProfile : employeeProfiles) {
            EmployeeProfileDTO employeeProfileDTO = new EmployeeProfileDTO();
            employeeProfileDTO.setEmployeeId(employeeProfile.getId());
            employeeProfileDTO.setId(employeeProfile.getId());
            employeeProfileDTO.setDepartment(employeeProfile.getDepartment());
            employeeProfileDTO.setPosition(employeeProfile.getPosition());
            employeeProfileDTO.setCreated_at(employeeProfile.getCreated_at());
            employeeProfileDTO.setUpdated_at(employeeProfile.getCreated_at());
            employeeProfileDTOS.add(employeeProfileDTO);
        }
        return employeeProfileDTOS;
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        try {
            employeeRepository.delete(employeeOptional.get());
        } catch (Exception e) {
            throw new ResourceCanNotBeDeleteException("Employee Profile is not empty");
        }
    }

    public void deleteProfile(Long id) {
        Optional<EmployeeProfile> employeeProfileOptional = employeeProfileRepository.findById(id);
        if (!employeeProfileOptional.isPresent()) {
            throw  new ResourceNotFoundException();
        }
        employeeProfileRepository.delete(employeeProfileOptional.get());
    }

    public EmployeeDTO findEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        Employee employee = employeeOptional.get();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setId(employee.getId());
        employeeDTO.setUpdated_at(employee.getUpdated_at());
        employeeDTO.setCreated_at(employee.getCreated_at());
        return employeeDTO;
    }

    public EmployeeProfileDTO findEmployeeProfileById(Long id) {
        Optional<EmployeeProfile> employeeProfileOptional = employeeProfileRepository.findById(id);
        if (!employeeProfileOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        EmployeeProfile employeeProfile = employeeProfileOptional.get();
        EmployeeProfileDTO employeeProfileDTO = new EmployeeProfileDTO();
        employeeProfileDTO.setDepartment(employeeProfile.getDepartment());
        employeeProfileDTO.setPosition(employeeProfile.getPosition());
        employeeProfileDTO.setId(employeeProfile.getId());
        employeeProfileDTO.setCreated_at(employeeProfile.getCreated_at());
        employeeProfileDTO.setUpdated_at(employeeProfile.getUpdated_at());
        return employeeProfileDTO;
    }

    public void createEmployeeProfile(EmployeeProfileDTO employeeProfileDTO) {
        EmployeeProfile employeeProfile = new EmployeeProfile();
        employeeProfile.setDepartment(employeeProfileDTO.getDepartment());
        employeeProfile.setPosition(employeeProfileDTO.getPosition());

        employeeProfileRepository.save(employeeProfile);
    }

    public void createEmployeeProfileWithEmployee(EmployeeProfileDTO employeeProfileDTO, Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }

        Employee employee = employeeOptional.get();
        EmployeeProfile employeeProfile = new EmployeeProfile();
        employeeProfile.setDepartment(employeeProfileDTO.getDepartment());
        employeeProfile.setPosition(employeeProfileDTO.getPosition());
        employeeProfile.setEmployee(employee);
        employeeProfileRepository.save(employeeProfile);
    }
}
