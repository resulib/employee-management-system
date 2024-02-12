package com.ems.ems.repository;

import com.ems.ems.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAllByIsDeleted(boolean isDeleted);
    Optional<EmployeeEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);
}
