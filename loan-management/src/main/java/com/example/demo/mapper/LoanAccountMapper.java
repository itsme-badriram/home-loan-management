package com.example.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entity.LoanAccount;

public class LoanAccountMapper implements RowMapper<LoanAccount>{

	@Override
	public LoanAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		LoanAccount loanAccount = new LoanAccount();
		loanAccount.setLoanID(rs.getInt("LoanAccountId"));
		loanAccount.setLoanAmount(rs.getDouble("TotalLoanAmount"));
		loanAccount.setSavingAccount(rs.getInt("SavingsAccountId"));
		loanAccount.setRate(rs.getDouble("InterestRate"));
		loanAccount.setTenure(rs.getInt("Time"));
		loanAccount.setStatus(rs.getString("Status"));
		
		return loanAccount;
	}
}
