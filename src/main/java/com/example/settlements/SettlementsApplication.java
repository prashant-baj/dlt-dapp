package com.example.settlements;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.settlements.model.SubmissionBlockTransaction;
import com.example.settlements.services.SettlementService;
import com.example.settlements.services.VAMapperService;

@SpringBootApplication
@RestController
public class SettlementsApplication {
	
	@Autowired
	SettlementService settlementService;
	
	@Autowired
	VAMapperService vAMapperService;

	public static void main(String[] args) {
		SpringApplication.run(SettlementsApplication.class, args);
	}
	
	@GetMapping("/getSubmissions")
	public Collection<SubmissionBlockTransaction> getSubmissions(){
		System.out.println("/getSubmissions");
		return settlementService.getSubmissionsList();
	}
	
	@GetMapping("/initiateSettlement")
	public SubmissionBlockTransaction initiateSettlement(){	
		System.out.println("initiateSettlement Request received ");
		SubmissionBlockTransaction sb = null;
		String fileName = "C:\\Users\\DELL\\OneDrive\\blockchain\\samples\\dataOct-6-2018.csv";
		try {
			sb = settlementService.initiateSettlement(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb;
	}
	
	@GetMapping("/processSubmission")
	public SubmissionBlockTransaction processSettlement(){
		System.out.println("processSettlement Request received ");
		String fileName = "C:\\Users\\DELL\\OneDrive\\blockchain\\samples\\dataOct-6-2018.csv";
		System.out.println(SubmissionBlockTransaction.printHeader());
		Collection<SubmissionBlockTransaction> list = vAMapperService.mapSubmissionFile(fileName).values();
		System.out.println("Transactions Being Submitted - ");
		
		System.out.println(list);
		String _fileName = null;
		System.out.println("**********************");
		
		String key = null;
		Iterator<SubmissionBlockTransaction> itr =  list.iterator();
		while(itr.hasNext()) {
			SubmissionBlockTransaction transaction = itr.next();
			System.out.println("Submitting Transaction "+transaction);
			key = transaction.getFileName()+transaction.getVA();
			_fileName = transaction.getFileName();
			try {
				
				System.out.println(key);
				vAMapperService.submitVATransaction(transaction);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// update status
		 try {
			settlementService.updateStatus(_fileName, "File_Processed", "node2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	@GetMapping("/submissionDetails/{fileName}")
	public Collection<SubmissionBlockTransaction> getSubmissionDetails(@PathVariable("fileName") String fileName) {
		
		Collection<SubmissionBlockTransaction> submissionDetails = null;
		
		try {
			submissionDetails =  vAMapperService.getSubmissionVirtualAccounts(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return submissionDetails;
		
	}
	
	
	
	@GetMapping("/applyFees")
	public SubmissionBlockTransaction applyFees(){
		System.out.println("applyFees Request received ");
		String fileName = "C:\\Users\\DELL\\OneDrive\\blockchain\\samples\\dataOct-6-2018.csv";
		System.out.println(SubmissionBlockTransaction.printHeader());
		Collection<SubmissionBlockTransaction> list = vAMapperService.mapSubmissionFile(fileName).values();
		System.out.println("Transactions Being Submitted - ");
		
		System.out.println(list);
		String _fileName = null;
		System.out.println("**********************");
		
		//Calculate Fees
		//list = vAMapperService.calculateFees(list);
		
		String key = null;
		Iterator<SubmissionBlockTransaction> itr =  list.iterator();
		while(itr.hasNext()) {
			SubmissionBlockTransaction transaction = itr.next();
			System.out.println("Submitting Transaction "+transaction);
			key = transaction.getFileName()+transaction.getVA();
			_fileName = transaction.getFileName();
			try {
				
				System.out.println(key);
				vAMapperService.submitFeesTransaction(transaction);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// update status
		 try {
			settlementService.updateStatus(_fileName, "Fees_Applied", "node2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	@GetMapping("/initiateFunding")
	public SubmissionBlockTransaction initiateFunding(){
		System.out.println("initiateFunding Request received ");
		String fileName = "C:\\Users\\DELL\\OneDrive\\blockchain\\samples\\dataOct-6-2018.csv";
		System.out.println(SubmissionBlockTransaction.printHeader());
		Collection<SubmissionBlockTransaction> list = vAMapperService.mapSubmissionFile(fileName).values();
		System.out.println("Transactions Being Submitted - ");
		
		System.out.println(list);
		String _fileName = null;
		System.out.println("**********************");
		
		//Calculate Fees
		//list = vAMapperService.calculateFees(list);
		long currentTime = System.currentTimeMillis();
		String key = null;
		Iterator<SubmissionBlockTransaction> itr =  list.iterator();
		while(itr.hasNext()) {
			SubmissionBlockTransaction transaction = itr.next();
			System.out.println("Submitting Transaction "+transaction);
			key = transaction.getFileName()+transaction.getVA();
			_fileName = transaction.getFileName();
			try {
				
				System.out.println(key);
				vAMapperService.submitFundingTransaction(transaction,currentTime);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// update status
		 try {
			settlementService.updateStatus(_fileName, "Funding_Initiated", "node2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	@GetMapping("/confirmFunding")
	public SubmissionBlockTransaction confirmFunding(){
		
		System.out.println("confirmFunding Request received ");
		String fileName = "C:\\Users\\DELL\\OneDrive\\blockchain\\samples\\dataOct-6-2018.csv";
		System.out.println(SubmissionBlockTransaction.printHeader());
		Collection<SubmissionBlockTransaction> list = vAMapperService.mapSubmissionFile(fileName).values();
		System.out.println("Transactions Being Submitted - ");
		
		System.out.println(list);
		String _fileName = null;
		System.out.println("**********************");
		
		
		String key = null;
		Iterator<SubmissionBlockTransaction> itr =  list.iterator();
		while(itr.hasNext()) {
			SubmissionBlockTransaction transaction = itr.next();
			System.out.println("Submitting Transaction "+transaction);
			key = transaction.getFileName()+transaction.getVA();
			_fileName = transaction.getFileName();
			try {
				
				System.out.println(key);
				vAMapperService.submitConfirmFundingTransaction(transaction);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// update status
		 try {
			settlementService.updateStatus(_fileName, "Funding_Confirmed", "node3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
