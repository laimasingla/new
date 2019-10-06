package com.cg.ibs.cardmanagement.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.CustomerBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;

public class DaoClass implements CustomerDao, BankDao {

	Boolean result;
	CaseIdBean caseIdObj = new CaseIdBean();
	DebitCardBean bean = new DebitCardBean();
	CreditCardBean bean1 = new CreditCardBean();
	CustomerBean bean2=new CustomerBean();

	private String caseIdTotal;
	private static Map<String, DebitCardTransaction> debitCardTransactionDetails = new HashMap<String, DebitCardTransaction>();
	private static Map<String, CreditCardTransaction> creditCardTransactionDetails = new HashMap<String, CreditCardTransaction>();
	private static Map<String, CaseIdBean> queryDetails = new HashMap<String, CaseIdBean>();
	private static Map<BigInteger, DebitCardBean> debitCardDetails = new HashMap<BigInteger, DebitCardBean>();
	private static Map<BigInteger, CreditCardBean> creditCardDetails = new HashMap<BigInteger, CreditCardBean>();
	private static Map<BigInteger, CustomerBean> customerDetails = new HashMap<BigInteger, CustomerBean>();

	static {

		DebitCardBean debit1 = new DebitCardBean(new BigInteger("1234567890"), new BigInteger("1234567891012131"), true,
				"Laima", 067, 1234, LocalDate.now(), "7894561239632587", "Platinum");
		DebitCardBean debit2 = new DebitCardBean(new BigInteger("1234547890"), new BigInteger("1234562391012131"), true,
				"Pragya", 057, 1034, LocalDate.now(), "7894561239632587", "Gold");
		DebitCardBean debit3 = new DebitCardBean(new BigInteger("2034567890"), new BigInteger("3234567891012131"), true,
				"Shubham", 167, 2234, LocalDate.now(), "7894561239632587", "Silver");

		debitCardDetails.put(debit1.getDebitCardNumber(), debit1);
		debitCardDetails.put(debit2.getDebitCardNumber(), debit2);
		debitCardDetails.put(debit3.getDebitCardNumber(), debit3);

		CreditCardBean credit1 = new CreditCardBean(new BigInteger("1234567891012132"), true, "Laima", 623, 9856,
				LocalDate.now(), "7894561239632587", " Silver", 200, new BigDecimal("1000000.00"), "gfdgfgfdgf");
		CreditCardBean credit2 = new CreditCardBean(new BigInteger("1234569891012132"), true, "Pragya", 683, 9056,
				LocalDate.now(), "7894561239632587", " Gold", 100, new BigDecimal("500000.00"), "gfdgfgfdgf");
		CreditCardBean credit3 = new CreditCardBean(new BigInteger("2234567891012132"), true, "Mohit", 223, 9256,
				LocalDate.now(), "7894561239632587", "Platinum ", 230, new BigDecimal("100000.00"), "gfdgfgfdgf");

		creditCardDetails.put(credit1.getCreditCardNumber(), credit1);
		creditCardDetails.put(credit2.getCreditCardNumber(), credit2);
		creditCardDetails.put(credit3.getCreditCardNumber(), credit3);

		CustomerBean cust1 = new CustomerBean(new BigInteger("1234567890"), "7894561239632587", "laima", "singla",
				"email.@gmail.com", "65231351", "42165431");
		CustomerBean cust2 = new CustomerBean(new BigInteger("9012345678"), "7894561239832587", "laima", "singla",
				"email.@gmail.com", "65233351", "49165431");
		customerDetails.put(cust1.getAccountNumber(), cust1);
		customerDetails.put(cust2.getAccountNumber(), cust2);

		// Transactions for Ajay Gold Debit Card
		DebitCardTransaction debittrans1 = new DebitCardTransaction("DEB101", "7894561239632587",
				new BigInteger("1234567890"), new BigInteger("1234567891012131"),
				LocalDateTime.of(2019, Month.OCTOBER, 04, 12, 54, 6), new BigDecimal("1563"), "Petrol", "Debit");
		DebitCardTransaction debittrans2 = new DebitCardTransaction("DEB102", "7894561239632587",
				new BigInteger("1234567890"), new BigInteger("1234567891012131"),
				LocalDateTime.of(2013, Month.JANUARY, 04, 12, 54, 6), new BigDecimal("20.45"), "Interest", "Credit");
		debitCardTransactionDetails.put(debittrans1.getTransactionId(), debittrans1);
		debitCardTransactionDetails.put(debittrans2.getTransactionId(), debittrans2);

		// Transactions for Ajay Platinum Credit Card
		CreditCardTransaction credittrans1 = new CreditCardTransaction("CRED101", "7894561239632587",
				new BigInteger("1234567891012132"), LocalDateTime.of(2013, Month.MARCH, 04, 12, 54, 6),
				new BigDecimal("5000"), "Shopping");
		CreditCardTransaction credittrans2 = new CreditCardTransaction("CRED102", "7894561239632587",
				new BigInteger("1234567891012132"), LocalDateTime.of(2018, Month.OCTOBER, 04, 12, 21, 6),
				new BigDecimal("5000"), "Movie");
		creditCardTransactionDetails.put(credittrans1.getTransactionid(), credittrans1);
		creditCardTransactionDetails.put(credittrans2.getTransactionid(), credittrans2);

	}

