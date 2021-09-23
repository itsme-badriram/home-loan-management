package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.domain.Loan;
import com.project.service.ProjectService;



@SpringBootApplication
public class ProjectApplication {
	
	static long loan_amt1;
	static long mon_sal1;
	static String address;
	static int tenure;
	static String img;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		}

}
