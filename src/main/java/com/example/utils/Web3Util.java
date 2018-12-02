package com.example.utils;



import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Wallet;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.methods.response.QuorumNodeInfo;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import com.example.contract.SettlementContract_1_0;
import com.example.settlements.services.QuorumService;
import com.example.settlements.services.QuorumServiceImpl;



public class Web3Util {

	public static void main(String[] args) {

		//Web3Util.generateContractArtifacts();
		//Web3Util.getTransactionTime();
		//Web3Util.getVA();
		
			String string = "Prashant";
	        byte[] byteValue = string.getBytes();
	        byte[] byteValueLen32 = new byte[32];
	        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
	        System.out.println(byteValueLen32);
	        String s = org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8(byteValue);
	        System.out.println(s);
	        System.out.println(org.apache.tomcat.util.codec.binary.StringUtils.newStringUsAscii(s.getBytes()));
	    
		
	}

	
	public static void getTransactionTime() {

		String txHash = "0x8a142c1edf1b1da3019e8e964c3455d75072af24392a7d2ec1aa653aadd91651";

		Quorum quorum = Quorum.build(new HttpService("http://13.233.78.97:22001"));
		
		QuorumService contract= new QuorumServiceImpl();
		SettlementContract_1_0 c = contract.getContract("node1");
		
		EthGetTransactionReceipt txReceipt = null;

		try {
			txReceipt = quorum.ethGetTransactionReceipt(txHash).sendAsync().get();
			EthBlock block = quorum.ethGetBlockByHash(txReceipt.getResult().getBlockHash(), true).sendAsync().get();
			//QuorumNodeInfo nodeInfo = quorum.quorumNodeInfo().sendAsync().get();
			
			long timestamp = block.getResult().getTimestamp().longValue();
			System.out.println(block.getResult().getHash());
			System.out.println(txReceipt.getResult().getLogs().get(0).getTopics().get(0));
			System.out.println(c.VIRTUAL_ACCOUNT_MAPPED_EVENT.getName());
			
			//Web3Util.decodeEvent(txReceipt.getResult().getLogs().get(0));
			
			

		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
		quorum.shutdown();

	}
	
	static String Settlement_Initialized_Event() {		
		Event event = new Event("Settlement_Initialized", 
                Arrays.<TypeReference<?>>asList(
                		new TypeReference<Bytes32>() {}, 
                		new TypeReference<Uint256>() {},
                		new TypeReference<Uint256>() {},
                		new TypeReference<Bytes32>() {},
                		new TypeReference<Uint256>() {},
                		new TypeReference<Bytes32>() {}
                		));		
		return EventEncoder.encode(event);		
		
	}
	
	public static com.example.contract.SettlementContract_1_0.Settlement_InitializedEventResponse decodeEvent(Log log) {
    	System.out.println("OKKKKK");
    	
    	Event event = new Event("Settlement_Initialized", 
                Arrays.<TypeReference<?>>asList(
                		new TypeReference<Bytes32>() {}, 
                		new TypeReference<Uint256>() {},
                		new TypeReference<Uint256>() {},
                		new TypeReference<Bytes32>() {},
                		new TypeReference<Uint256>() {},
                		new TypeReference<Bytes32>() {}
                		));	
    	
        
    	List<Type> nonIndexedValues = FunctionReturnDecoder.decode(
                log.getData(), event.getNonIndexedParameters());
    	
    	System.out.println("nonIndexedValues "+ nonIndexedValues);
    	
    	EventValues eventValues = Contract.staticExtractEventParameters(event, log);
        
        System.out.println("Event Values "+ eventValues);
        com.example.contract.SettlementContract_1_0.Settlement_InitializedEventResponse typedResponse = 
        		new com.example.contract.SettlementContract_1_0.Settlement_InitializedEventResponse();
        typedResponse.fileNane = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.recordCount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.totalAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.merchantId = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        byte[] b = typedResponse.fileNane;
        System.out.println(new String(typedResponse.fileNane,StandardCharsets.UTF_8));
        System.out.println("Record Count: "+typedResponse.recordCount);
        System.out.println("Amount: "+typedResponse.totalAmount);
        System.out.println("MID: "+typedResponse.merchantId);
        System.out.println("Date: "+ new Date(typedResponse.timestamp.longValue()));
        
        return typedResponse;
    }
	
	public static void getVA(){
		
		Quorum quorum = Quorum.build(new HttpService("http://13.233.78.97:22001"));
		
		try {
			
			QuorumService quorumService = new QuorumServiceImpl();
			SettlementContract_1_0 contract = quorumService.getContract("node2");
			byte[] _fileName = quorumService.stringToBytes32("[B@505bc810");
			
			if(contract.isValid()) {
				BigInteger count = contract.getVirtualAccountsCount(_fileName).send();
				int vaCount = count.intValue();
				System.out.println("Total VAs - " + vaCount);
				//byte[] va = contract.getVirtualAccountNameByIndexInArray(_fileName, count).send();
				//System.out.println("Virtual Account - "+ new String(va));
			}else {
				System.out.println("Contract Invalid");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
