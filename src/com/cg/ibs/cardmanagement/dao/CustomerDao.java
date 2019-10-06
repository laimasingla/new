package com.cg.ibs.cardmanagement.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;

public interface CustomerDao {
	
	void newDebitCard(CaseIdBean caseIdObj,BigInteger accountNumber);
	
	void newCreditCard(CaseIdBean caseIdObjId);
	
	List <DebitCardBean> viewAllDebitCards();
	void setUci(BigInteger accountNumber, String Uci);
	String getUci(BigInteger accountNumber);
	BigInteger getAccountNumber(BigInteger debitCardNumber);
	
	String getdebitCardType(BigInteger debitCardNumber);
	void requestDebitCardUpgrade(CaseIdBean caseIdObj, BigInteger debitCardNumber);


	List<CreditCardBean> viewAllCreditCards();

	void requestDebitCardLost(CaseIdBean caseIdObj, BigInteger debitCardNumber);

	void requestCreditCardLost(CaseIdBean caseIdObj, BigInteger creditCardNumber);
	
	boolean verifyAccountNumber(BigInteger accountNumber);

	boolean verifyDebitCardNumber(BigInteger debitCardNumber);
	boolean verifyCreditCardNumber(BigInteger creditCardNumber);
	void setNewDebitPin(BigInteger debitCardNumber, int newPin);
	int getDebitCardPin(BigInteger debitCardNumber);
	
	void setNewCreditPin(BigInteger creditCardNumber, int newPin);
	int getCreditCardPin(BigInteger creditCardNumber);

	void requestCreditCardUpgrade(CaseIdBean caseIdObj, BigInteger creditCardNumber);

	String getCreditCardType(BigInteger creditCardNumber);

	List<DebitCardTransaction> getDebitTrans(int dys, BigInteger debitCardNumber);
}