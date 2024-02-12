package com.ems.ems.mapper;

import com.ems.ems.dto.CreateEmployeeDTO;
import com.ems.ems.dto.EmployeeDTO;
import com.ems.ems.dto.UpdateEmployeeDTO;
import com.ems.ems.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    List<EmployeeDTO> toEmployeeDTOList(List<EmployeeEntity> employeeEntities);

    EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity);

    EmployeeEntity toEmployeeEntity(CreateEmployeeDTO createEmployeeDTO);

    void toEmployeeEntity(UpdateEmployeeDTO updateEmployeeDTO, @MappingTarget EmployeeEntity employeeEntity);

}