	public DaoClass() {
		super();
	}

	public DaoClass(CaseIdBean caseIdObj) {
		super();

		this.caseIdObj = caseIdObj;

	}

	public boolean verifyAccountNumber(BigInteger accountNumber) {

		result = customerDetails.containsKey(accountNumber);
		return result;
	}

	public boolean verifyDebitCardNumber(BigInteger debitCardNumber) {

		result = debitCardDetails.containsKey(debitCardNumber);
		
		return result;
	}

	public boolean verifyCreditCardNumber(BigInteger creditCardNumber) {

		result = creditCardDetails.containsKey(creditCardNumber);
		
		return result;
	}

	public BigInteger getAccountNumber(BigInteger debitCardNumber){
		BigInteger acc=debitCardDetails.get(debitCardNumber).getAccountNumber();
	return acc;
	}
	@Override
	public void newCreditCard(CaseIdBean caseIdObjId) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("New Credit Card applied successfully");

		List<CreditCardBean> creditCards = new ArrayList<>();

	}

	@Override
	public void newDebitCard(CaseIdBean caseIdObj, BigInteger accountNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("New Debit Card applied successfully");
	}

	@Override
	public List<CaseIdBean> viewAllQueries() {
		List<CaseIdBean> query = new ArrayList();

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {
			query.add(entry.getValue());
		}

		return query;

	}

	@Override
	public List<DebitCardBean> viewAllDebitCards() {
		List<DebitCardBean> debitCards = new ArrayList();

		for (Entry<BigInteger, DebitCardBean> entry : debitCardDetails.entrySet()) {
			debitCards.add(entry.getValue());
		}
		return debitCards;

	}

	public List<CreditCardBean> viewAllCreditCards() {
		List<CreditCardBean> creditCards = new ArrayList<>();

		for (Entry<BigInteger, CreditCardBean> entry : creditCardDetails.entrySet()) {
			creditCards.add(entry.getValue());
		}
		return creditCards;

	}

	public void requestDebitCardLost(CaseIdBean caseIdObj, BigInteger debitCardNumber) {
		CaseIdBean caseIdObj1= caseIdObj;
		System.out.println("cseobj"+caseIdObj1.toString());
		queryDetails.put(caseIdObj1.getCaseIdTotal(), caseIdObj1);
		for(Map.Entry<String, CaseIdBean> entry:queryDetails.entrySet()) {
			System.out.println("key :"+entry.getKey()+" vlue : "+entry.getValue().toString() );
			
			
		}
		System.out.println("TICKET RAISED SUCCESSFULLY");

	}

	public void requestCreditCardLost(CaseIdBean caseIdObj, BigInteger creditCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");

	}


	public String getdebitCardType(BigInteger debitCardNumber) {
		String type = debitCardDetails.get(debitCardNumber).getDebitCardType();
		return type;

	}

	public void requestDebitCardUpgrade(CaseIdBean caseIdObj, BigInteger debitCardNumber) {
		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");

	}
	@Override
	public void requestCreditCardUpgrade(CaseIdBean caseIdObj, BigInteger creditCardNumber) {
		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");

		
	}

	public int getDebitCardPin(BigInteger debitCardNumber) {
		int pin = debitCardDetails.get(debitCardNumber).getDebitCurrentPin();
		return pin;

	}

	public void setNewDebitPin(BigInteger debitCardNumber, int newPin) {
		bean = debitCardDetails.get(debitCardNumber);
		bean.setDebitCurrentPin(newPin);
		debitCardDetails.replace(debitCardNumber, bean);
	}

	public int getCreditCardPin(BigInteger creditCardNumber) {
		int pin = creditCardDetails.get(creditCardNumber).getCreditCurrentPin();
		return pin;

	}

	public void setNewCreditPin(BigInteger creditCardNumber, int newPin) {
		bean1 = creditCardDetails.get(creditCardNumber);
		bean1.setCreditCurrentPin(newPin);
		creditCardDetails.replace(creditCardNumber, bean1);
	}
	
	public String getUci(BigInteger accountNumber){
		String getUci=customerDetails.get(accountNumber).getUCI();
		return getUci;
	}
	
	public void setUci(BigInteger accountNumber, String Uci) {
		bean2 = customerDetails.get(accountNumber);
		bean2.setUCI(Uci);
		customerDetails.replace(accountNumber, bean2);
	}

	@Override
	public String getCreditCardType(BigInteger creditCardNumber) {
		String type = creditCardDetails.get(creditCardNumber).getCreditCardType();
		return type;
	}

	@Override
	public List<DebitCardTransaction> getDebitTrans(int dys, BigInteger debitCardNumber) {
		
			
		List<DebitCardTransaction> debitCardsList = new ArrayList();
        LocalDateTime dLocalDateTime = LocalDateTime.now().minusDays(dys);
		for (Entry<String, DebitCardTransaction> entry : debitCardTransactionDetails.entrySet()) {
			if(entry.getValue().getDate().isAfter(dLocalDateTime))
			 debitCardsList.add(entry.getValue());
		}
		
		return debitCardsList;
	}

	
	
	

}