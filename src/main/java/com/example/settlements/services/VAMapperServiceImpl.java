package com.example.settlements.services;

import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple7;

import com.example.contract.SettlementContract_1_0;
import com.example.settlements.model.SubmissionBlockTransaction;
import com.example.settlements.model.SubmissionRecord;
import com.opencsv.CSVReader;

@Service
public class VAMapperServiceImpl implements VAMapperService {

	@Autowired
	QuorumService quorumService;
	
	
	@Override
	public Map<String, SubmissionBlockTransaction> mapSubmissionFile(String filePath) {
		// TODO Auto-generated method stub
		Map<String, SubmissionBlockTransaction> blockTrnasactions = mapSubmissionsToVA(readDataLineByLine(filePath));
		return blockTrnasactions;
	}

	private String getSettlementCurrency(SubmissionRecord submission) {

		String stlCurrency = submission.getCurrency();

		if (submission.getBU().endsWith("3"))
			stlCurrency = "USD";

		return stlCurrency;

	}

	private Map<String, SubmissionBlockTransaction> mapSubmissionsToVA(List<SubmissionRecord> submissions) {

		
		Map<String, SubmissionBlockTransaction> blockTrnasactions = new HashMap<>();
		
		/*
		 * 	
		 *	2111972-GBU1-USD-USD-MC
			2111972-GBU1-USD-USD-VI
			2111972-GBU2-EUR-EUR-VI
			2111972-GBU2-EUR-EUR-MC
			2111972-GBU3-CAD-USD-MC
			2111972-GBU3-CAD-USD-VI
			2111972-GBU4-AUD-AUD-VI
			2111972-GBU4-AUD-AUD-MC

		 * 
		 */
		
		
		
		Iterator<SubmissionRecord> itr = submissions.iterator();
		int counter = 1;
		int size = submissions.size();
		
		while (itr.hasNext()) {
			
			SubmissionRecord s = (SubmissionRecord) itr.next();			
			StringBuffer va = new StringBuffer();
			va.append(s.getMid())
			.append("-")
			.append(s.getBU())
			.append("-")
			.append(s.getCurrency())
			.append("-")
			.append(getSettlementCurrency(s))
			.append("-")
			.append(s.getNetwork());
			
			s.setVA(va.toString());	
			
			// just mark the last record as "return" record
			if( counter == 20 || counter == 40 || counter == 60 || counter == 80 || counter == 100) {
				s.setAccepted(false);
			} else {
				s.setAccepted(true);
			}
			
			
			// populate the SubmissionBlockTransaction
			
			if (blockTrnasactions.get(s.getVA()) == null) {
				blockTrnasactions.put(s.getVA(), new SubmissionBlockTransaction());
				
			}
			
			
			SubmissionBlockTransaction sb = blockTrnasactions.get(s.getVA());
			
			
			if(sb.getVA() == null) sb.setVA(s.getVA());
			if(sb.getFileName() == null) sb.setFileName(s.getFile());
			if(sb.getSubmissionDate() == null) sb.setSubmissionDate(s.getDate());
			
			sb.setTotalRecordsCount(sb.getTotalRecordsCount()+1);
			sb.setTotalAmount(sb.getTotalAmount()+s.getAmount());
			if(s.isAccepted()) {
				if(s.getType().equals("S") ) {
					sb.setSaleRecordsCount(sb.getSaleRecordsCount()+1);
					sb.setSalesRecordsAmount(sb.getSalesRecordsAmount()+s.getAmount());
				}else if(s.getType().equals("A") ) {
						sb.setAuthOnlyRecordsCount(sb.getAuthOnlyRecordsCount()+1);
						sb.setAuthOnlyRecordsAmount(sb.getAuthOnlyRecordsAmount()+s.getAmount());
				} 
			}else {
				sb.setReturnRecordsCount(sb.getReturnRecordsCount()+1);
				sb.setReturnRecordsAmount(sb.getReturnRecordsAmount()+s.getAmount());
			}
			
			
			 counter++;
						
		}		
		
		return blockTrnasactions;
		
	}

