package com.homeloan.entity;

public class LoginloanEntity {
	
	private String accountnumber;
	private String password;
	
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginloanEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginloanEntity(String accountnumber, String password) {
		super();
		this.accountnumber = accountnumber;
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginloanEntity [accountnumber=" + accountnumber + ", password=" + password + "]";
	}
	

}
