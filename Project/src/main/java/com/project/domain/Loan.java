package com.project.domain;
import java.util.*;

public class Loan {
	
	String property_address;
	long loan_amt;
	int tenure;
	long monthly_salary;
	String image;
	
	Scanner sc =new Scanner(System.in);
	public String getProperty_address() {
		System.out.println("Enter your property address");
		property_address= sc.next();
		return property_address;
	}
	public void setProperty_address(String property_address) {
		this.property_address = property_address;
	}
	public long getLoan_amt() {
		System.out.println("Enter Loan amount");
		loan_amt=sc.nextLong();
		return loan_amt;
	}
	public void setLoan_amt() {
		System.out.println("The loan amount is"+this.loan_amt);
	}
	public int getTenure() {
		System.out.println("Enter Tenure ");
		tenure=sc.nextInt();
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public long getMonthly_salary() {
		System.out.println("Enter your Monthly Salary");
		monthly_salary =sc.nextLong();
		return monthly_salary;
	}
	public void setMonthly_salary(long monthly_salary) {
		this.monthly_salary = monthly_salary;
	}
	public String getImage() {
		System.out.println("Upload Your Image");
		image=sc.next();
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Loan(String property_address, long loan_amt, int tenure, long monthly_salary, String image) {
		super();
		this.property_address = property_address;
		this.loan_amt = loan_amt;
		this.tenure = tenure;
		this.monthly_salary = monthly_salary;
		this.image = image;
	}
	public Loan() {
		//System.out.println("Hello I am working above super");
		super();
		System.out.println("Hello I am working");
	}
	@Override
	public String toString() {
		return "Loan [property_address=" + property_address + ", loan_amt=" + loan_amt + ", tenure=" + tenure
				+ ", monthly_salary=" + monthly_salary + ", image=" + image + "]";
	}
	

}
