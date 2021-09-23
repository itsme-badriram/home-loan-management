package com.example.demo.entity;




public class LoanAccount {
	
	
	
	private int LoanID;
	
	private int SavingAccount;
	
	private double LoanAmount;
	
	private double Rate;
	
	private int Tenure;
	
	public LoanAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String Status;
	
	
	
	public LoanAccount(int loanID, int savingAccount, double loanAmount, double rate, int tenure, String status) {
		super();
		LoanID = loanID;
		SavingAccount = savingAccount;
		LoanAmount = loanAmount;
		Rate = rate;
		Tenure = tenure;
		Status = status;
	}
	
	
	public int getLoanID() {
		return LoanID;
	}
	public void setLoanID(int loanID) {
		LoanID = loanID;
	}
	public int getSavingAccount() {
		return SavingAccount;
	}
	public void setSavingAccount(int savingAccount) {
		SavingAccount = savingAccount;
	}
	public double getLoanAmount() {
		return LoanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		LoanAmount = loanAmount;
	}
	public double getRate() {
		return Rate;
	}
	public void setRate(double rate) {
		Rate = rate;
	}
	public int getTenure() {
		return Tenure;
	}
	public void setTenure(int tenure) {
		Tenure = tenure;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	

}
