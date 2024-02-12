package com.ems.ems.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private int age;
    private String phoneNumber;
    private String position;
    private double salary;
}
