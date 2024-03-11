package com.example.springboot.cruddemo;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.springboot.cruddemo.swinggui.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(CruddemoApplication.class);

		builder.headless(false);
		SwingUtilities.invokeLater(() -> new CrudGUI().setVisible(true));

		ConfigurableApplicationContext context = builder.run(args);


	}

}
