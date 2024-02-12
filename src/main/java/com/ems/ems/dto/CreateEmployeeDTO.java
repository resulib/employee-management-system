package com.ems.ems.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeDTO {
    private String name;
    private int age;
    private String phoneNumber;
    private String position;
    private double salary;
}
