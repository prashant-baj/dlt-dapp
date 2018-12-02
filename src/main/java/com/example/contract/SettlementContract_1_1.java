package com.example.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes8;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class SettlementContract_1_1 extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506116e1806100606000396000f300608060405260043610610112576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630f33b62114610117578063223e63121461015c5780632fd949ca146101f65780633014fb851461020d5780633d41871714610341578063490ca953146103ab57806349b843fb146104315780634e83f7af146104885780636aab6bbd14610513578063756dd3a4146105bf578063838de6be146106725780638da5cb5b146107085780638da9b7721461075f578063c5c9c0a2146107ef578063c7be361e146108a2578063ce5b719014610960578063e7de7263146109d1578063e980a50414610a24578063eeac76c914610a93578063f6ba8b1214610b17575b600080fd5b34801561012357600080fd5b506101466004803603810190808035600019169060200190929190505050610b6e565b6040518082815260200191505060405180910390f35b34801561016857600080fd5b506101c960048036038101908080356000191690602001909291908035600019169060200190929190803560001916906020019092919080359060200190929190803590602001909291908035600019169060200190929190505050610b96565b60405180836000191660001916815260200182600019166000191681526020019250505060405180910390f35b34801561020257600080fd5b5061020b610c66565b005b34801561021957600080fd5b5061023c6004803603810190808035600019169060200190929190505050610cfb565b60405180896000191660001916815260200188600019166000191681526020018781526020018677ffffffffffffffffffffffffffffffffffffffffffffffff191677ffffffffffffffffffffffffffffffffffffffffffffffff191681526020018577ffffffffffffffffffffffffffffffffffffffffffffffff191677ffffffffffffffffffffffffffffffffffffffffffffffff191681526020018477ffffffffffffffffffffffffffffffffffffffffffffffff191677ffffffffffffffffffffffffffffffffffffffffffffffff191681526020018315151515815260200182600019166000191681526020019850505050505050505060405180910390f35b34801561034d57600080fd5b5061037e60048036038101908080356000191690602001909291908035600019169060200190929190505050610dbf565b60405180836000191660001916815260200182600019166000191681526020019250505060405180910390f35b3480156103b757600080fd5b506103da6004803603810190808035600019169060200190929190505050610e42565b60405180896000191660001916815260200188600019166000191681526020018781526020018681526020018581526020018481526020018381526020018281526020019850505050505050505060405180910390f35b34801561043d57600080fd5b5061046a600480360381019080803560001916906020019092919080359060200190929190505050610e8a565b60405180826000191660001916815260200191505060405180910390f35b34801561049457600080fd5b506104b76004803603810190808035600019169060200190929190505050610eba565b604051808860001916600019168152602001876000191660001916815260200186600019166000191681526020018581526020018481526020018315151515815260200182815260200197505050505050505060405180910390f35b34801561051f57600080fd5b5061056e60048036038101908080356000191690602001909291908035600019169060200190929190803590602001909291908035906020019092919080359060200190929190505050610f09565b60405180876000191660001916815260200186815260200185815260200184600019166000191681526020018381526020018260001916600019168152602001965050505050505060405180910390f35b3480156105cb57600080fd5b506105ee60048036038101908080356000191690602001909291905050506111b3565b604051808860001916600019168152602001876000191660001916815260200186815260200185815260200184815260200183600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200197505050505050505060405180910390f35b34801561067e57600080fd5b506106db600480360381019080803560001916906020019092919080356000191690602001909291908035600019169060200190929190803590602001909291908035906020019092919080359060200190929190505050611239565b60405180836000191660001916815260200182600019166000191681526020019250505060405180910390f35b34801561071457600080fd5b5061071d6112e8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561076b57600080fd5b5061077461130d565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156107b4578082015181840152602081019050610799565b50505050905090810190601f1680156107e15780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156107fb57600080fd5b5061081e6004803603810190808035600019169060200190929190505050611370565b604051808860001916600019168152602001876000191660001916815260200186815260200185815260200184815260200183600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200197505050505050505060405180910390f35b3480156108ae57600080fd5b50610933600480360381019080803560001916906020019092919080356000191690602001909291908035600019169060200190929190803590602001909291908035906020019092919080359060200190929190803590602001909291908035906020019092919080359060200190929190803590602001909291905050506113d2565b60405180836000191660001916815260200182600019166000191681526020019250505060405180910390f35b34801561096c57600080fd5b5061098f6004803603810190808035600019169060200190929190505050611513565b60405180866000191660001916815260200185600019166000191681526020018481526020018381526020018281526020019550505050505060405180910390f35b3480156109dd57600080fd5b50610a006004803603810190808035600019169060200190929190505050611549565b60405180848152602001838152602001828152602001935050505060405180910390f35b348015610a3057600080fd5b50610a536004803603810190808035600019169060200190929190505050611589565b6040518088815260200187815260200186815260200185815260200184815260200183815260200182815260200197505050505050505060405180910390f35b348015610a9f57600080fd5b50610ac2600480360381019080803560001916906020019092919050505061160f565b6040518087600019166000191681526020018660001916600019168152602001856000191660001916815260200184815260200183815260200182151515158152602001965050505050505060405180910390f35b348015610b2357600080fd5b50610b50600480360381019080803560001916906020019092919080359060200190929190505050611678565b60405180826000191660001916815260200191505060405180910390f35b6000600260008360001916600019168152602001908152602001600020805490509050919050565b6000806000600660008a60001916600019168152602001908152602001600020905086816000018160001916905550878160010181600019169055508381600201816000191690555085816003018190555084816004018190555060008160050160006101000a81548160ff0219169083151502179055508060010154600019168160000154600019167fe97ddb569fe69cf12f9623dac8bd769de28047dd658cb9ab7d4f9237964cef7460405160405180910390a3806000015481600101549250925050965096945050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610cc157600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60036020528060005260406000206000915090508060000154908060010154908060020154908060030160009054906101000a9004780100000000000000000000000000000000000000000000000002908060030160089054906101000a9004780100000000000000000000000000000000000000000000000002908060030160109054906101000a9004780100000000000000000000000000000000000000000000000002908060030160189054906101000a900460ff16908060040154905088565b60008060006001600086600019166000191681526020019081526020016000209050838160050181600019169055508060050154600019168160000154600019167f8a236d6dbcea6be18f44c553ab4381adf7b29c79716b6eb44f7124ec6088ff3160405160405180910390a38060000154816005015492509250509250929050565b60046020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154908060050154908060060154908060070154905088565b600260205281600052604060002081815481101515610ea557fe5b90600052602060002001600091509150505481565b60066020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154908060050160009054906101000a900460ff16908060060154905087565b60008060008060008060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610f6f57600080fd5b600160008d6000191660001916815260200190815260200160002090508b60001916816000015460001916141561103f578b600019167f38a426e437bbd79c4c3387b8d397c1dfe97627e48a94e86c236c00b0226d686760405160405180910390a26040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4475706c6963617465205375626d697373696f6e00000000000000000000000081525060200191505060405180910390fd5b8b8160000181600019169055508a8160010181600019169055508981600201819055508881600301819055508781600401819055507f536574746c656d656e745f496e697469616c697a6564000000000000000000008160050181600019169055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160060160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550806004015481600301548260000154600019167f150357a02fdd725cc279a04cb24b19d7282c29e925609813ad7cb6ef11a740658460010154856002015486600501546040518084600019166000191681526020018381526020018260001916600019168152602001935050505060405180910390a4806000015481600301548260040154836001015484600201548560050154965096509650965096509650509550955095509550955095565b600080600080600080600080600160008a6000191660001916815260200190815260200160002090508060000154816001015482600201548360030154846004015485600501548660060160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16975097509750975097509750975050919395979092949650565b6000806000600560008a60001916600019168152602001908152602001600020905087816000018160001916905550868160010181600019169055508581600201819055508481600301819055508381600401819055508060000154600019168160010154600019167fe6723f6a02b8f64272fb9c900c9b380cc20989e57e51a66cfb8e0fd26a87736c60405160405180910390a3806001015481600001549250925050965096945050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60608060405190810160405280602181526020017f48656c6c6f2066726f6d20536574746c656d656e7420436f6e7472616374202181526020017f2100000000000000000000000000000000000000000000000000000000000000815250905090565b60016020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154908060050154908060060160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905087565b600080600080600360008f600019166000191681526020019081526020016000209150600460008f6000191660001916815260200190815260200160002090508c8260000181600019169055508b8260010181600019169055508a8260020181905550898160020181905550888160030181905550878160040181905550868160050181905550858160060181905550848160070181905550600260008d600019166000191681526020019081526020016000208d90806001815401808255809150509060018203906000526020600020016000909192909190915090600019169055508160000154600019168260010154600019167f3e7f0ab3b659f2e22da275a900dd21e1a271763af79b531125adb5b508d6aa8060405160405180910390a3816001015482600001549350935050509a509a98505050505050505050565b60056020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154905085565b6000806000806005600086600019166000191681526020019081526020016000209050806002015481600301548260040154935093509350509193909250565b6000806000806000806000806000600360008b600019166000191681526020019081526020016000209150600460008b600019166000191681526020019081526020016000209050806002015481600301548260040154836005015484600601548560070154876002015498509850985098509850985098505050919395979092949650565b60008060008060008060006006600089600019166000191681526020019081526020016000209050806000015481600101548260020154836003015484600401548560050160009054906101000a900460ff169650965096509650965096505091939550919395565b6000600260008460001916600019168152602001908152602001600020828154811015156116a257fe5b90600052602060002001549050929150505600a165627a7a7230582064d5ffe4470fd97a87912a14fb97aa02c1fc1fdc304015b0a2306ccc357467e80029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected SettlementContract_1_1(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SettlementContract_1_1(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<Settlement_InitializedEventResponse> getSettlement_InitializedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Settlement_Initialized", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<Settlement_InitializedEventResponse> responses = new ArrayList<Settlement_InitializedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            Settlement_InitializedEventResponse typedResponse = new Settlement_InitializedEventResponse();
            typedResponse.fileNane = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.recordCount = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.totalAmount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.merchantId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.currentStatus = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Settlement_InitializedEventResponse> settlement_InitializedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Settlement_Initialized", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, Settlement_InitializedEventResponse>() {
            @Override
            public Settlement_InitializedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                Settlement_InitializedEventResponse typedResponse = new Settlement_InitializedEventResponse();
                typedResponse.fileNane = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.recordCount = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.totalAmount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.merchantId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.currentStatus = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public List<Virtual_Account_MappedEventResponse> getVirtual_Account_MappedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Virtual_Account_Mapped", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<Virtual_Account_MappedEventResponse> responses = new ArrayList<Virtual_Account_MappedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            Virtual_Account_MappedEventResponse typedResponse = new Virtual_Account_MappedEventResponse();
            typedResponse.fileName = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.virtualAccount = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Virtual_Account_MappedEventResponse> virtual_Account_MappedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Virtual_Account_Mapped", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, Virtual_Account_MappedEventResponse>() {
            @Override
            public Virtual_Account_MappedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                Virtual_Account_MappedEventResponse typedResponse = new Virtual_Account_MappedEventResponse();
                typedResponse.fileName = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.virtualAccount = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<Fees_AppliedEventResponse> getFees_AppliedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Fees_Applied", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<Fees_AppliedEventResponse> responses = new ArrayList<Fees_AppliedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            Fees_AppliedEventResponse typedResponse = new Fees_AppliedEventResponse();
            typedResponse.fileName = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.virtualAccount = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Fees_AppliedEventResponse> fees_AppliedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Fees_Applied", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, Fees_AppliedEventResponse>() {
            @Override
            public Fees_AppliedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                Fees_AppliedEventResponse typedResponse = new Fees_AppliedEventResponse();
                typedResponse.fileName = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.virtualAccount = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<Funding_InitializedEventResponse> getFunding_InitializedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Funding_Initialized", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<Funding_InitializedEventResponse> responses = new ArrayList<Funding_InitializedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            Funding_InitializedEventResponse typedResponse = new Funding_InitializedEventResponse();
            typedResponse.virtualAccount = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.fundingFileName = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Funding_InitializedEventResponse> funding_InitializedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Funding_Initialized", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, Funding_InitializedEventResponse>() {
            @Override
            public Funding_InitializedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                Funding_InitializedEventResponse typedResponse = new Funding_InitializedEventResponse();
                typedResponse.virtualAccount = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.fundingFileName = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<Status_UpdatedEventResponse> getStatus_UpdatedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Status_Updated", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<Status_UpdatedEventResponse> responses = new ArrayList<Status_UpdatedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            Status_UpdatedEventResponse typedResponse = new Status_UpdatedEventResponse();
            typedResponse.fileName = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.status = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Status_UpdatedEventResponse> status_UpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Status_Updated", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, Status_UpdatedEventResponse>() {
            @Override
            public Status_UpdatedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                Status_UpdatedEventResponse typedResponse = new Status_UpdatedEventResponse();
                typedResponse.fileName = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.status = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<Duplicate_SubmissionEventResponse> getDuplicate_SubmissionEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Duplicate_Submission", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<Duplicate_SubmissionEventResponse> responses = new ArrayList<Duplicate_SubmissionEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            Duplicate_SubmissionEventResponse typedResponse = new Duplicate_SubmissionEventResponse();
            typedResponse.fileName = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Duplicate_SubmissionEventResponse> duplicate_SubmissionEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Duplicate_Submission", 
                //Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, Duplicate_SubmissionEventResponse>() {
            @Override
            public Duplicate_SubmissionEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                Duplicate_SubmissionEventResponse typedResponse = new Duplicate_SubmissionEventResponse();
                typedResponse.fileName = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]>> submissionDetails(byte[] param0) {
        final Function function = new Function("submissionDetails", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bool>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteCall<Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]>>(
                new Callable<Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]>>() {
                    @Override
                    public Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue(), 
                                (byte[]) results.get(4).getValue(), 
                                (byte[]) results.get(5).getValue(), 
                                (Boolean) results.get(6).getValue(), 
                                (byte[]) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<Tuple8<byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> submissionBreakups(byte[] param0) {
        final Function function = new Function("submissionBreakups", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple8<byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple8<byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple8<byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple8<byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<byte[]> virtualAccounts(byte[] param0, BigInteger param1) {
        Function function = new Function("virtualAccounts", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger>> fundingdetails(byte[] param0) {
        final Function function = new Function("fundingdetails", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger>>(
                new Callable<Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger>>() {
                    @Override
                    public Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>> submissions(byte[] param0) {
        final Function function = new Function("submissions", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>>(
                new Callable<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>>() {
                    @Override
                    public Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (byte[]) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger>> allSubmissionFees(byte[] param0) {
        final Function function = new Function("allSubmissionFees", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public static RemoteCall<SettlementContract_1_1> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SettlementContract_1_1.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SettlementContract_1_1> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SettlementContract_1_1.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public RemoteCall<TransactionReceipt> initialSubmission(byte[] _fileName, byte[] _merchantId, BigInteger _timestamp, BigInteger _recourdCount, BigInteger _totalAmount) {
        Function function = new Function(
                "initialSubmission", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName), 
                new org.web3j.abi.datatypes.generated.Bytes32(_merchantId), 
                new org.web3j.abi.datatypes.generated.Uint256(_timestamp), 
                new org.web3j.abi.datatypes.generated.Uint256(_recourdCount), 
                new org.web3j.abi.datatypes.generated.Uint256(_totalAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>> getSubmission(byte[] _fileName) {
        final Function function = new Function("getSubmission", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>>(
                new Callable<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>>() {
                    @Override
                    public Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (byte[]) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getVirtualAccountsCount(byte[] _fileName) {
        Function function = new Function("getVirtualAccountsCount", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> getVirtualAccountNameByIndexInArray(byte[] _fileName, BigInteger _indexInArray) {
        Function function = new Function("getVirtualAccountNameByIndexInArray", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName), 
                new org.web3j.abi.datatypes.generated.Uint256(_indexInArray)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> getSubmissionDetails(byte[] key) {
        final Function function = new Function("getSubmissionDetails", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> mapVirtualAccounts(byte[] key, byte[] _virtualaccount, byte[] _fileName, BigInteger _timestamp, BigInteger _depositsRecordCount, BigInteger _depositsAmount, BigInteger _authReversalRecordCount, BigInteger _authReversalAmount, BigInteger _authOnlyRecordCount, BigInteger _totalAuthOnlyAmount) {
        Function function = new Function(
                "mapVirtualAccounts", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(key), 
                new org.web3j.abi.datatypes.generated.Bytes32(_virtualaccount), 
                new org.web3j.abi.datatypes.generated.Bytes32(_fileName), 
                new org.web3j.abi.datatypes.generated.Uint256(_timestamp), 
                new org.web3j.abi.datatypes.generated.Uint256(_depositsRecordCount), 
                new org.web3j.abi.datatypes.generated.Uint256(_depositsAmount), 
                new org.web3j.abi.datatypes.generated.Uint256(_authReversalRecordCount), 
                new org.web3j.abi.datatypes.generated.Uint256(_authReversalAmount), 
                new org.web3j.abi.datatypes.generated.Uint256(_authOnlyRecordCount), 
                new org.web3j.abi.datatypes.generated.Uint256(_totalAuthOnlyAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> applyFees(byte[] _key, byte[] _virtualaccount, byte[] _fileName, BigInteger _interchange, BigInteger _otherfees, BigInteger _adjustments) {
        Function function = new Function(
                "applyFees", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_key), 
                new org.web3j.abi.datatypes.generated.Bytes32(_virtualaccount), 
                new org.web3j.abi.datatypes.generated.Bytes32(_fileName), 
                new org.web3j.abi.datatypes.generated.Uint256(_interchange), 
                new org.web3j.abi.datatypes.generated.Uint256(_otherfees), 
                new org.web3j.abi.datatypes.generated.Uint256(_adjustments)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<BigInteger, BigInteger, BigInteger>> getFeesDetails(byte[] key) {
        final Function function = new Function("getFeesDetails", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> fundingInitiated(byte[] _fundingkey, byte[] _fundingFileName, byte[] _virtualAccount, BigInteger _amount, BigInteger _timestamp, byte[] _remittanceType) {
        Function function = new Function(
                "fundingInitiated", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fundingkey), 
                new org.web3j.abi.datatypes.generated.Bytes32(_fundingFileName), 
                new org.web3j.abi.datatypes.generated.Bytes32(_virtualAccount), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.web3j.abi.datatypes.generated.Uint256(_timestamp), 
                new org.web3j.abi.datatypes.generated.Bytes32(_remittanceType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean>> getFundingDetails(byte[] _fundingkey) {
        final Function function = new Function("getFundingDetails", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fundingkey)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean>>(
                new Callable<Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> updateStatus(byte[] _fileName, byte[] _status) {
        Function function = new Function(
                "updateStatus", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName), 
                new org.web3j.abi.datatypes.generated.Bytes32(_status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> terminateContract() {
        Function function = new Function(
                "terminateContract", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getHello() {
        Function function = new Function("getHello", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static SettlementContract_1_1 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SettlementContract_1_1(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SettlementContract_1_1 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SettlementContract_1_1(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class Settlement_InitializedEventResponse {
        public byte[] fileNane;

        public BigInteger recordCount;

        public BigInteger totalAmount;

        public byte[] merchantId;

        public BigInteger timestamp;

        public byte[] currentStatus;
    }

    public static class Virtual_Account_MappedEventResponse {
        public byte[] fileName;

        public byte[] virtualAccount;
    }

    public static class Fees_AppliedEventResponse {
        public byte[] fileName;

        public byte[] virtualAccount;
    }

    public static class Funding_InitializedEventResponse {
        public byte[] virtualAccount;

        public byte[] fundingFileName;
    }

    public static class Status_UpdatedEventResponse {
        public byte[] fileName;

        public byte[] status;
    }

    public static class Duplicate_SubmissionEventResponse {
        public byte[] fileName;
    }
}
