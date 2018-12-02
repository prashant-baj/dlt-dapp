package com.example.settlements.services;

import org.web3j.quorum.Quorum;
import org.web3j.tx.Contract;

import com.example.contract.SettlementContract_1_0;

public interface QuorumService {
	
	public SettlementContract_1_0 getContract(String node);
	public byte[] stringToBytes32(String string);

}
