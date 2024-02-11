package com.ems.ems.service;

import com.ems.ems.dto.CreateEmployeeDTO;
import com.ems.ems.dto.EmployeeDTO;
import com.ems.ems.dto.UpdateEmployeeDTO;
import com.ems.ems.entity.EmployeeEntity;
import com.ems.ems.exception.EmployeeNotFoundException;
import com.ems.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
//    private final EmployeeMapper mapper;

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByIsDeleted(false);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            employeeDTOS.add(mapToDTO(employeeEntity));
        }
        return employeeDTOS;
    }

    public EmployeeDTO findById(Long id) {
        EmployeeEntity employee = getEntityById(id);
        return mapToDTO(employee);
    }

    public void create(CreateEmployeeDTO createEmployeeDTO) {
        EmployeeEntity employee = mapToEntity(createEmployeeDTO);
        employeeRepository.save(employee);
    }

    public void update(Long id, UpdateEmployeeDTO updateEmployeeDTO) {
        EmployeeEntity employee = getEntityById(id);
        employee.setName(updateEmployeeDTO.getName());
        employee.setAge(updateEmployeeDTO.getAge());
        employee.setSalary(updateEmployeeDTO.getSalary());
        employee.setPhoneNumber(updateEmployeeDTO.getPhoneNumber());
        employee.setPosition(updateEmployeeDTO.getPosition());
        employeeRepository.save(employee);
    }

    public void delete(Long id) {
        EmployeeEntity employee = getEntityById(id);
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

    public EmployeeEntity getEntityById(Long id) {
        return employeeRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with Id: " + id));
    }

    public static EmployeeDTO mapToDTO(EmployeeEntity employeeEntity) {
        return EmployeeDTO.builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .age(employeeEntity.getAge())
                .phoneNumber(employeeEntity.getPhoneNumber())
                .position(employeeEntity.getPosition())
                .salary(employeeEntity.getSalary())
                .build();
    }

    public static EmployeeEntity mapToEntity(CreateEmployeeDTO createEmployeeDTO) {
        return EmployeeEntity.builder()
                .name(createEmployeeDTO.getName())
                .age(createEmployeeDTO.getAge())
                .salary(createEmployeeDTO.getSalary())
                .position(createEmployeeDTO.getPosition())
                .phoneNumber(createEmployeeDTO.getPhoneNumber())
                .build();
    }
}
