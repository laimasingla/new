package com.cg.ibs.cardmanagement.service;

import java.util.List;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.dao.BankDao;
import com.cg.ibs.cardmanagement.dao.CustomerDao;
import com.cg.ibs.cardmanagement.dao.DaoClass;

public class BankServiceClassImpl implements BankService {
	
	
	
	private BankDao bankDao;
	public BankServiceClassImpl(){
		bankDao=new DaoClass ();
	}

	CaseIdBean caseIdObj= new CaseIdBean();
	



	@Override
	public List<CaseIdBean> viewQueries() {
	
		List<CaseIdBean> caseBeans= bankDao.viewAllQueries();
		

		
		return bankDao.viewAllQueries();
		
		
		
	}






	




	






}