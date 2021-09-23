package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoanRepayment;
import com.example.demo.service.EMIDeductionService;


@RestController
public class EMIDeductionController { 
	@Autowired
	EMIDeductionService emiDeductionService;
	 
	@RequestMapping("/")
	public String print() {

		System.out.println("Hello World!");
		return "Hello World";
	}
	
	@RequestMapping("api/getLoanAccountId/{id}")
	public ResponseEntity<Integer> getLoanAccountId (@PathVariable int id) { 
		int res =  emiDeductionService.getLoanAccountId(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	 @RequestMapping("api/getLoanRepayment")
		public ResponseEntity<List<LoanRepayment>> getLoanRepayment() { 
			List<LoanRepayment> res =  emiDeductionService.getLoanRepayment();
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	
	 @RequestMapping("api/updateCurrentBalance")
	public List<LoanRepayment> updateCurrentBalance() { 
		
		emiDeductionService.updateSavingsAccount();
		System.out.println(emiDeductionService.getLoanRepayment());
		List<LoanRepayment> res =  emiDeductionService.getLoanRepayment();
		return res;
	}
	 @RequestMapping(value = "api/addLoanRepayment/{loanAmt}/{tenure}/{rate}/{savingsId}", 
			  produces = "application/json", 
			  method=RequestMethod.GET)
	 public String addLoanRepayment(@PathVariable double loanAmt, @PathVariable int tenure, @PathVariable double rate, @PathVariable int savingsId) {
		 emiDeductionService.addLoanRepayment(loanAmt, tenure, rate, savingsId);
		 return "Added Successfully";
		  
	 }
}
 