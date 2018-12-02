package com.example.settlements.model;

import java.util.Date;


public class SubmissionBlockTransaction {
	
	// this is a summary record for blockchain typically posted by processor (accept)

		
	String fileName;
	String merchantId;
	Date submissionDate;
	
	String VA;
	
	int totalRecordsCount = 0;
	
	int saleRecordsCount  = 0;
	int authOnlyRecordsCount  = 0;
	int returnRecordsCount = 0;
	
	double totalAmount = 0;
	double salesRecordsAmount = 0;
	double authOnlyRecordsAmount = 0;
	double returnRecordsAmount = 0;
	
	double interchange = 0;
	double otherFees = 0;
	double adjustments = 0;
	
	double fundingInitiatedAmount = 0;
	String fundingFileName;
	String remittanceType;	
	String fundingStatus;
	boolean fundingConfirmed;
	
	
	
	String accountKey; // blockchain account of merchant privateFor:
	String transactionHash;
	
	String currentStatus;

	
	
	
	public SubmissionBlockTransaction(String fileName, Date submissionDate, int totalRecordsCount, double totalAmount) {
		super();
		this.fileName = fileName;
		this.submissionDate = submissionDate;
		this.totalRecordsCount = totalRecordsCount;
		this.totalAmount = totalAmount;
	}

	public SubmissionBlockTransaction() {
		// TODO Auto-generated constructor stub
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getVA() {
		return VA;
	}

	public void setVA(String vA) {
		VA = vA;
	}

	public int getTotalRecordsCount() {
		return totalRecordsCount;
	}

	public void setTotalRecordsCount(int totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}

	public int getSaleRecordsCount() {
		return saleRecordsCount;
	}

	public void setSaleRecordsCount(int saleRecordsCount) {
		this.saleRecordsCount = saleRecordsCount;
	}

	public int getAuthOnlyRecordsCount() {
		return authOnlyRecordsCount;
	}

	public void setAuthOnlyRecordsCount(int authOnlyRecordsCount) {
		this.authOnlyRecordsCount = authOnlyRecordsCount;
	}

	public int getReturnRecordsCount() {
		return returnRecordsCount;
	}

	public void setReturnRecordsCount(int returnRecordsCount) {
		this.returnRecordsCount = returnRecordsCount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getSalesRecordsAmount() {
		return salesRecordsAmount;
	}

	public void setSalesRecordsAmount(double saledRecordsAmount) {
		this.salesRecordsAmount = saledRecordsAmount;
	}

	public double getAuthOnlyRecordsAmount() {
		return authOnlyRecordsAmount;
	}

	public void setAuthOnlyRecordsAmount(double authOnlyRecordsAmount) {
		this.authOnlyRecordsAmount = authOnlyRecordsAmount;
	}

	public double getReturnRecordsAmount() {
		return returnRecordsAmount;
	}

	public void setReturnRecordsAmount(double returnRecordsAmount) {
		this.returnRecordsAmount = returnRecordsAmount;
	}
	
	
	public double getInterchange() {
		return interchange;
	}

	public void setInterchange(double interchange) {
		this.interchange = interchange;
	}

	public double getOtherFees() {
		return otherFees;
	}

	public void setOtherFees(double otherFees) {
		this.otherFees = otherFees;
	}

	public double getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(double adjustments) {
		this.adjustments = adjustments;
	}

	public double getFundingInitiatedAmount() {
		return fundingInitiatedAmount;
	}

	public void setFundingInitiatedAmount(double fundingInitiatedAmount) {
		this.fundingInitiatedAmount = fundingInitiatedAmount;
	}

	public String getFundingFileName() {
		return fundingFileName;
	}

	public void setFundingFileName(String fundingFileName) {
		this.fundingFileName = fundingFileName;
	}

	public String getRemittanceType() {
		return remittanceType;
	}

	public void setRemittanceType(String remittanceType) {
		this.remittanceType = remittanceType;
	}

	public String getFundingStatus() {
		return fundingStatus;
	}

	public void setFundingStatus(String fundingStatus) {
		this.fundingStatus = fundingStatus;
	}
	

	public boolean isFundingConfirmed() {
		return fundingConfirmed;
	}

	public void setFundingConfirmed(boolean fundingConfirmed) {
		this.fundingConfirmed = fundingConfirmed;
	}

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
	
	public String getTransactionHash() {
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}

	
	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	@Override
	public String toString() {
StringBuffer sb = new StringBuffer();
		
		sb.append("\n")
		.append(getVA())
		.append("\t")
		
		
		.append(getFileName())
		.append("\t")
		
		
		.append(getSubmissionDate())
		.append("\t")
		
		
		.append(getTotalRecordsCount())
		.append("\t")
		
		
		.append(getTotalAmount())
		.append("\t")
		
		
		.append(getSaleRecordsCount())
		.append("\t")
		
		
		.append(getSalesRecordsAmount())
		.append("\t")
		
		
		.append(getAuthOnlyRecordsCount())
		.append("\t")
		
		
		.append(getAuthOnlyRecordsAmount())
		.append("\t")
		
		
		.append(getReturnRecordsCount())
		.append("\t")
		
		
		.append(getReturnRecordsAmount())
		.append("\t")
		
		/*
		 * 
	double interchange = 0;
	double otherFees = 0;
	double adjustments = 0;
	
	double fundingInitiatedAmount = 0;
	String fundingFileName;
	
	String fundingStatus;
		 */
		.append(getInterchange())
		.append("\t")
		
		.append(getOtherFees())
		.append("\t")
		
		.append(getAdjustments())
		.append("\t")
		
		.append(getFundingInitiatedAmount())
		.append("\t")
		
		.append(getFundingFileName())
		.append("\t")
		
		.append(getRemittanceType())
		.append("\t")
		
		.append(getFundingStatus())
		.append("\t")
		
		.append(getCurrentStatus())
		.append("\t")
		
		.append(getAccountKey())
		.append("\t")
		
		
		.append(getTransactionHash())
		.append("\t")	;
		
		return sb.toString();
	}
	
	public static String printHeader() {
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("\n")
		.append("VA :")
		.append("\t")
		
		
		.append("File Nane :")
		.append("\t")
		
		
		.append("Submission Date :")
		.append("\t")
		
		
		.append("Total Records Count :")
		.append("\t")
		
		
		.append("Total Amount :")
		.append("\t")
		
		
		.append("Total Sales Records :")
		.append("\t")
		
		
		.append("Total Sales Amount :")
		.append("\t")
		
		
		.append("Total Conveyed Count :")
		.append("\t\t")
		
		
		.append("Total Conveyed Amount")
		.append("\t")
		
		
		.append("Return Records :")
		.append("\t")
		
		
		.append("Return Records Amount :")
		.append("\t")
		
		
		.append("Interchange")
		.append("\t")
		
		.append("Other Fees")
		.append("\t")
		
		.append("Adjustments")
		.append("\t")
		
		.append("Funding Amount")
		.append("\t")
		
		.append("Funding File Name")
		.append("\t")
		
		.append("Remittance Type")
		.append("\t")
		
		.append("Funding Status")
		.append("\t")
		
		.append("Status :")
		.append("\t")
		
		
		.append("Node Account Key :")
		.append("\t")
		
		
		.append("Transaction Hash :")
		.append("\t")
			;
		
		return sb.toString();
	}
	
	

	
}
