package com.project.schoolmanagment;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolManagementApplication implements CommandLineRunner {


    private final UserRoleService userRoleService;

    public SchoolManagementApplication(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementApplication.class, args);


    }

}









