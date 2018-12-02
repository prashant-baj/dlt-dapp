package com.example.settlements.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.settlements.model.SubmissionBlockTransaction;



public interface VAMapperService {
	
	// Triggered on receipt of the file. 
	public Map<String, SubmissionBlockTransaction> mapSubmissionFile(String filePath);
	public SubmissionBlockTransaction submitVATransaction(SubmissionBlockTransaction transaction) throws Exception ;
	public Collection<SubmissionBlockTransaction> getSubmissionVirtualAccounts(String fileName) throws Exception;
	
	public Collection<SubmissionBlockTransaction> calculateFees(Collection<SubmissionBlockTransaction> list) ;
	public SubmissionBlockTransaction submitFeesTransaction(SubmissionBlockTransaction transaction) throws Exception ;
	
	public SubmissionBlockTransaction submitFundingTransaction(SubmissionBlockTransaction transaction, long currentTime) throws Exception ;
	public SubmissionBlockTransaction submitConfirmFundingTransaction(SubmissionBlockTransaction transaction) throws Exception;
}