	private List<SubmissionRecord> readDataLineByLine(String file) {

		ArrayList<SubmissionRecord> submissions = new ArrayList<>();

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

			while ((nextRecord = csvReader.readNext()) != null) {

				if (counter > 0) {

					SubmissionRecord submission = new SubmissionRecord();

					int cells = nextRecord.length;

					for (int i = 0; i < cells; i++) {
						String cell = nextRecord[i];
						switch (i) {
						case 0:
							submission.setMid(Integer.parseInt(cell));
							break;
						case 1:
							submission.setFile(cell);
							break;
						case 2:
							submission.setDate(new Date(cell));
							break;
						case 3:
							submission.setNetwork(cell);
							break;
						case 4:
							submission.setCurrency(cell);
							break;
						case 5:
							submission.setBU(cell);
							break;
						case 6:
							submission.setAmount(Double.parseDouble(cell));
							break;
						case 7:
							submission.setType(cell);
							break;
						default:
							break;
						}

					}

					submissions.add(submission);

				}
				counter++;
			}

			csvReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return submissions;
	}
	
	
	
	@Override
	public SubmissionBlockTransaction submitVATransaction(SubmissionBlockTransaction request) throws Exception {
		
		
		SettlementContract_1_0 contract = quorumService.getContract("node2");
		
		/*
		function mapVirtualAccounts (
        bytes32 key,
        bytes32 _virtualaccount,
        bytes32 _fileName,
        uint256 _timestamp,
        uint256 _depositsRecordCount,
        uint256 _depositsAmount,        
        uint256 _authReversalRecordCount,
        uint256 _authReversalAmount,
        uint256 _authOnlyRecordCount,
        uint256 _totalAuthOnlyAmount  
		
		*/
		
		
		SubmissionBlockTransaction response = null;
		String transactionHash = null;
		System.out.println(request);
		
		System.out.println("Contract Valid " + contract.isValid());
		byte[] key = quorumService.stringToBytes32(request.getFileName()+request.getVA());
		byte[] va = quorumService.stringToBytes32(request.getVA());
		byte[] _fileName = quorumService.stringToBytes32(request.getFileName());
		BigInteger _timestamp = new BigInteger(System.currentTimeMillis()+"");
		BigInteger _depositsRecordCount = new BigInteger(request.getSaleRecordsCount()+"");
		BigInteger _depositsAmount = new BigInteger(Math.round(request.getSalesRecordsAmount()*100)+"");        
		BigInteger _authReversalRecordCount = new BigInteger(request.getReturnRecordsCount()+"");
		BigInteger _authReversalAmount = new BigInteger(Math.round(request.getReturnRecordsAmount()*100)+"");
		BigInteger _authOnlyRecordCount = new BigInteger(request.getAuthOnlyRecordsCount()+"");
		BigInteger _totalAuthOnlyAmount = new BigInteger(Math.round(request.getAuthOnlyRecordsAmount()*100)+"");
				
		if(contract.isValid()) {
			TransactionReceipt txReceipt = contract.mapVirtualAccounts(key, va, _fileName, _timestamp, _depositsRecordCount, _depositsAmount, _authReversalRecordCount, _authReversalAmount, _authOnlyRecordCount, _totalAuthOnlyAmount).send();
			transactionHash = txReceipt.getTransactionHash();
			System.out.println("Tx - "+ transactionHash );
			if(txReceipt != null){
				Tuple7<BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger> t = contract.getSubmissionDetails(key).send();
				response = getSubmissionBlockTransaction(t,request);
				response.setTransactionHash(transactionHash);
				System.out.println("************ "+response);
				
			}
								
		}
		
		
		
		return response;
	}
	
