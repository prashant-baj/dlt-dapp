package com.example.settlements.services;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;
import org.web3j.tx.Contract;

import com.example.contract.SettlementContract_1_0;

@Service
public class QuorumServiceImpl implements QuorumService {

	
	
	@Override
	public SettlementContract_1_0 getContract(String node) {
		Quorum quorum = null;
		SettlementContract_1_0 contract = null;
		String nodePort = null;
		String address = null;
		String fromAddress = null;
		List<String> privateFor = null;
		String contractAddress = "0xd45E6ff73C4eF7795e139212DcAB5749191E9848";//"0x3Fd22DA4f6E47B6E00DA10Bd4BF7e29D60634F9C";//"0x5ecCc194A8386B2D738305e21D68Ca3A2B9D7649";//"0x4e3b50d446BC0c016fA90578ed3299a598C17b1D";//"0x821f6754DbCdCd00E23f617386E2009a79079244";//"0x02dc67Ac75Bd6Aeb1f557974C2c93dD66bb49eAe";//"0xbf2be8f3E82bE9695b0b9cc9Da1B0aC878B1Fb97";//"0xf17840f453D385FBCC8B71AF6CeC140F4a3D2714";//"0x2d82fB39a83F576bE0f4456EB894b13DfB821C72";//"0xf0cCc13C4ff98C03a81270d5B99E2cA25fF252a7";
		
		switch (node) {
		case "node1":
			nodePort = "22000";
			address = "0x7708d73f2e4ae344f28f4489a678d043bc1d81a8";
			fromAddress = "BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo=";
			privateFor = Arrays.asList(
					"8SjRHlUBe4hAmTk3KDeJ96RhN+s10xRrHDrxEi1O5W0=", 
					"QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc=");
			break;
		
		case "node2":
			nodePort = "22001";
			address = "0x08d074cd0a86fc7273d5cf0a0a210b5d4f76ffa4";
			fromAddress = "8SjRHlUBe4hAmTk3KDeJ96RhN+s10xRrHDrxEi1O5W0=";
			privateFor = Arrays.asList(
					"BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo=", 
					"QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc=");
			
			break;
		
		case "node3":
			
			nodePort = "22002";
			address = "0x82af55fb4cc8973b8b4b898fa27a11cb6e9a66af";
			fromAddress = "QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc=";
			privateFor = Arrays.asList(
				"BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo=", 
				"8SjRHlUBe4hAmTk3KDeJ96RhN+s10xRrHDrxEi1O5W0=");
			
			break;

		default:
			break;
		}
		String jpip = "http://52.23.220.35";
		String pbip = "http://13.233.78.97";
		quorum = Quorum.build(new HttpService(jpip+":"+nodePort));
		ClientTransactionManager transactionManager = 
				new ClientTransactionManager(
						quorum, 
						address, 
						fromAddress, 
						privateFor, 
						2, 10);
		
		
		contract = SettlementContract_1_0.load(
				contractAddress,
				quorum,transactionManager,
				new BigInteger("0"), 
				new BigInteger("4700000")
				);
		
		return contract;
	}
	
	public byte[] stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return byteValueLen32;
    }
	
	
	

}
