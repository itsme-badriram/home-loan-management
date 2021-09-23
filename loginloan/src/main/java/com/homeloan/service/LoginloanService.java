package com.homeloan.service;
import com.homeloan.entity.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class LoginloanService {

@Autowired
JdbcTemplate jdbcTemplate;	

public String Printmsg(LoginloanEntity lle) {
	
	try {
	String sql = "SELECT AccountNumber FROM users WHERE AccountNumber='" +lle.getAccountnumber()+ "' AND Password='" + lle.getPassword()+"'";
	//System.out.println(sql);
	String users = jdbcTemplate.queryForObject(sql,String.class);
	//System.out.println("INSIDE DATABASE"); 
	//if (users== null) {
		//return null;
	//} else {
		//System.out.println(users);
		return ( "LOGIN SUCCESFUL: " +users);
	//}
	}
	catch (Exception e)
	{
		//System.out.println(lle.getAccountnumber());
		return ("invalid credentials");
	}
	
		}

}
