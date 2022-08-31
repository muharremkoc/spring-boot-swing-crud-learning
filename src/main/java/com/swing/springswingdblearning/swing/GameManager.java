package com.swing.springswingdblearning.swing;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class GameManager {

    @Autowired
    ManagementUI managementUI;
    public  void callPanel(){

               managementUI.setVisible(true);
    }
}
