package com.cg.ibs.cardmanagement.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.CustomerBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;
import com.cg.ibs.cardmanagement.dao.BankDao;
import com.cg.ibs.cardmanagement.dao.CustomerDao;
import com.cg.ibs.cardmanagement.dao.DaoClass;

public class CustomerServiceImpl implements CustomerService {

	CustomerDao customerDao = new DaoClass();

	CaseIdBean caseIdObj = new CaseIdBean();
	CustomerBean customObj= new CustomerBean();
	CustomerService customService;
	String UCI="7894561239632587";
	
	Scanner scan = new Scanner(System.in);

	String caseIdGenOne = " ";
	String caseIdTotal = " ";
	static int caseIdGenTwo = 0;
	LocalDateTime timestamp;
	LocalDateTime fromDate;
	LocalDateTime toDate;

	String addToQueryTable(String caseIdGenOne) {
		caseIdTotal = caseIdGenOne + caseIdGenTwo;
		caseIdGenTwo++;
		return caseIdTotal;
	}

	public CustomerServiceImpl() {
		super();
	}

	

	@Override
	public void applyNewDebitCard(BigInteger accountNumber) {
		boolean check = customerDao.verifyAccountNumber(accountNumber);
		if (check) {

			caseIdGenOne = "ANDC";
			timestamp = LocalDateTime.now();

			caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
			caseIdObj.setCaseTimeStamp(timestamp);
			caseIdObj.setStatusOfQuery("Pending");
			caseIdObj.setUCI(UCI);
			caseIdObj.setDefineQuery(accountNumber.toString());
			customerDao.newDebitCard(caseIdObj, accountNumber);
		}

		else {
			System.out.println("Something wrong !! Try Again");

		}

	}

	@Override
	public void applyNewCreditCard() {
		caseIdGenOne = "ANCD";
		timestamp = LocalDateTime.now();

		caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
		caseIdObj.setCaseTimeStamp(timestamp);
		caseIdObj.setStatusOfQuery("Pending");
		caseIdObj.setUCI(caseIdObj.getUCI());

		customerDao.newCreditCard(caseIdObj);

	}

	

	

	@Override
	public void requestDebitCardLost(BigInteger debitCardNumber) {
		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);