	public SubmissionBlockTransaction getSubmissionBlockTransaction(
			Tuple7<BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger> t, SubmissionBlockTransaction request) {
		
		SubmissionBlockTransaction s = new SubmissionBlockTransaction();
		
		s.setFileName(request.getFileName());
		s.setVA(request.getVA());
		/*
		 * return (
            sb.depositsRecordCount, 
            sb.depositsAmount, 
            sb.authReversalRecordCount, 
            sb.authReversalAmount, 
            sb.authOnlyRecordCount, 
            sb.totalAuthOnlyAmount, 
            sd.timestamp)
		 * 
		 */
		
		if(t.getValue1() != null) {
			s.setSaleRecordsCount(t.getValue1().intValue());
		}
		if(t.getValue2() != null) {
			s.setSalesRecordsAmount(t.getValue2().doubleValue());
		}
		if(t.getValue3() != null) {
			s.setReturnRecordsCount(t.getValue3().intValue());
		}
		if(t.getValue4() != null) {
			s.setReturnRecordsAmount(t.getValue4().doubleValue());
		}
		if(t.getValue5() != null) {
			s.setAuthOnlyRecordsCount(t.getValue5().intValue());
		}
		if(t.getValue6() != null) {
			s.setAuthOnlyRecordsAmount(t.getValue6().doubleValue());
		}
		if(t.getValue7() != null) {
			s.setSubmissionDate(new Date(t.getValue7().longValue()));
		}	
		
		return s;
	}
	
	
	public Collection<SubmissionBlockTransaction> getSubmissionVirtualAccounts(String fileName) throws Exception{
		
		SettlementContract_1_0 contract = quorumService.getContract("node2");
		byte[] _fileName = quorumService.stringToBytes32(fileName);
		BigInteger count = contract.getVirtualAccountsCount(_fileName).send();
		Collection<SubmissionBlockTransaction> list = new ArrayList<SubmissionBlockTransaction>();
		
		int vaCount = count.intValue();
		
		System.out.println(fileName + " count =" + vaCount );
		String key = null;
		for (int i = 0; i < vaCount; i++) {
			BigInteger bi = new BigInteger(i+"");
			byte[] va = contract.getVirtualAccountNameByIndexInArray(_fileName, bi).send();
			System.out.println("Virtual Account at index "+ i +" - " + StringUtils.newStringUsAscii(va));
			key = fileName+StringUtils.newStringUsAscii(va).trim();
			System.out.println("key is - " + key);
			byte[] _key = quorumService.stringToBytes32(key);
			Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> t = contract.getSubmissionDetails(_key).send();
			/*
			sb.depositsRecordCount, 
            sb.depositsAmount, 
            sb.authReversalRecordCount, 
            sb.authReversalAmount, 
            sb.authOnlyRecordCount, 
            sb.totalAuthOnlyAmount, 
            sd.timestamp);
			 */
			if(t != null) {
				SubmissionBlockTransaction s = new SubmissionBlockTransaction();
				s.setFileName(fileName);
				s.setVA(StringUtils.newStringUsAscii(va).trim());
				s.setSaleRecordsCount(t.getValue1().intValue());
				s.setSalesRecordsAmount(t.getValue2().doubleValue()/100);
				s.setReturnRecordsCount(t.getValue3().intValue());
				s.setReturnRecordsAmount(t.getValue4().doubleValue()/100);
				s.setAuthOnlyRecordsCount(t.getValue5().intValue());
				s.setAuthOnlyRecordsAmount(t.getValue6().doubleValue()/100);
				s.setSubmissionDate(new Date(t.getValue7().longValue()));
				
				
				//byte[] _key = quorumService.stringToBytes32(s.getFileName()+s.getVA());
				Tuple3<BigInteger, BigInteger, BigInteger> t3 = contract.getFeesDetails(_key).send();
				
				s.setInterchange(t3.getValue1().doubleValue()/100);
				s.setOtherFees(t3.getValue2().doubleValue()/100);
				s.setAdjustments(t3.getValue3().doubleValue()/100);
				
				
				Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean> t6 = contract.getFundingDetails(_key).send();
				s.setFundingFileName(StringUtils.newStringUsAscii(t6.getValue2()).trim());				
				s.setRemittanceType(StringUtils.newStringUsAscii(t6.getValue3()).trim());
				s.setFundingInitiatedAmount(t6.getValue4().doubleValue()/100);
				s.setFundingConfirmed(t6.getValue6());
										
				list.add(s);
				
			}
			
			
		}
		
		System.out.println(list);
		
		return list;
	}
	
