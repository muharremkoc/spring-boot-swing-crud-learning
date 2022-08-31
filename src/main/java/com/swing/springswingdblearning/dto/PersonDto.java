package com.swing.springswingdblearning.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonDto {

    String firstName;
    String lastName;
    int age;
}
