package com.blog.blog_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@SpringBootApplication
public class BlogProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogProjectApplication.class, args);
	}

    @Bean
    public CommandLineRunner logDatabaseInfo(Environment env, DataSource dataSource) {
        return args -> {
            System.out.println("=================================");
            System.out.println("DATABASE CONNECTION INFO:");
            System.out.println("URL: " + env.getProperty("spring.datasource.url"));
            System.out.println("Username: " + env.getProperty("spring.datasource.username"));
            System.out.println("Driver: " + env.getProperty("spring.datasource.driver-class-name"));
            System.out.println("DataSource class: " + dataSource.getClass().getName());
            System.out.println("=================================");
        };
    }

}