	public Collection<SubmissionBlockTransaction> calculateFees(Collection<SubmissionBlockTransaction> list) {
		
		return list;
	}
	
	public SubmissionBlockTransaction submitFeesTransaction(SubmissionBlockTransaction transaction) throws Exception {
		
		calculateFees(transaction);
		
		SettlementContract_1_0 contract = quorumService.getContract("node2");
		/*
		 * bytes32 _key, 
        bytes32 _virtualaccount, 
        bytes32 _fileName, 
        uint256 _interchange,
        uint256 _otherfees,
        uint256 _adjustments
		 */
		byte[] _key = quorumService.stringToBytes32(transaction.getFileName()+transaction.getVA());
		byte[] _virtualAccount = quorumService.stringToBytes32(transaction.getVA());
		byte[] _fileName = quorumService.stringToBytes32(transaction.getFileName());
		BigInteger _timestamp = new BigInteger(System.currentTimeMillis()+"");
		
		BigInteger _interchange = new BigInteger(Math.round(transaction.getInterchange()*100)+"");
		BigInteger _otherfees = new BigInteger(Math.round(transaction.getOtherFees()*100)+"");
		BigInteger _adjustments = new BigInteger(Math.round(transaction.getAdjustments()*100)+"");
		
		System.out.println("_interchange::"+_interchange);
		System.out.println("_otherfees::"+_otherfees);
		System.out.println("_adjustments::"+_adjustments);
				
		if(contract.isValid()) {
			TransactionReceipt txReceipt = contract.applyFees(_key, _virtualAccount, _fileName, _interchange, _otherfees, _adjustments).send();
			
			if(txReceipt != null){
				Tuple7<BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger> t = contract.getSubmissionDetails(_key).send();
				transaction = getSubmissionBlockTransaction(t,transaction);
				transaction.setTransactionHash(txReceipt.getTransactionHash());
				System.out.println("************ "+transaction);
				
			}
								
		}
		
		
		
		return transaction;
	}
	
