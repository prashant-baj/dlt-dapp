package com.example.settlements.services;


import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;

import com.example.contract.SettlementContract_1_0;
import com.example.settlements.model.SubmissionBlockTransaction;
import com.opencsv.CSVReader;

@Service
public class SettlementServiceImpl implements SettlementService {
	
	@Autowired
	QuorumService quorumService;
	
	//List<SubmissionBlockTransaction> submissions = new ArrayList<SubmissionBlockTransaction>();
	Map<String, SubmissionBlockTransaction> submissions = new HashMap<String, SubmissionBlockTransaction>();
	
	
	public SettlementServiceImpl() {
		quorumService = new QuorumServiceImpl();
		startObservable();
	}
	
	@Override
	public Collection<SubmissionBlockTransaction> getSubmissionsList() {
		loadSubmissionsList();
		return submissions.values();
	}
	
	
	void startObservable() {
		
		loadSubmissionsList();
		
	}
	
	void loadSubmissionsList() {
		
		SettlementContract_1_0 contract = quorumService.getContract("node1");
		DefaultBlockParameter startBlock =  DefaultBlockParameterName.EARLIEST;
		DefaultBlockParameter  endBlock = DefaultBlockParameterName.LATEST;
		
		contract.settlement_InitializedEventObservable(startBlock, endBlock).subscribe( log -> {
			
			
	        SubmissionBlockTransaction sb = new SubmissionBlockTransaction();
	        sb.setFileName((StringUtils.newStringUsAscii(log.fileNane)).trim());
	        /*
	        sb.setMerchantId((StringUtils.newStringUsAscii(log.merchantId)).trim());
	        sb.setTotalRecordsCount(log.recordCount.intValue());
	        sb.setTotalAmount(log.totalAmount.doubleValue());
			sb.setSubmissionDate(new Date(log.timestamp.longValue()));
			sb.setCurrentStatus((StringUtils.newStringUsAscii(log.currentStatus)).trim());
			*/
	        
	        try {
				Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String> t = 
						contract.getSubmission(quorumService.stringToBytes32(sb.getFileName())).send();
			
				submissions.remove(sb.getFileName());
				SubmissionBlockTransaction s = getSubmissionBlockTransaction(t);
				
				
				
				
				submissions.put(sb.getFileName(), s);
				
				
	        
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			
			System.out.println(submissions);
		
		});
	}
	
	
	@Override
	public SubmissionBlockTransaction initiateSettlement(String fileName) throws Exception {
		
		
		SettlementContract_1_0 contract = quorumService.getContract("node1");
		SubmissionBlockTransaction request = readDataLineByLine(fileName);
		SubmissionBlockTransaction response = null;
		String tx = null;
		System.out.println(request);
		
		System.out.println("Contract Valid " + contract.isValid());
		
		byte[] _fileName = stringToBytes32(request.getFileName());
		byte[] _mid = stringToBytes32(request.getMerchantId());
				
		if(contract.isValid()) {
			TransactionReceipt txReceipt = contract.initialSubmission(_fileName,_mid, new BigInteger(System.currentTimeMillis()+""), new BigInteger(request.getTotalRecordsCount()+""), BigDecimal.valueOf(request.getTotalAmount()*100).toBigInteger()).send();
			//tx = txReceipt.getTransactionHash();
			/*
			Event event1 = new Event("Settlement_Initialized", 
	                Arrays.<TypeReference<?>>asList(
	                		new TypeReference<Bytes32>() {}, 
	                		new TypeReference<Uint256>() {},
	                		new TypeReference<Uint256>() {},
	                		new TypeReference<Bytes32>() {},
	                		new TypeReference<Uint256>() {},
	                		new TypeReference<Bytes32>() {}
	                		));	
			
			EventValues values = contract.staticExtractEventParameters(event1, txReceipt.getLogs().get(0));
			
			//System.out.println(" NonIndexed value" + values.getNonIndexedValues().get(0));
			 List<Type> results = FunctionReturnDecoder.decode(
					 txReceipt.getLogs().get(0).getData(), event1.getNonIndexedParameters());
			System.out.println("Size = "+results.size());
			
			 Bytes32 b= (Bytes32)results.get(0);
			 System.out.println("NonIndexed value 1=== " + StringUtils.newStringUsAscii(b.getValue()));
			 //System.out.println("NonIndexed value 1=== "+ new BigInteger(results.get(1)+""));
			 System.out.println("NonIndexed value 2=== "+results.get(1));
			 System.out.println("NonIndexed value 3=== "+results.get(2));
			 */
			if(txReceipt != null){				
				
				Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String> t = contract.getSubmission(_fileName).send();
				response = getSubmissionBlockTransaction(t);
				System.out.println("************ "+response);
				
			}
								
		}
		
		
		
		return response;
	}
	
	private List<SubmissionBlockTransaction> getPseudoList() {
		
		
		
		
		List<SubmissionBlockTransaction> submissions = new ArrayList<SubmissionBlockTransaction>();
		
		submissions.add(new SubmissionBlockTransaction("Test", new Date() , 1000, 25000.90));
		submissions.add(new SubmissionBlockTransaction("Test2", new Date(), 1100, 25100.90));
		submissions.add(new SubmissionBlockTransaction("Test3", new Date(), 1200, 25200.90));
		
		submissions.add(new SubmissionBlockTransaction("Test", new Date() , 1000, 25000.90));
		submissions.add(new SubmissionBlockTransaction("Test2", new Date(), 1100, 25100.90));
		submissions.add(new SubmissionBlockTransaction("Test3", new Date(), 1200, 25200.90));
		
		submissions.add(new SubmissionBlockTransaction("Test", new Date() , 1000, 25000.90));
		submissions.add(new SubmissionBlockTransaction("Test2", new Date(), 1100, 25100.90));
		submissions.add(new SubmissionBlockTransaction("Test3", new Date(), 1200, 25200.90));
		
		submissions.add(new SubmissionBlockTransaction("Test", new Date() , 1000, 25000.90));
		submissions.add(new SubmissionBlockTransaction("Test2", new Date(), 1100, 25100.90));
		submissions.add(new SubmissionBlockTransaction("Test3", new Date(), 1200, 25200.90));
		
		return submissions;
		
	}
	
	private SubmissionBlockTransaction readDataLineByLine(String file) {

		SubmissionBlockTransaction sb = new SubmissionBlockTransaction();

		try {
			// C:\Users\DELL\OneDrive\blockchain\samples\dataOct-6-2018.csv
			// Create an object of filereader
			// class with CSV file as a parameter.
			FileReader filereader = new FileReader(file);

			// create csvReader object passing
			// file reader as a parameter
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;

			// we are going to read data line by line
			int counter = 0;
			double amount = 0;
			String fileName = null;
			String merchantId = null;

			while ((nextRecord = csvReader.readNext()) != null) {

				if (counter >= 1) {					

					int cells = nextRecord.length;

					for (int i = 0; i < cells; i++) {
						String cell = nextRecord[i];
						
						switch (i) {
						case 0:
							merchantId = cell;
							break;
							
						case 1:
							fileName = cell;
							break;

						case 2:
							
							break;
						case 3:
							
							break;
						case 4:
							
							break;
						case 5:
							
							break;
						case 6:
							amount = amount+ Double.parseDouble(cell);
							break;	
														
						default:
							break;
						}
							

					}				

				}
				counter++;
				
			}
			
			sb.setFileName(fileName);
			sb.setMerchantId(merchantId);
			sb.setTotalAmount(amount);
			sb.setTotalRecordsCount(counter-1);

			csvReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb;
	}
	
	public static byte[] stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return byteValueLen32;
    }
	
	
	public SubmissionBlockTransaction getSubmissionBlockTransaction(
			Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String> t) {
		
		SubmissionBlockTransaction s = new SubmissionBlockTransaction();
		
		//return (subm.fileName, subm.merchantId, subm.timestamp, subm.recordCount, subm.totalAmount, subm.currentStatus, subm.owner);
		
		if(t.getValue1() != null) {				
			s.setFileName(StringUtils.newStringUsAscii(t.getValue1()).trim());
		}
		if(t.getValue2() != null) {
			s.setMerchantId(StringUtils.newStringUsAscii(t.getValue2()).trim());
		}
		if(t.getValue3() != null) {
			s.setSubmissionDate(new Date(t.getValue3().longValue()));
		}
		if(t.getValue4() != null) {
			s.setTotalRecordsCount(t.getValue4().intValue());
		}
		if(t.getValue5() != null) {
			s.setTotalAmount(t.getValue5().doubleValue()/100);
		}
		if(t.getValue6() != null) {
			s.setCurrentStatus(StringUtils.newStringUsAscii(t.getValue6()).trim());
		}
		if(t.getValue7() != null) {
			s.setAccountKey(t.getValue7());
		}
		
		
		
		
		return s;
	}
	
	public void updateStatus(String fileName, String status, String node) throws Exception {
		
		
		
		SettlementContract_1_0 contract = quorumService.getContract(node);
		TransactionReceipt receipt = contract.updateStatus(quorumService.stringToBytes32(fileName), quorumService.stringToBytes32(status)).send();
		if(receipt != null){				
			
			Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String> t = contract.getSubmission(quorumService.stringToBytes32(fileName)).send();
			//response = ;
			System.out.println("************ "+getSubmissionBlockTransaction(t));
			
		}
		 
	}
	
	
	
	

}
