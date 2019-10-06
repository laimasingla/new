package com.cg.ibs.cardmanagement.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;

public interface BankDao {
	
 

List<CaseIdBean > viewAllQueries();
	  
String getUci(BigInteger accountNumber); 
	
	
	
}