	private SubmissionBlockTransaction calculateFees(SubmissionBlockTransaction transaction) {
		
		transaction.setInterchange(((transaction.getAuthOnlyRecordsAmount()+transaction.getSalesRecordsAmount()+transaction.getReturnRecordsAmount())*0.0025));
		transaction.setOtherFees(((transaction.getAuthOnlyRecordsAmount()+transaction.getSalesRecordsAmount()+transaction.getReturnRecordsAmount())*0.01));
		transaction.setAdjustments(((transaction.getAuthOnlyRecordsAmount()+transaction.getSalesRecordsAmount()+transaction.getReturnRecordsAmount())*0.005));
		
		// determine Payable
		double totalFees = transaction.getInterchange()+transaction.getOtherFees()+transaction.getAdjustments();
		double payable = transaction.getSalesRecordsAmount()-totalFees;
		transaction.setFundingInitiatedAmount(payable);
		
		return transaction;
	}
	
	
	public SubmissionBlockTransaction submitFundingTransaction(SubmissionBlockTransaction transaction, long currentTime) throws Exception {
		
		calculateFees(transaction);
		SettlementContract_1_0 contract = quorumService.getContract("node2");
		
		/*
		 * 	
		 *	2111972-GBU1-USD-USD-MC
			2111972-GBU1-USD-USD-VI
			2111972-GBU2-EUR-EUR-VI
			2111972-GBU2-EUR-EUR-MC
			2111972-GBU3-CAD-USD-MC
			2111972-GBU3-CAD-USD-VI
			2111972-GBU4-AUD-AUD-VI
			2111972-GBU4-AUD-AUD-MC

		 * 
		 */
		String va = transaction.getVA();
		
		switch (va) {
		case "2111972-GBU1-USD-USD-MC":
			transaction.setFundingFileName(currentTime+".ACH");
			transaction.setRemittanceType("ACH");
			break;			
		case "2111972-GBU1-USD-USD-VI":
			transaction.setFundingFileName(currentTime+".ACH");
			transaction.setRemittanceType("ACH");
			break;
		case "2111972-GBU2-EUR-EUR-VI":
			transaction.setFundingFileName(currentTime+".SEPA");
			transaction.setRemittanceType("SEPA");
			break;
		case "2111972-GBU2-EUR-EUR-MC":
			transaction.setFundingFileName(currentTime+".SEPA");
			transaction.setRemittanceType("SEPA");
			break;
		case "2111972-GBU3-CAD-USD-MC":
			transaction.setFundingFileName(currentTime+".WIRE");
			transaction.setRemittanceType("WIRE");
			break;
		case "2111972-GBU3-CAD-USD-VI":
			transaction.setFundingFileName(currentTime+".WIRE");
			transaction.setRemittanceType("WIRE");
			break;
		case "2111972-GBU4-AUD-AUD-VI":
			transaction.setFundingFileName(currentTime+".SWIFT");
			transaction.setRemittanceType("SWIFT");
			break;
		case "2111972-GBU4-AUD-AUD-MC":
			transaction.setFundingFileName(currentTime+".SWIFT");
			transaction.setRemittanceType("SWIFT");
			break;

		default:
			break;
		}
		
		/*
		bytes32 _fundingkey,
        bytes32 _fundingFileName,         
        bytes32 _virtualAccount, 
        uint256 _amount, 
        uint256 _timestamp, 
        bytes32 _remittanceType
		 */
		
		byte[] _key = quorumService.stringToBytes32(transaction.getFileName()+transaction.getVA());
		byte[] _fundingFileName = quorumService.stringToBytes32(transaction.getFundingFileName());
		byte[] _virtualAccount = quorumService.stringToBytes32(transaction.getVA());
		BigInteger _amount = new BigInteger(Math.round(transaction.getFundingInitiatedAmount()*100)+"");
		BigInteger _timestamp = new BigInteger(System.currentTimeMillis()+"");
		byte[] _remittanceType = quorumService.stringToBytes32(transaction.getRemittanceType());
		
		if(contract.isValid()) {
			TransactionReceipt txReceipt = contract.fundingInitiated(_key, _fundingFileName, _virtualAccount, _amount, _timestamp, _remittanceType).send();
			
			if(txReceipt != null){
				Tuple7<BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger> t = contract.getSubmissionDetails(_key).send();
				transaction = getSubmissionBlockTransaction(t,transaction);
				transaction.setTransactionHash(txReceipt.getTransactionHash());
				System.out.println("************ "+transaction);
				
			}
								
		}
		
		return transaction;
	}
	
	
	
public SubmissionBlockTransaction submitConfirmFundingTransaction(SubmissionBlockTransaction transaction) throws Exception {
		SettlementContract_1_0 contract = quorumService.getContract("node3");
		
		byte[] _key = quorumService.stringToBytes32(transaction.getFileName()+transaction.getVA());
		byte[] _virtualAccount = quorumService.stringToBytes32(transaction.getVA());
		
		if(contract.isValid()) {
			TransactionReceipt txReceipt = contract.confirmFunding(_key, _virtualAccount, true).send();
			
			if(txReceipt != null){
				Tuple7<BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger,BigInteger> t = contract.getSubmissionDetails(_key).send();
				transaction = getSubmissionBlockTransaction(t,transaction);
				transaction.setTransactionHash(txReceipt.getTransactionHash());
				System.out.println("************ "+transaction);
				
			}
								
		}
		
		
		
		return transaction;
	}
	
	
	
	

}
