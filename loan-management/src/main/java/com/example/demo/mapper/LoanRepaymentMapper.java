package com.example.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entity.LoanRepayment;

public class LoanRepaymentMapper implements RowMapper<LoanRepayment>{
	
	@Override
	public LoanRepayment mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		LoanRepayment loanRepayment = new LoanRepayment();
		loanRepayment.setLoanAccountId(rs.getInt("LoanAccountId"));
		loanRepayment.setLoanDate(rs.getDate("LoanDate"));
		loanRepayment.setEMIAmount(rs.getDouble("EMIAmount"));
		loanRepayment.setPrincipalAmount(rs.getDouble("PrincipalAmount"));
		loanRepayment.setInterestAmount(rs.getDouble("InterestAmount"));
		loanRepayment.setOutstandingAmount(rs.getDouble("OutstandingAmount"));
		loanRepayment.setStatus(rs.getString("Status"));
		
		return loanRepayment;
	}

}
