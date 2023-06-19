package com.project.schoolmanagment;


import com.project.schoolmanagment.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolManagementApplication implements CommandLineRunner {


    public  SchoolManagementApplication(UserRoleService userRoleService) {
    }


    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {

    }
}









