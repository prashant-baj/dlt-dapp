package com.example.settlements.services;

import java.util.Collection;
import java.util.List;

import com.example.settlements.model.SubmissionBlockTransaction;


public interface SettlementService  {

	public Collection<SubmissionBlockTransaction>	getSubmissionsList(); 
	
	public SubmissionBlockTransaction initiateSettlement(String fileName) throws Exception;
	
	public void updateStatus(String fileName, String status, String node) throws Exception;
}
