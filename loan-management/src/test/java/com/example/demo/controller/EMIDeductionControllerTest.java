package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.LoanRepayment;
import com.example.demo.service.EMIDeductionService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@WebMvcTest(EMIDeductionController.class)
public class EMIDeductionControllerTest {
	 @Autowired
	    private MockMvc mockMvc;
	 
	    
	    
	 @MockBean
	    private EMIDeductionService eService;

	    
	    @Test
	    public void testGetLoanAccountIdExample() throws Exception {
	    	
	    	 Mockito.when(eService.getLoanAccountId(107)).thenReturn(232);
	         mockMvc.perform(get("/api/getLoanAccountId/107")).andExpect(status().isOk());
	    	 
	    	
	    }
	    @Test
	    public void testGetLoanRepaymentExample() throws Exception {
	    	
	    	 List<LoanRepayment> loans = new ArrayList<>();
	    	 LoanRepayment loan = new LoanRepayment();
	    	 loan.setEMIAmount(581);
	    	 loan.setPrincipalAmount(290);
	    	 loan.setOutstandingAmount(50000);
	    	 loan.setLoanAccountId(232);
	    	 loans.add(loan);
	    	 Mockito.when(eService.getLoanRepayment()).thenReturn(loans);
	         mockMvc.perform(get("/api/getLoanRepayment")).andExpect(status().isOk())
	         .andExpect(jsonPath("$[0].loanAccountId", Matchers.equalTo(232)));;
	    	 
	    	
	    }
}
