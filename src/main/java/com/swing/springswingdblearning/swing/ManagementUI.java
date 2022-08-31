package com.swing.springswingdblearning.swing;

import com.swing.springswingdblearning.dto.PersonDto;
import com.swing.springswingdblearning.model.Person;
import com.swing.springswingdblearning.repository.PersonRepository;
import com.swing.springswingdblearning.service.PersonService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ManagementUI extends JFrame implements PersonService {


    private String updateName;
    private String updateLastName;
    private int updateAge;
    private JTabbedPane tabbedPane1;
    private JPanel insert_panel;
    private JPanel update_panel;
    private JPanel panel1;
    private JButton button1;
    private JTextField first_name_text;
    private JTextField last_name_text;
    private JTextField age_text;
    private JPanel delete_panel;
    private JLabel first_name_value;
    private JLabel last_name_value;
    private JLabel age_value;
    private JTextField person_id_Text;
    private JTextField update_last_name_text;
    private JTextField update_age_text;
    private JTextField textField4;
    private JButton delete_button;
    private JButton select_button;
    private JLabel person_id;
    private JTextField update_person_id;
    private JCheckBox first_name_check;
    private JCheckBox last_name_check;
    private JCheckBox age_check;
    private JTextField update_first_name_text;
    private JButton update_button;
    private JButton checkButton;
    private JButton update_person;

    @Autowired
    private PersonRepository personRepository;

    public ManagementUI() {
        setResizable(true);
        setType(Type.UTILITY);
        add(panel1);
        setSize(700, 400);
        setTitle("Person Management UI");
        //add(panel1);
        tabbedPane1.setTitleAt(0, "Insert");
        tabbedPane1.setTitleAt(1, "Delete/Select");
        tabbedPane1.setTitleAt(2, "Update");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (personRepository.existsById(Integer.parseInt(update_person_id.getText()))) {
                    Person person = getPerson(Integer.parseInt(update_person_id.getText()));
                    update_first_name_text.setText(person.getFirstName());
                    update_last_name_text.setText(person.getLastName());
                    update_age_text.setText(String.valueOf(person.getAge()));
                } else {
                    JOptionPane.showMessageDialog(tabbedPane1, person_id_Text.getText() + "ID's Person is not found");
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = first_name_text.getText();
                String lastname = last_name_text.getText();
                int age = Integer.parseInt(age_text.getText());
                PersonDto personDto = PersonDto.builder()
                        .firstName(firstname)
                        .lastName(lastname)
                        .age(age).build();
                savePerson(personDto);
            }
        });
        delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String personID = person_id_Text.getText();
                if (personRepository.existsById(Integer.parseInt(personID))) {
                    personRepository.deleteById(Integer.parseInt(personID));
                    JOptionPane.showMessageDialog(tabbedPane1, person_id_Text.getText() + "ID's Person is deleted");
                } else {
                    JOptionPane.showMessageDialog(tabbedPane1, person_id_Text.getText() + "ID's Person is not found");

                }
            }
        });
        select_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String personID = person_id_Text.getText();
                if (personRepository.existsById(Integer.parseInt(personID))) {
                    Person person = personRepository.findById(Integer.parseInt(personID)).get();
                    first_name_value.setText(person.getFirstName());
                    last_name_value.setText(person.getLastName());
                    age_value.setText(String.valueOf(person.getAge()));
                } else {
                    JOptionPane.showMessageDialog(tabbedPane1, person_id_Text.getText() + " ID's Person is not found");

                }
            }
        });
        update_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePerson(updateName,updateLastName,updateAge);
            }
        });

        JCheckBox[] jCheckBoxes = new JCheckBox[]{first_name_check, last_name_check, age_check};

        for (JCheckBox box : jCheckBoxes)
            box.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    Person person=getPerson(Integer.parseInt(update_person_id.getText()));
                    if ((!update_first_name_text.getText().isBlank() || !update_first_name_text.getText().isEmpty()) && (box.getText().equals("FirstName") && e.getStateChange() == ItemEvent.SELECTED))
                        updateName = update_first_name_text.getText();
                    else if (box.getText().equals("FirstName") && e.getStateChange() == ItemEvent.DESELECTED) {
                        updateName = person.getFirstName();
                    }
                    if ((!update_last_name_text.getText().isBlank() || !update_last_name_text.getText().isEmpty()) && (box.getText().equals("Lastname") && e.getStateChange() == ItemEvent.SELECTED))
                        updateLastName=update_last_name_text.getText();
                     else if (box.getText().equals("Lastname") && e.getStateChange() == ItemEvent.DESELECTED)
                        updateLastName =person.getLastName();

                    if ((!update_age_text.getText().isBlank() || !update_age_text.getText().isEmpty()) && (box.getText().equals("Age")
                            && e.getStateChange() == ItemEvent.SELECTED))
                        updateAge = Integer.parseInt(update_age_text.getText());
                     else if (box.getText().equals("Age") && e.getStateChange() == ItemEvent.DESELECTED)
                        updateAge = person.getAge();
                }

            });


    }

    private void createUIComponents() {
    }

    @Override
    public Person savePerson(PersonDto personDto) {
        Person person = Person.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .age(personDto.getAge())
                .build();
        return personRepository.save(person);
    }

    @Override
    public void updatePerson(String firstname,String lastname,int age) {

                Person person= getPerson(Integer.parseInt(update_person_id.getText()));
                       person.setFirstName(firstname);
                       person.setLastName(lastname);
                       person.setAge(age);
                System.out.println(person);
                personRepository.save(person);
    }

    @Override
    public Person getPerson(int id) {
        return personRepository.findById(id).get();
    }
}
