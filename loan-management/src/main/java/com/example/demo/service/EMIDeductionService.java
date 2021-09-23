package com.example.demo.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoanAccount;
import com.example.demo.entity.LoanRepayment;
import com.example.demo.mapper.LoanAccountMapper;
import com.example.demo.mapper.LoanRepaymentMapper;


@Service
public class EMIDeductionService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	public Long getAmount() { 
		Long amt = (long) 2;
		return amt;
	}
	public int getLoanAccountId(int savingsAccountId) {
		String sql = "SELECT LoanAccountId FROM loanaccount WHERE SavingsAccountId = " + savingsAccountId;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public  int getSavingsAccountId(int loanAccountId) {
		String sql = "SELECT SavingsAccountId FROM loanaccount WHERE LoanAccountId = " + loanAccountId;
			
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public  int getCurrentBalance(int savingsAccountId) {
		String sql = "SELECT CurrentBalance FROM savingsaccount WHERE SavingsAccountId = " + savingsAccountId;
			
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<LoanRepayment> getLoanRepayment() {
		String sql = "SELECT * FROM loanrepayment WHERE status = 'Ongoing'";
		List<LoanRepayment>  loans =  jdbcTemplate.query(sql, new LoanRepaymentMapper());
		return loans;
	}
	
	public void updateSavingsAccount() {
		
		List<LoanRepayment>  loans =  getLoanRepayment();
		 
		for (LoanRepayment loan: loans) {
			LocalDate d2 = LocalDate.parse(java.time.LocalDate.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
			LocalDate d1 = LocalDate.parse(loan.getLoanDate().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
			Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
			long diffDays = diff.toDays();
			if (diffDays  == 30) {
				
				System.out.println(loan.getOutstandingAmount() + " ---  " + loan.getLoanAccountId() );
				calculateInterest(loan);

			}
				}
	}
	public double roundOff(double a) {
		double roundOff = Math.round(a*100)/100;
		return (roundOff);
	}
	public  void calculateInterest(LoanRepayment loan) {
		String sql = "SELECT * FROM loanaccount WHERE LoanAccountId = " + loan.getLoanAccountId();
		LoanAccount loanAccount = jdbcTemplate.queryForObject(sql, new LoanAccountMapper());
		Double principal = 0.0;
		System.out.println(loanAccount.getTenure());
		Double rate = loanAccount.getRate() / 1200;
		
		Double balanceOutstanding = loan.getOutstandingAmount();
		Double interest = 0.0;
		Double emi = loan.getEMIAmount();
		
		int savingsAccountId = getSavingsAccountId(loan.getLoanAccountId());
		int currentBalance = getCurrentBalance(savingsAccountId);
		String query1 = "UPDATE savingsaccount SET CurrentBalance =  " + (currentBalance - loan.getPrincipalAmount()) + " WHERE SavingsAccountId = " + savingsAccountId ;
		jdbcTemplate.update(query1); 
		
		balanceOutstanding =  roundOff(balanceOutstanding - loan.getPrincipalAmount());
		interest = roundOff( balanceOutstanding * rate);
		principal =  roundOff(emi - interest);
		System.out.println("Interest " + Math.ceil(interest));
		System.out.println("Rate " + rate);
		System.out.println("Emi " + emi); 
		System.out.println("Outstanding  " + balanceOutstanding); 
		if (balanceOutstanding <= 0) {
			String query = "UPDATE loanrepayment SET Status = 'Paid',PrincipalAmount =" + principal + ", InterestAmount =  " +interest +  ", OutstandingAmount = " +balanceOutstanding + ", LoanDate = '" + java.time.LocalDate.now().toString() + "' WHERE LoanAccountId = " + loan.getLoanAccountId();
			jdbcTemplate.update(query);  	
		}
		else {
			String query = "UPDATE loanrepayment SET PrincipalAmount =" + principal + ", InterestAmount =  " +interest +  ", OutstandingAmount = " +balanceOutstanding + ", LoanDate = '" + java.time.LocalDate.now().toString() + "' WHERE LoanAccountId = " + loan.getLoanAccountId();
			jdbcTemplate.update(query);  	
		}
		
			
	}
	
	public static Double calculatemi(LoanAccount emicalculator) {
		
		Double loan = emicalculator.getLoanAmount();
		int tenure = emicalculator.getTenure() * 12;
		Double rate = emicalculator.getRate() / 1200;
		double emi= (loan*rate*Math.pow(1+rate,tenure))/(Math.pow(1+rate,tenure)-1);
	    return Math.ceil(emi);
	}

	public void addLoanRepayment(double loanAmt, int tenure, double rate, int savingsId) {
		// TODO Auto-generated method stub

		int loanId = getLoanAccountId(savingsId);
		LoanAccount loan = new LoanAccount();
		loan.setLoanAmount(loanAmt);
		loan.setTenure(tenure);
		loan.setRate(rate);
		Double emi = calculatemi(loan);
		rate = loan.getRate() / 1200;
		Double principal = 0.0;
		Double interest = 0.0;
		Double balanceOutstanding = loan.getLoanAmount();
		interest = roundOff( balanceOutstanding * rate);
		principal =  roundOff(emi - interest);
		balanceOutstanding =  roundOff(balanceOutstanding - principal);;

		
		String query = "\r\n"
				+ "INSERT INTO `loanrepayment` (`LoanDate`, `EMIAmount`, `PrincipalAmount`, `InterestAmount`, `OutstandingAmount`, `Status`, `LoanAccountId`) "
				+ "VALUES ('" + java.time.LocalDate.now().toString() + "'," + emi +"," + principal +","+ interest + "," + loanAmt + ",'" + "Ongoing" + "',"+ loanId + ")";
		System.out.println(query);		
		jdbcTemplate.update(query);  
		
		
	}

	
}
		

		
