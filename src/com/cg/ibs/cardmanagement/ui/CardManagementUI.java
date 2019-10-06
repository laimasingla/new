package com.cg.ibs.cardmanagement.ui;

import java.util.*;

import com.cg.ibs.cardmanagement.service.BankService;
import com.cg.ibs.cardmanagement.service.BankServiceClassImpl;
import com.cg.ibs.cardmanagement.service.CustomerService;
import com.cg.ibs.cardmanagement.service.CustomerServiceImpl;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CardManagementUI {

	private enum CardMenu {

		LIST_EXISTING_DEBIT_CARDS, LIST_EXISTING_CREDIT_CARDS, APPLY_NEW_DEBIT_CARD, APPLY_NEW_CREDIT_CARD, UPGRADE_EXISTING_DEBIT_CARD, UPGRADE_EXISTING_CREDIT_CARD, RESET_DEBIT_CARD_PIN, RESET_CREDIT_CARD_PIN, REPORT_DEBIT_CARD_LOST, REPORT_CREDIT_CARD_LOST, REQUEST_DEBIT_CARD_STATEMENT, REQUEST_CREDIT_CARD_STATEMENT, REPORT_DEBITCARD_STATEMENT_MISMATCH, REPORT_CREDITCARD_STATEMENT_MISMATCH, CUSTOMER_LOG_OUT;
	}

	enum BankMenu {
		LIST_QUERIES, REPLY_QUERIES, VIEW_DEBIT_CARD_STATEMENT, VIEW_CREDIT_CARD_STATEMENT, BANK_LOG_OUT;
	}

	public static void main(String args[]) throws Exception {
		BigInteger accountNumber;
		Scanner scan = new Scanner(System.in);
		CardManagementUI obj = new CardManagementUI();
		CustomerService customService = new CustomerServiceImpl();
		BankService bankService = new BankServiceClassImpl();
       while(true){
		System.out.println("Welcome to card management System");
		System.out.println("Enter 1 to login as a customer");
		System.out.println("Enter 2 to login as a bank admin");
		int userInput=0;
		try {
		 userInput = scan.nextInt();
		if (userInput == 1) {
			System.out.println("You are logged in as a customer");
			CardMenu choice = null;
			while (choice != CardMenu.CUSTOMER_LOG_OUT) {
				System.out.println("Menu");
				System.out.println("--------------------");
				System.out.println("Choice");
				System.out.println("--------------------");
				for (CardMenu mmenu : CardMenu.values()) {
					System.out.println(mmenu.ordinal() + "\t" + mmenu);
				}
				System.out.println("Choice");
				int ordinal = scan.nextInt();
				if (ordinal >= 0 && ordinal < 15) {
					choice = CardMenu.values()[ordinal];

					switch (choice) {

					case LIST_EXISTING_DEBIT_CARDS:
						try {
						List<DebitCardBean> debitCardBeans = customService.viewAllDebitCards();
						for (DebitCardBean debitCardBean : debitCardBeans) {
							System.out.println(debitCardBean.toString());
						}
						}
						catch (Exception e) {
							System.out.println("No existing debit cards");
						}
						break;

					case LIST_EXISTING_CREDIT_CARDS:
                         try {
						List<CreditCardBean> creditCardBeans = customService.viewAllCreditCards();
						for (CreditCardBean creditCardBean : creditCardBeans) {
							System.out.println(creditCardBean.toString());
									
						}}
                         catch (Exception e) {
                        	 System.out.println("No existing credit cards");
						}

						break;

					case APPLY_NEW_DEBIT_CARD:
						System.out.println("You are applying for a new Debit Card");
						System.out.println("Enter Account Number you want to apply debit card for :");
						accountNumber = scan.nextBigInteger();

						customService.applyNewDebitCard(accountNumber);
						break;
					case APPLY_NEW_CREDIT_CARD:

						System.out.println("You are applying for a new Credit Card");

						customService.applyNewCreditCard();

						break;
					case UPGRADE_EXISTING_DEBIT_CARD:
						System.out.println("Enter your Debit Card Number: ");
						BigInteger debitCardNumber;
						int myChoice;
						debitCardNumber = scan.nextBigInteger();
						String type = customService.verifyDebitcardType(debitCardNumber);
						System.out.println("Your debit card is:" + type);

						if (type.equals("Silver")) {
							System.out.println("Choose 1 to upgrade to Gold");
							System.out.println("Choose 2 to upgrade to Platinum");
							myChoice = scan.nextInt();
							customService.requestDebitCardUpgrade(debitCardNumber, myChoice);

						} else if (type.equals("Gold")) {
							System.out.println("Choose 2 to upgrade to Platinum");
							myChoice = scan.nextInt();
							customService.requestDebitCardUpgrade(debitCardNumber, myChoice);

						} else {
							System.out.println("You already have a Platinum Card");
						}

						break;
					case UPGRADE_EXISTING_CREDIT_CARD:
						
						System.out.println("Enter your Credit Card Number: ");
						BigInteger creditCardNumber;
						
						creditCardNumber = scan.nextBigInteger();
					    String typecredit = customService.verifyCreditCardType(creditCardNumber);
						 
						System.out.println("Your credit card is:" + typecredit);
                        System.out.println("typecredit" + typecredit);
						if (!typecredit.equals("Silver")) {
							System.out.println("Choose 1 to upgrade to Gold");
							System.out.println("Choose 2 to upgrade to Platinum");
							myChoice = scan.nextInt();
							customService.requestCreditCardUpgrade(	creditCardNumber, myChoice);

						} 
						if (typecredit.equals("Gold")) {
							System.out.println("Choose 2 to upgrade to Platinum");
							myChoice = scan.nextInt();
							customService.requestCreditCardUpgrade(	creditCardNumber, myChoice);

						} if(typecredit.equals("Platinum")) {
							System.out.println("You already have a Platinum Card");
						}

						break;
					case RESET_DEBIT_CARD_PIN:
						System.out.println("Enter your Debit Card Number: ");
						debitCardNumber = scan.nextBigInteger();
						boolean check = customService.verifyDebitCardNumber(debitCardNumber);
						if (check) {
							System.out.println("Enter your existing pin:");
							int pin = scan.nextInt();
							if (customService.verifyDebitPin(pin, debitCardNumber)) {
								System.out.println("Enter new pin");
								int newPin = scan.nextInt();
								customService.resetDebitPin(debitCardNumber, newPin);
							} else {
								System.out.println("Invalid pin entered");
							}

						} else {
							System.out.println("Invalid debit card number");
						}

						break;
					case RESET_CREDIT_CARD_PIN:
						System.out.println("Enter your Credit Card Number: ");
						 creditCardNumber = scan.nextBigInteger();
						boolean check1 = customService.verifyCreditCardNumber(creditCardNumber);
						if (check1) {
							System.out.println("Enter your existing pin:");
							int pin = scan.nextInt();
							if (customService.verifyCreditPin(pin, creditCardNumber)) {
								System.out.println("Enter new pin");
								int newPin = scan.nextInt();
								customService.resetCreditPin(creditCardNumber, newPin);
							} else {
								System.out.println("Invalid pin entered");
							}

						} else {
							System.out.println("Invalid credit card number");
						}
						break;
					case REPORT_DEBIT_CARD_LOST:
						System.out.println("Enter your Debit Card Number: ");

						debitCardNumber = scan.nextBigInteger();
						customService.requestDebitCardLost(debitCardNumber);
						break;
					case REPORT_CREDIT_CARD_LOST:
						System.out.println("Enter your Credit Card Number: ");

						creditCardNumber = scan.nextBigInteger();
						customService.requestCreditCardLost(creditCardNumber);
						break;
					case REQUEST_DEBIT_CARD_STATEMENT:
						
						  System.out.println("Enter your Debit Card Number: ");
						  debitCardNumber=scan.nextBigInteger(); 
						
						  
						  System.out.println("enter dys : ");
						  int dys = scan.nextInt();
						
						  try {
						  List<DebitCardTransaction> debitCardBeanTrns = customService.getDebitTrns(dys,debitCardNumber);
						  for (DebitCardTransaction debitCardTrns : debitCardBeanTrns) 
						        System.out.println(debitCardTrns.toString());
						  }
						  
						  catch(Exception e) {
							  System.out.println("invlid dt formt otr debit crd does not exist");
						  }
							
						  
						  
					
						  
						 
						break;
					case REQUEST_CREDIT_CARD_STATEMENT:
						break;
					case REPORT_DEBITCARD_STATEMENT_MISMATCH:
						break;
					case REPORT_CREDITCARD_STATEMENT_MISMATCH:
						break;
					case CUSTOMER_LOG_OUT:
						System.out.println("LOGGED OUT");
						break;
					}
				}
			}
		} else {
			if (userInput == 2) {

				System.out.println("You are logged in as a Bank Admin");
				BankMenu cchoice = null;
				while (cchoice != BankMenu.BANK_LOG_OUT) {
					System.out.println("Menu");
					System.out.println("--------------------");
					System.out.println("Choice");
					System.out.println("--------------------");
					for (BankMenu mmenu : BankMenu.values()) {
						System.out.println(mmenu.ordinal() + "\t" + mmenu);
					}
					System.out.println("Choice");
					int ordinal = scan.nextInt();
					if (ordinal >= 0 && ordinal < BankMenu.values().length) {
						cchoice = BankMenu.values()[ordinal];

						switch (cchoice) {

						case LIST_QUERIES:

							List<CaseIdBean> caseBeans = bankService.viewQueries();
						
							for (CaseIdBean caseId : caseBeans) {
								
								System.out.println(caseId.toString());
							}
							break;

						case REPLY_QUERIES:

							

							break;
						case VIEW_DEBIT_CARD_STATEMENT:
							
							break;
						case VIEW_CREDIT_CARD_STATEMENT:

							

							break;
						case BANK_LOG_OUT:
							System.out.println("LOGGED OUT");
							break;

						}
					}
				}
			} else {
				System.out.println("Invalid Option!!");

			}

		}
		
          }
		catch (Exception e) {
			System.out.println("Your choice is invalid");
			break;
		}
		}
}}