package com.ems.ems.dto;

import com.ems.ems.constants.Position;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeDTO {
    private String name;
    private int age;
    private String phoneNumber;
    private Position position;
    private double salary;
}
