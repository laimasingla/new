package com.cg.ibs.cardmanagement.cardbean;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class CaseIdBean {
	
	
	

	public CaseIdBean() {
		
	}

	private String caseIdTotal;

	@Override
	public String toString() {
		return "CaseIdBean [caseIdTotal=" + caseIdTotal + ", caseTimeStamp=" + caseTimeStamp + ", statusOfQuery="
				+ statusOfQuery + ", UCI=" + UCI + ", defineQuery=" + defineQuery
				+ ", cardNumber=" + cardNumber + "]";
	}

	private LocalDateTime caseTimeStamp;
	private String statusOfQuery;

	private String UCI ;
	private String defineQuery;
	private BigInteger cardNumber;
	

	public BigInteger getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(BigInteger cardNumber) {
		this.cardNumber = cardNumber;
	}

	

	public String getDefineQuery() {
		return defineQuery;
	}

	public void setDefineQuery(String defineQuery) {
		this.defineQuery = defineQuery;
	}

	

	public String getUCI() {
		return UCI;
	}

	public void setUCI(String uCI) {
		UCI = uCI;
	}

	public String getCaseIdTotal() {
		return caseIdTotal;
	}

	public void setCaseIdTotal(String caseIdTotal) {
		this.caseIdTotal = caseIdTotal;
	}

	public LocalDateTime getCaseTimeStamp() {
		return caseTimeStamp;
	}

	public void setCaseTimeStamp(LocalDateTime timestamp) {
		this.caseTimeStamp = timestamp;
	}

	public String getStatusOfQuery() {
		return statusOfQuery;
	}

	public void setStatusOfQuery(String statusOfQuery) {
		this.statusOfQuery = statusOfQuery;
	}

}