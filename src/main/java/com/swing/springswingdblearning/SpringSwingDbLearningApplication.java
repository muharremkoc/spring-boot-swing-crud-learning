package com.swing.springswingdblearning;

import com.swing.springswingdblearning.swing.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringSwingDbLearningApplication implements CommandLineRunner {

    @Autowired
    GameManager gameManager;


    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringSwingDbLearningApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        gameManager.callPanel();

    }
}
