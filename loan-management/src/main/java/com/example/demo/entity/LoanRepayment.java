package com.example.demo.entity;

import java.sql.Date;

public class LoanRepayment {
 private int LoanAccountId;
 private Date LoanDate;
 private double EMIAmount;
 private double PrincipalAmount;
 private double InterestAmount;
 private double OutstandingAmount;
 private String Status;
 
public LoanRepayment() {
}
public LoanRepayment(int loanAccountId, Date loanDate, double eMIAmount, double principalAmount, double interestAmount,
		double outstandingAmount, String status) {
	super();
	LoanAccountId = loanAccountId;
	LoanDate = loanDate;
	EMIAmount = eMIAmount;
	PrincipalAmount = principalAmount;
	InterestAmount = interestAmount;
	OutstandingAmount = outstandingAmount;
	Status = status;
}
@Override
public String toString() {
	return "LoanRepayment [LoanAccountId=" + LoanAccountId + ", LoanDate=" + LoanDate + ", EMIAmount=" + EMIAmount
			+ ", PrincipalAmount=" + PrincipalAmount + ", InterestAmount=" + InterestAmount + ", OutstandingAmount="
			+ OutstandingAmount + ", Status=" + Status + "]";
}
public int getLoanAccountId() {
	return LoanAccountId;
}
public void setLoanAccountId(int loanAccountId) {
	LoanAccountId = loanAccountId;
}
public Date getLoanDate() {
	return LoanDate;
}
public void setLoanDate(Date loanDate) {
	LoanDate = loanDate;
}
public double getEMIAmount() {
	return EMIAmount;
}
public void setEMIAmount(double eMIAmount) {
	EMIAmount = eMIAmount;
}
public double getPrincipalAmount() {
	return PrincipalAmount;
}
public void setPrincipalAmount(double principalAmount) {
	PrincipalAmount = principalAmount;
}
public double getInterestAmount() {
	return InterestAmount;
}
public void setInterestAmount(double intrestAmount) {
	InterestAmount = intrestAmount;
}
public double getOutstandingAmount() {
	return OutstandingAmount;
}
public void setOutstandingAmount(double outstandingAmount) {
	OutstandingAmount = outstandingAmount;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
 
}
