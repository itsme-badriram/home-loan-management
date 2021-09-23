package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Loan;
import com.project.service.ProjectService;

@RestController
public class ProjectController {
	long monthlysalary;
	long loan_amt;
	int savingid,tenure;
	String address;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	void sendEmail(String accountno) {
		String mail= projectservice.getEMail(accountno);
		System.out.println(mail);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail);
        

        msg.setSubject("Approval of loan");
        msg.setText("The loan has been granted");

        javaMailSender.send(msg);
        
        System.out.println("Sending Email...");

      
    } 
	
	
	@Autowired
	ProjectService projectservice;
	
	// t is tenure, a is the property address, ta is the total loan amount
		@RequestMapping("api/insert/{accno}/{ta}/{t}/{a}")
		public String insert(@PathVariable String accno,@PathVariable double ta,@PathVariable int t,@PathVariable String a)
		{
			
			
				savingid= projectservice.getSavingAccountId(accno);
				monthlysalary = projectservice.getMonthlySalary(savingid);
				double maxloan = monthlysalary*50;
				tenure = t;
				address=a;
				if(ta <=maxloan)
				{
					System.out.println("Loan granted");
					sendEmail(accno);
					projectservice.insertValues(savingid,ta,t,a,monthlysalary);
					return"Loan granted and record inserted successfully";
				}
					else
					{
						return("Sorry loan cannot be granted. Maximum amount of loan that can be given is of"+maxloan+" Do you want to cancel your application or go with the maximum amt");		
					}
				
		
	            }
			 
		
		@RequestMapping("api/choice/{value}")
		public String choice (@PathVariable String value)
		{
			if((value.compareTo("cancel")==1)||(value.compareTo("Cancel")==1))
			{
				return ("Thank you for Choosing Us");
				
			}
			else if((value.compareTo("go")==1)||(value.compareTo("Go")==1))
			{
				
				monthlysalary = projectservice.getMonthlySalary(savingid);
				double maxloan = monthlysalary*50;
				projectservice.insertValues(savingid,maxloan,tenure,address,monthlysalary);
				return"Loan granted and record inserted successfully";
			}
		else
			return ("Sorry Please enter the correct value");
		}
			
	}