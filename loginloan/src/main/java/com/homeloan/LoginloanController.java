package com.homeloan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.homeloan.entity.LoginloanEntity;
import com.homeloan.service.LoginloanService;

@RestController
public class LoginloanController {
	
@Autowired	
LoginloanService loginloanservice;

@GetMapping("/{acno}/{pass}")

public String Print(@PathVariable String acno, @PathVariable String pass) {
	LoginloanEntity lle = new LoginloanEntity();
	lle.setAccountnumber(acno);
	lle.setPassword(pass);
	System.out.println(lle.getAccountnumber());
	
	String name = loginloanservice.Printmsg(lle);
	return name;
	
	}	

}