		if (check) {
			 CaseIdBean caseIdObj=new   CaseIdBean();
			caseIdObj.setCardNumber(debitCardNumber);

			caseIdGenOne = "RDCL";

			timestamp = LocalDateTime.now();

			caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
			caseIdObj.setCaseTimeStamp(timestamp);
			caseIdObj.setStatusOfQuery("Pending");
			caseIdObj.setUCI(caseIdObj.getUCI());
			caseIdObj.setDefineQuery(debitCardNumber.toString());

			customerDao.requestDebitCardLost(caseIdObj, debitCardNumber);
		} else {
			System.out.println("INVALID DEBIT CARD NUMBER");

		}
	}

	@Override
	public void requestCreditCardLost(BigInteger creditCardNumber) {
		boolean check = customerDao.verifyCreditCardNumber(creditCardNumber);
		if (check) {
            CaseIdBean caseIdObj=new   CaseIdBean();
			caseIdObj.setCardNumber(creditCardNumber);

			caseIdGenOne = "RDCL";

			timestamp = LocalDateTime.now();

			caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
			caseIdObj.setCaseTimeStamp(timestamp);
			caseIdObj.setStatusOfQuery("Pending");
			caseIdObj.setUCI(caseIdObj.getUCI());
			caseIdObj.setDefineQuery(creditCardNumber.toString());

			customerDao.requestCreditCardLost(caseIdObj, creditCardNumber);
		} else {
			System.out.println("INVALID CREDIT CARD NUMBER");

		}
	}

	

	@Override
	public void requestCreditCardUpgrade(BigInteger creditCardNumber,int myChoice) {
	
		
		caseIdObj.setCardNumber(creditCardNumber);
		caseIdGenOne = "RCCU";
		timestamp = LocalDateTime.now();

		caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
		caseIdObj.setCaseTimeStamp(timestamp);
		caseIdObj.setStatusOfQuery("Pending");
		BigInteger acc=customerDao.getAccountNumber(creditCardNumber);
		//caseIdObj.setUCI(customObj.getUCI());
		String Uci=customerDao.getUci(acc);
		customerDao.setUci(acc, Uci);
		
		if (myChoice == 1) {
			caseIdObj.setDefineQuery("Upgrade to Gold");
			customerDao.requestCreditCardUpgrade(caseIdObj, creditCardNumber);
		} else if (myChoice == 2) {
			caseIdObj.setDefineQuery("Upgrade to Platinum");
			customerDao.requestCreditCardUpgrade(caseIdObj,creditCardNumber);
		} else {
			System.out.println("Choose a valid option");
		}

	}

	
	@Override
	public void raiseDebitMismatchTicket(BigInteger debitCardNumber, BigInteger transactionId) {
		// TODO Auto-generated method stub
	}

	public List<DebitCardBean> viewAllDebitCards() {

		return customerDao.viewAllDebitCards();
	}

	@Override
	public List<CreditCardBean> viewAllCreditCards() {

		return customerDao.viewAllCreditCards();

	}

 
	
    public boolean verifyDate(LocalDateTime fromDate2, LocalDateTime toDate2) {
		boolean checkDate = false;
		if (fromDate2.isBefore(toDate2)) {
			checkDate = true;
		}

		return checkDate;
	}
    
	@Override
	public List<CreditCardTransaction> showCreditCardStatement(Date fromDate, Date toDate,
			BigInteger creditCardNumber) {
		return null;
	}


	@Override
	public void requestDebitCardUpgrade(BigInteger debitCardNumber,int myChoice) {
		

		caseIdObj.setCardNumber(debitCardNumber);
		caseIdGenOne = "RDCU";
		timestamp = LocalDateTime.now();

		caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
		caseIdObj.setCaseTimeStamp(timestamp);
		caseIdObj.setStatusOfQuery("Pending");
		BigInteger acc=customerDao.getAccountNumber(debitCardNumber);
		//caseIdObj.setUCI(customObj.getUCI());
		String Uci=customerDao.getUci(acc);
		customerDao.setUci(acc, Uci);
		if (myChoice == 1) {
			caseIdObj.setDefineQuery("Upgrade to Gold");
			customerDao.requestDebitCardUpgrade(caseIdObj, debitCardNumber);
		} else if (myChoice == 2) {
			caseIdObj.setDefineQuery("Upgrade to Platinum");
			customerDao.requestDebitCardUpgrade(caseIdObj, debitCardNumber);
		} else {
			System.out.println("Choose a valid option");
		}

	}

	@Override
	public String verifyDebitcardType(BigInteger debitCardNumber) {
		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);
		if (check) {
			String type = customerDao.getdebitCardType(debitCardNumber);
			return type;
		} else {
			System.out.println("INVALID DEBIT CARD NUMBER");
			return null;
		}

	}
	public boolean verifyDebitCardNumber(BigInteger debitCardNumber) {

		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);
		return (check);
	}
	public boolean verifyCreditCardNumber(BigInteger creditCardNumber) {

		boolean check1 = customerDao.verifyCreditCardNumber(creditCardNumber);
		return (check1);
	}
	
	public boolean verifyDebitPin(int pin, BigInteger debitCardNumber) {
		if (pin == customerDao.getDebitCardPin(debitCardNumber)) {

			return true;
		} else {
			return false;
		}
	}
	
	public void resetDebitPin(BigInteger debitCardNumber, int newPin) {

		customerDao.setNewDebitPin(debitCardNumber, newPin);
		System.out.println("PIN UPDATED SUCCESSFULLY!");
	}
	
	
	
	public boolean verifyCreditPin(int pin, BigInteger creditCardNumber) {
		if (pin == customerDao.getCreditCardPin(creditCardNumber)) {

			return true;
		} else {
			return false;
		}
	}
	
	public void resetCreditPin(BigInteger creditCardNumber, int newPin) {

		customerDao.setNewCreditPin(creditCardNumber, newPin);
		System.out.println("PIN UPDATED SUCCESSFULLY!");
	}

	@Override
	public String verifyCreditCardType(BigInteger creditCardNumber) {
		
		boolean check = customerDao.verifyCreditCardNumber( creditCardNumber);
		System.out.println(check);
		if (check) {
			String type = customerDao.getCreditCardType( creditCardNumber);
			return type;
		} else {
			System.out.println("INVALID CREDIT CARD NUMBER");
			return null;
		}
	}

	@Override
	public List<DebitCardTransaction> getDebitTrns(int dys, BigInteger debitCardNumber) {
		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);
		if (check){
		    if(dys>=1) {
			    
		    	return customerDao.getDebitTrans(dys, debitCardNumber);
		    }
		    else {
		    	System.out.println("invlid dys formt");
		    	return null;
		    }
		
	    }
		else {
			System.out.println("debit crd does not exist");
		return null;
	   }
	}

	

	

	

	
}