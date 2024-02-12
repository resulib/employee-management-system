package com.ems.ems.service;

import com.ems.ems.dto.CreateEmployeeDTO;
import com.ems.ems.dto.EmployeeDTO;
import com.ems.ems.dto.UpdateEmployeeDTO;
import com.ems.ems.entity.EmployeeEntity;
import com.ems.ems.exception.EmployeeNotFoundException;
import com.ems.ems.mapper.EmployeeMapper;
import com.ems.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public List<EmployeeDTO> findAll() {
        var employeeEntities = employeeRepository.findAllByIsDeleted(false);
        return mapper.toEmployeeDTOList(employeeEntities);
    }

    public EmployeeDTO findById(Long id) {
        EmployeeEntity employee = getEntityById(id);
        return mapper.toEmployeeDTO(employee);
    }

    public void create(CreateEmployeeDTO createEmployeeDTO) {
        EmployeeEntity employee = mapper.toEmployeeEntity(createEmployeeDTO);
        employeeRepository.save(employee);
    }

    public void update(Long id, UpdateEmployeeDTO updateEmployeeDTO) {
        EmployeeEntity employee = getEntityById(id);
        mapper.toEmployeeEntity(updateEmployeeDTO, employee);
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

}
