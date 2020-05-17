package com.svceindore.minor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class MinorProjectApplication {

	public static void main(String[] args) {
		File file = new File("project.properties");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
			System.out.println("created" +properties.getProperty("name"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(MinorProjectApplication.class, args);
	}

}
