package com.swing.springswingdblearning.service;

import com.swing.springswingdblearning.dto.PersonDto;
import com.swing.springswingdblearning.model.Person;

import java.util.List;

public interface PersonService {
    Person savePerson(PersonDto personDto);
    void updatePerson(String firstname,String lastname,int age);
    Person getPerson(int id);
}
