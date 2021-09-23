package com.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.springframework.jdbc.core.support.SqlLobValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	 
	int savingId; 
	
	public String getEMail(String accountnumber ) {
		String sql = "SELECT EmailAddress from savingsaccount WHERE AccountNumber = " + accountnumber ;
		 String mail = jdbcTemplate.queryForObject(sql, String.class);
		return mail;
	}
	
	public int getSavingAccountId(String accountnumber ) {
		String sql = "SELECT SavingsAccountId from savingsaccount WHERE AccountNumber = " + accountnumber ;
		savingId = jdbcTemplate.queryForObject(sql, int.class);
		return savingId;
	}
	
	public Long getMonthlySalary(int savingaccountId ) {
		String sql = "SELECT MonthlySalary from savingsaccount WHERE SavingsAccountId = " + savingaccountId ;
		Long monthlysalary = jdbcTemplate.queryForObject(sql, Long.class);
		return monthlysalary;
	}


	public Long getLoanAmt(int savingaccountId) {
		String sql = "SELECT TotalLoanAmount from loanaccount WHERE SavingsAccountId = " + savingaccountId ;
		Long loanamt = jdbcTemplate.queryForObject(sql, Long.class);
		return loanamt;
		
	}
	public int totalRowCount() {
		String sql = "select count(*) from loanaccount"; 
		return jdbcTemplate. queryForObject(sql, Integer.class);
	}
	
	public void insertValues(int savingacctid,double totalloanamt,int tenure, String address, double monthly_salary)
	{	
		int r= totalRowCount();
		int val=r+1;
       String sql = "INSERT INTO `loanaccount` (`SavingsAccountId`, `TotalLoanAmount`, `InterestRate`,`Time`, `Status`, `Address`, `MonthlySalary`, `ImageId`) VALUES ("+savingacctid+","+totalloanamt+",'7',"+tenure+", 'Approved', '"+address+"', "+monthly_salary+", "+val+")" ;
       jdbcTemplate.update(sql);
       insertImage(val);
       System.out.println("Record inserted successfully"); 
       Map<String, Double> hm = new HashMap<String, Double>();
       hm.put("LoanAmount", totalloanamt);
       hm.put("Tenure", Double.valueOf(tenure));
       hm.put("InterestRate",Double.valueOf(7));
       ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8001/api/addLoanRepayment/" + totalloanamt + "/" + tenure + "/" + 7 + "/" + savingacctid , String.class);
	    String response = responseEntity.getBody();
       
	} 
	public void insertImage(int imgid) {
		 
		  try {
		   final File image = new File("C:\\Users\\Badri\\Pictures\\psd-vs-clean.png");
		   final InputStream imageIs = new FileInputStream(image);  
		   LobHandler lobHandler = new DefaultLobHandler(); 
		   jdbcTemplate.update(
		         "update loanaccount set Image= ? where ImageId = "+imgid+"",
		         new Object[] {
		           new SqlLobValue(imageIs, (int)image.length(), lobHandler),
		         },
		         new int[] {Types.BLOB});
		   
		   
		  } catch (DataAccessException e) {
		   System.out.println("DataAccessException " + e.getMessage());
		  } catch (FileNotFoundException e) {
		   System.out.println("DataAccessException " + e.getMessage());
		  }
	}
}


