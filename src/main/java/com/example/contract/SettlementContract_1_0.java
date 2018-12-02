package com.example.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
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
import org.web3j.tx.gas.ContractGasProvider;
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
public class SettlementContract_1_0 extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611c6d806100606000396000f300608060405260043610610128576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630f33b6211461012d578063223e6312146101725780632fd949ca146101e15780633014fb85146101f85780633d4187171461032c57806344b274e61461036b578063490ca953146103b657806349b843fb1461043c5780634e83f7af146104935780636aab6bbd1461051e578063756dd3a41461057b578063838de6be1461062e5780638da5cb5b146106995780638da9b772146106f0578063a71198bb14610780578063c5c9c0a2146107c9578063c7be361e1461087c578063ce5b71901461090f578063e7de726314610980578063e980a504146109d3578063eeac76c914610a42578063f6ba8b1214610ac6575b600080fd5b34801561013957600080fd5b5061015c6004803603810190808035600019169060200190929190505050610b1d565b6040518082815260200191505060405180910390f35b34801561017e57600080fd5b506101df60048036038101908080356000191690602001909291908035600019169060200190929190803560001916906020019092919080359060200190929190803590602001909291908035600019169060200190929190505050610b45565b005b3480156101ed57600080fd5b506101f6610d93565b005b34801561020457600080fd5b506102276004803603810190808035600019169060200190929190505050610e28565b60405180896000191660001916815260200188600019166000191681526020018781526020018677ffffffffffffffffffffffffffffffffffffffffffffffff191677ffffffffffffffffffffffffffffffffffffffffffffffff191681526020018577ffffffffffffffffffffffffffffffffffffffffffffffff191677ffffffffffffffffffffffffffffffffffffffffffffffff191681526020018477ffffffffffffffffffffffffffffffffffffffffffffffff191677ffffffffffffffffffffffffffffffffffffffffffffffff191681526020018315151515815260200182600019166000191681526020019850505050505050505060405180910390f35b34801561033857600080fd5b5061036960048036038101908080356000191690602001909291908035600019169060200190929190505050610eec565b005b34801561037757600080fd5b506103b460048036038101908080356000191690602001909291908035600019169060200190929190803515159060200190929190505050610f74565b005b3480156103c257600080fd5b506103e5600480360381019080803560001916906020019092919050505061100c565b60405180896000191660001916815260200188600019166000191681526020018781526020018681526020018581526020018481526020018381526020018281526020019850505050505050505060405180910390f35b34801561044857600080fd5b50610475600480360381019080803560001916906020019092919080359060200190929190505050611054565b60405180826000191660001916815260200191505060405180910390f35b34801561049f57600080fd5b506104c26004803603810190808035600019169060200190929190505050611084565b604051808860001916600019168152602001876000191660001916815260200186600019166000191681526020018581526020018481526020018315151515815260200182815260200197505050505050505060405180910390f35b34801561052a57600080fd5b50610579600480360381019080803560001916906020019092919080356000191690602001909291908035906020019092919080359060200190929190803590602001909291905050506110d3565b005b34801561058757600080fd5b506105aa6004803603810190808035600019169060200190929190505050611423565b604051808860001916600019168152602001876000191660001916815260200186815260200185815260200184815260200183600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200197505050505050505060405180910390f35b34801561063a57600080fd5b506106976004803603810190808035600019169060200190929190803560001916906020019092919080356000191690602001909291908035906020019092919080359060200190929190803590602001909291905050506114a9565b005b3480156106a557600080fd5b506106ae6116d6565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156106fc57600080fd5b506107056116fb565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561074557808201518184015260208101905061072a565b50505050905090810190601f1680156107725780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561078c57600080fd5b506107af600480360381019080803560001916906020019092919050505061175e565b604051808215151515815260200191505060405180910390f35b3480156107d557600080fd5b506107f8600480360381019080803560001916906020019092919050505061177e565b604051808860001916600019168152602001876000191660001916815260200186815260200185815260200184815260200183600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200197505050505050505060405180910390f35b34801561088857600080fd5b5061090d600480360381019080803560001916906020019092919080356000191690602001909291908035600019169060200190929190803590602001909291908035906020019092919080359060200190929190803590602001909291908035906020019092919080359060200190929190803590602001909291905050506117e0565b005b34801561091b57600080fd5b5061093e6004803603810190808035600019169060200190929190505050611a9f565b60405180866000191660001916815260200185600019166000191681526020018481526020018381526020018281526020019550505050505060405180910390f35b34801561098c57600080fd5b506109af6004803603810190808035600019169060200190929190505050611ad5565b60405180848152602001838152602001828152602001935050505060405180910390f35b3480156109df57600080fd5b50610a026004803603810190808035600019169060200190929190505050611b15565b6040518088815260200187815260200186815260200185815260200184815260200183815260200182815260200197505050505050505060405180910390f35b348015610a4e57600080fd5b50610a716004803603810190808035600019169060200190929190505050611b9b565b6040518087600019166000191681526020018660001916600019168152602001856000191660001916815260200184815260200183815260200182151515158152602001965050505050505060405180910390f35b348015610ad257600080fd5b50610aff600480360381019080803560001916906020019092919080359060200190929190505050611c04565b60405180826000191660001916815260200191505060405180910390f35b6000600260008360001916600019168152602001908152602001600020805490509050919050565b600060066000886000191660001916815260200190815260200160002090506002856040518082600019166000191681526020019150506020604051808303816000865af1158015610b9b573d6000803e3d6000fd5b5050506040513d6020811015610bb057600080fd5b810190808051906020019092919050505060001916600282600001546040518082600019166000191681526020019150506020604051808303816000865af1158015610c00573d6000803e3d6000fd5b5050506040513d6020811015610c1557600080fd5b8101908080519060200190929190505050600019161415610cdd577f38a426e437bbd79c4c3387b8d397c1dfe97627e48a94e86c236c00b0226d68678760405180826000191660001916815260200191505060405180910390a16040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4475706c6963617465205375626d697373696f6e00000000000000000000000081525060200191505060405180910390fd5b84816000018160001916905550858160010181600019169055508181600201816000191690555083816003018190555082816004018190555060008160050160006101000a81548160ff0219169083151502179055507fe97ddb569fe69cf12f9623dac8bd769de28047dd658cb9ab7d4f9237964cef748160000154826001015460405180836000191660001916815260200182600019166000191681526020019250505060405180910390a150505050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610dee57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60036020528060005260406000206000915090508060000154908060010154908060020154908060030160009054906101000a9004780100000000000000000000000000000000000000000000000002908060030160089054906101000a9004780100000000000000000000000000000000000000000000000002908060030160109054906101000a9004780100000000000000000000000000000000000000000000000002908060030160189054906101000a900460ff16908060040154905088565b60006001600084600019166000191681526020019081526020016000209050818160050181600019169055507f8a236d6dbcea6be18f44c553ab4381adf7b29c79716b6eb44f7124ec6088ff318160000154826005015460405180836000191660001916815260200182600019166000191681526020019250505060405180910390a1505050565b60006006600085600019166000191681526020019081526020016000209050818160050160006101000a81548160ff0219169083151502179055507fe97ddb569fe69cf12f9623dac8bd769de28047dd658cb9ab7d4f9237964cef748160000154826001015460405180836000191660001916815260200182600019166000191681526020019250505060405180910390a150505050565b60046020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154908060050154908060060154908060070154905088565b60026020528160005260406000208181548110151561106f57fe5b90600052602060002001600091509150505481565b60066020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154908060050160009054906101000a900460ff16908060060154905087565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561113057600080fd5b60016000876000191660001916815260200190815260200160002090506002866040518082600019166000191681526020019150506020604051808303816000865af1158015611184573d6000803e3d6000fd5b5050506040513d602081101561119957600080fd5b810190808051906020019092919050505060001916600282600001546040518082600019166000191681526020019150506020604051808303816000865af11580156111e9573d6000803e3d6000fd5b5050506040513d60208110156111fe57600080fd5b81019080805190602001909291905050506000191614156112c6577f38a426e437bbd79c4c3387b8d397c1dfe97627e48a94e86c236c00b0226d68678660405180826000191660001916815260200191505060405180910390a16040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4475706c6963617465205375626d697373696f6e00000000000000000000000081525060200191505060405180910390fd5b85816000018160001916905550848160010181600019169055508381600201819055508281600301819055508181600401819055507f536574746c656d656e745f496e697469616c697a6564000000000000000000008160050181600019169055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160060160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507f150357a02fdd725cc279a04cb24b19d7282c29e925609813ad7cb6ef11a7406581600001548260030154836004015484600101548560020154866005015460405180876000191660001916815260200186815260200185815260200184600019166000191681526020018381526020018260001916600019168152602001965050505050505060405180910390a1505050505050565b600080600080600080600080600160008a6000191660001916815260200190815260200160002090508060000154816001015482600201548360030154846004015485600501548660060160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16975097509750975097509750975050919395979092949650565b600060056000886000191660001916815260200190815260200160002090506002866040518082600019166000191681526020019150506020604051808303816000865af11580156114ff573d6000803e3d6000fd5b5050506040513d602081101561151457600080fd5b810190808051906020019092919050505060001916600282600001546040518082600019166000191681526020019150506020604051808303816000865af1158015611564573d6000803e3d6000fd5b5050506040513d602081101561157957600080fd5b8101908080519060200190929190505050600019161415611641577f38a426e437bbd79c4c3387b8d397c1dfe97627e48a94e86c236c00b0226d68678560405180826000191660001916815260200191505060405180910390a16040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4475706c6963617465205375626d697373696f6e00000000000000000000000081525060200191505060405180910390fd5b85816000018160001916905550848160010181600019169055508381600201819055508281600301819055508181600401819055507fe6723f6a02b8f64272fb9c900c9b380cc20989e57e51a66cfb8e0fd26a87736c8160010154826000015460405180836000191660001916815260200182600019166000191681526020019250505060405180910390a150505050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60608060405190810160405280602181526020017f48656c6c6f2066726f6d20536574746c656d656e7420436f6e7472616374202181526020017f2100000000000000000000000000000000000000000000000000000000000000815250905090565b60076020528060005260406000206000915054906101000a900460ff1681565b60016020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154908060050154908060060160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905087565b600080600360008d600019166000191681526020019081526020016000209150600460008d60001916600019168152602001908152602001600020905060028a6040518082600019166000191681526020019150506020604051808303816000865af1158015611854573d6000803e3d6000fd5b5050506040513d602081101561186957600080fd5b810190808051906020019092919050505060001916600283600101546040518082600019166000191681526020019150506020604051808303816000865af11580156118b9573d6000803e3d6000fd5b5050506040513d60208110156118ce57600080fd5b8101908080519060200190929190505050600019161415611996577f38a426e437bbd79c4c3387b8d397c1dfe97627e48a94e86c236c00b0226d68678a60405180826000191660001916815260200191505060405180910390a16040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4475706c6963617465205375626d697373696f6e00000000000000000000000081525060200191505060405180910390fd5b8a82600001816000191690555089826001018160001916905550888260020181905550878160020181905550868160030181905550858160040181905550848160050181905550838160060181905550828160070181905550600260008b600019166000191681526020019081526020016000208b90806001815401808255809150509060018203906000526020600020016000909192909190915090600019169055507f3e7f0ab3b659f2e22da275a900dd21e1a271763af79b531125adb5b508d6aa808260010154836000015460405180836000191660001916815260200182600019166000191681526020019250505060405180910390a1505050505050505050505050565b60056020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154905085565b6000806000806005600086600019166000191681526020019081526020016000209050806002015481600301548260040154935093509350509193909250565b6000806000806000806000806000600360008b600019166000191681526020019081526020016000209150600460008b600019166000191681526020019081526020016000209050806002015481600301548260040154836005015484600601548560070154876002015498509850985098509850985098505050919395979092949650565b60008060008060008060006006600089600019166000191681526020019081526020016000209050806000015481600101548260020154836003015484600401548560050160009054906101000a900460ff169650965096509650965096505091939550919395565b600060026000846000191660001916815260200190815260200160002082815481101515611c2e57fe5b90600052602060002001549050929150505600a165627a7a72305820136dc7168a12f5c00ad875d6eb26933e5e9f054557d422d0ea2141257095a9720029";

    public static final String FUNC_SUBMISSIONDETAILS = "submissionDetails";

    public static final String FUNC_SUBMISSIONBREAKUPS = "submissionBreakups";

    public static final String FUNC_VIRTUALACCOUNTS = "virtualAccounts";

    public static final String FUNC_FUNDINGDETAILS = "fundingdetails";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ISVAAVAILABLE = "isVAAvailable";

    public static final String FUNC_SUBMISSIONS = "submissions";

    public static final String FUNC_ALLSUBMISSIONFEES = "allSubmissionFees";

    public static final String FUNC_INITIALSUBMISSION = "initialSubmission";

    public static final String FUNC_GETSUBMISSION = "getSubmission";

    public static final String FUNC_GETVIRTUALACCOUNTSCOUNT = "getVirtualAccountsCount";

    public static final String FUNC_GETVIRTUALACCOUNTNAMEBYINDEXINARRAY = "getVirtualAccountNameByIndexInArray";

    public static final String FUNC_GETSUBMISSIONDETAILS = "getSubmissionDetails";

    public static final String FUNC_MAPVIRTUALACCOUNTS = "mapVirtualAccounts";

    public static final String FUNC_APPLYFEES = "applyFees";

    public static final String FUNC_GETFEESDETAILS = "getFeesDetails";

    public static final String FUNC_FUNDINGINITIATED = "fundingInitiated";

    public static final String FUNC_CONFIRMFUNDING = "confirmFunding";

    public static final String FUNC_GETFUNDINGDETAILS = "getFundingDetails";

    public static final String FUNC_UPDATESTATUS = "updateStatus";

    public static final String FUNC_TERMINATECONTRACT = "terminateContract";

    public static final String FUNC_GETHELLO = "getHello";

    public static final Event SETTLEMENT_INITIALIZED_EVENT = new Event("Settlement_Initialized", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event VIRTUAL_ACCOUNT_MAPPED_EVENT = new Event("Virtual_Account_Mapped", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event FEES_APPLIED_EVENT = new Event("Fees_Applied", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event FUNDING_INITIALIZED_EVENT = new Event("Funding_Initialized", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event FUNDING_CONFIRMED_EVENT = new Event("Funding_Confirmed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event STATUS_UPDATED_EVENT = new Event("Status_Updated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event DUPLICATE_SUBMISSION_EVENT = new Event("Duplicate_Submission", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("10", "0x3fd22da4f6e47b6e00da10bd4bf7e29d60634f9c");
    }

    @Deprecated
    protected SettlementContract_1_0(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

   

    @Deprecated
    protected SettlementContract_1_0(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SettlementContract_1_0(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]>> submissionDetails(byte[] param0) {
        final Function function = new Function(FUNC_SUBMISSIONDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bool>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteCall<Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]>>(
                new Callable<Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]>>() {
                    @Override
                    public Tuple8<byte[], byte[], BigInteger, byte[], byte[], byte[], Boolean, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
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
        final Function function = new Function(FUNC_SUBMISSIONBREAKUPS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple8<byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple8<byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple8<byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
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
        final Function function = new Function(FUNC_VIRTUALACCOUNTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger>> fundingdetails(byte[] param0) {
        final Function function = new Function(FUNC_FUNDINGDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger>>(
                new Callable<Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger>>() {
                    @Override
                    public Tuple7<byte[], byte[], byte[], BigInteger, BigInteger, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
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
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> isVAAvailable(byte[] param0) {
        final Function function = new Function(FUNC_ISVAAVAILABLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>> submissions(byte[] param0) {
        final Function function = new Function(FUNC_SUBMISSIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>>(
                new Callable<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>>() {
                    @Override
                    public Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
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
        final Function function = new Function(FUNC_ALLSUBMISSIONFEES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<byte[], byte[], BigInteger, BigInteger, BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

   

    @Deprecated
    public static RemoteCall<SettlementContract_1_0> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SettlementContract_1_0.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SettlementContract_1_0> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SettlementContract_1_0.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<Settlement_InitializedEventResponse> getSettlement_InitializedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SETTLEMENT_INITIALIZED_EVENT, transactionReceipt);
        ArrayList<Settlement_InitializedEventResponse> responses = new ArrayList<Settlement_InitializedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Settlement_InitializedEventResponse typedResponse = new Settlement_InitializedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileNane = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.recordCount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.totalAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.merchantId = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.currentStatus = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Settlement_InitializedEventResponse> settlement_InitializedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, Settlement_InitializedEventResponse>() {
            @Override
            public Settlement_InitializedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SETTLEMENT_INITIALIZED_EVENT, log);
                Settlement_InitializedEventResponse typedResponse = new Settlement_InitializedEventResponse();
                typedResponse.log = log;
                typedResponse.fileNane = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.recordCount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.totalAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.merchantId = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.currentStatus = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<Settlement_InitializedEventResponse> settlement_InitializedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETTLEMENT_INITIALIZED_EVENT));
        return settlement_InitializedEventObservable(filter);
    }

    public List<Virtual_Account_MappedEventResponse> getVirtual_Account_MappedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VIRTUAL_ACCOUNT_MAPPED_EVENT, transactionReceipt);
        ArrayList<Virtual_Account_MappedEventResponse> responses = new ArrayList<Virtual_Account_MappedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Virtual_Account_MappedEventResponse typedResponse = new Virtual_Account_MappedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileName = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.virtualAccount = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Virtual_Account_MappedEventResponse> virtual_Account_MappedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, Virtual_Account_MappedEventResponse>() {
            @Override
            public Virtual_Account_MappedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VIRTUAL_ACCOUNT_MAPPED_EVENT, log);
                Virtual_Account_MappedEventResponse typedResponse = new Virtual_Account_MappedEventResponse();
                typedResponse.log = log;
                typedResponse.fileName = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.virtualAccount = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<Virtual_Account_MappedEventResponse> virtual_Account_MappedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VIRTUAL_ACCOUNT_MAPPED_EVENT));
        return virtual_Account_MappedEventObservable(filter);
    }

    public List<Fees_AppliedEventResponse> getFees_AppliedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FEES_APPLIED_EVENT, transactionReceipt);
        ArrayList<Fees_AppliedEventResponse> responses = new ArrayList<Fees_AppliedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Fees_AppliedEventResponse typedResponse = new Fees_AppliedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileName = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.virtualAccount = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Fees_AppliedEventResponse> fees_AppliedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, Fees_AppliedEventResponse>() {
            @Override
            public Fees_AppliedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FEES_APPLIED_EVENT, log);
                Fees_AppliedEventResponse typedResponse = new Fees_AppliedEventResponse();
                typedResponse.log = log;
                typedResponse.fileName = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.virtualAccount = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<Fees_AppliedEventResponse> fees_AppliedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FEES_APPLIED_EVENT));
        return fees_AppliedEventObservable(filter);
    }

    public List<Funding_InitializedEventResponse> getFunding_InitializedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FUNDING_INITIALIZED_EVENT, transactionReceipt);
        ArrayList<Funding_InitializedEventResponse> responses = new ArrayList<Funding_InitializedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Funding_InitializedEventResponse typedResponse = new Funding_InitializedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.virtualAccount = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.fundingFileName = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Funding_InitializedEventResponse> funding_InitializedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, Funding_InitializedEventResponse>() {
            @Override
            public Funding_InitializedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FUNDING_INITIALIZED_EVENT, log);
                Funding_InitializedEventResponse typedResponse = new Funding_InitializedEventResponse();
                typedResponse.log = log;
                typedResponse.virtualAccount = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.fundingFileName = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<Funding_InitializedEventResponse> funding_InitializedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FUNDING_INITIALIZED_EVENT));
        return funding_InitializedEventObservable(filter);
    }

    public List<Funding_ConfirmedEventResponse> getFunding_ConfirmedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FUNDING_CONFIRMED_EVENT, transactionReceipt);
        ArrayList<Funding_ConfirmedEventResponse> responses = new ArrayList<Funding_ConfirmedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Funding_ConfirmedEventResponse typedResponse = new Funding_ConfirmedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.virtualAccount = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.fundingFileName = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Funding_ConfirmedEventResponse> funding_ConfirmedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, Funding_ConfirmedEventResponse>() {
            @Override
            public Funding_ConfirmedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FUNDING_CONFIRMED_EVENT, log);
                Funding_ConfirmedEventResponse typedResponse = new Funding_ConfirmedEventResponse();
                typedResponse.log = log;
                typedResponse.virtualAccount = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.fundingFileName = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<Funding_ConfirmedEventResponse> funding_ConfirmedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FUNDING_CONFIRMED_EVENT));
        return funding_ConfirmedEventObservable(filter);
    }

    public List<Status_UpdatedEventResponse> getStatus_UpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STATUS_UPDATED_EVENT, transactionReceipt);
        ArrayList<Status_UpdatedEventResponse> responses = new ArrayList<Status_UpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Status_UpdatedEventResponse typedResponse = new Status_UpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileName = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.status = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Status_UpdatedEventResponse> status_UpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, Status_UpdatedEventResponse>() {
            @Override
            public Status_UpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STATUS_UPDATED_EVENT, log);
                Status_UpdatedEventResponse typedResponse = new Status_UpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.fileName = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.status = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<Status_UpdatedEventResponse> status_UpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STATUS_UPDATED_EVENT));
        return status_UpdatedEventObservable(filter);
    }

    public List<Duplicate_SubmissionEventResponse> getDuplicate_SubmissionEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DUPLICATE_SUBMISSION_EVENT, transactionReceipt);
        ArrayList<Duplicate_SubmissionEventResponse> responses = new ArrayList<Duplicate_SubmissionEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Duplicate_SubmissionEventResponse typedResponse = new Duplicate_SubmissionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileName = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<Duplicate_SubmissionEventResponse> duplicate_SubmissionEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, Duplicate_SubmissionEventResponse>() {
            @Override
            public Duplicate_SubmissionEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DUPLICATE_SUBMISSION_EVENT, log);
                Duplicate_SubmissionEventResponse typedResponse = new Duplicate_SubmissionEventResponse();
                typedResponse.log = log;
                typedResponse.fileName = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<Duplicate_SubmissionEventResponse> duplicate_SubmissionEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DUPLICATE_SUBMISSION_EVENT));
        return duplicate_SubmissionEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> initialSubmission(byte[] _fileName, byte[] _merchantId, BigInteger _timestamp, BigInteger _recourdCount, BigInteger _totalAmount) {
        final Function function = new Function(
                FUNC_INITIALSUBMISSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName), 
                new org.web3j.abi.datatypes.generated.Bytes32(_merchantId), 
                new org.web3j.abi.datatypes.generated.Uint256(_timestamp), 
                new org.web3j.abi.datatypes.generated.Uint256(_recourdCount), 
                new org.web3j.abi.datatypes.generated.Uint256(_totalAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>> getSubmission(byte[] _fileName) {
        final Function function = new Function(FUNC_GETSUBMISSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>>(
                new Callable<Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String>>() {
                    @Override
                    public Tuple7<byte[], byte[], BigInteger, BigInteger, BigInteger, byte[], String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
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
        final Function function = new Function(FUNC_GETVIRTUALACCOUNTSCOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> getVirtualAccountNameByIndexInArray(byte[] _fileName, BigInteger _indexInArray) {
        final Function function = new Function(FUNC_GETVIRTUALACCOUNTNAMEBYINDEXINARRAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName), 
                new org.web3j.abi.datatypes.generated.Uint256(_indexInArray)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> getSubmissionDetails(byte[] key) {
        final Function function = new Function(FUNC_GETSUBMISSIONDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
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
        final Function function = new Function(
                FUNC_MAPVIRTUALACCOUNTS, 
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
        final Function function = new Function(
                FUNC_APPLYFEES, 
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
        final Function function = new Function(FUNC_GETFEESDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> fundingInitiated(byte[] _fundingkey, byte[] _fundingFileName, byte[] _virtualAccount, BigInteger _amount, BigInteger _timestamp, byte[] _remittanceType) {
        final Function function = new Function(
                FUNC_FUNDINGINITIATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fundingkey), 
                new org.web3j.abi.datatypes.generated.Bytes32(_fundingFileName), 
                new org.web3j.abi.datatypes.generated.Bytes32(_virtualAccount), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.web3j.abi.datatypes.generated.Uint256(_timestamp), 
                new org.web3j.abi.datatypes.generated.Bytes32(_remittanceType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> confirmFunding(byte[] _fundingkey, byte[] _virtualAccount, Boolean _fundingConfirmed) {
        final Function function = new Function(
                FUNC_CONFIRMFUNDING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fundingkey), 
                new org.web3j.abi.datatypes.generated.Bytes32(_virtualAccount), 
                new org.web3j.abi.datatypes.Bool(_fundingConfirmed)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean>> getFundingDetails(byte[] _fundingkey) {
        final Function function = new Function(FUNC_GETFUNDINGDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fundingkey)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean>>(
                new Callable<Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple6<byte[], byte[], byte[], BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
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
        final Function function = new Function(
                FUNC_UPDATESTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_fileName), 
                new org.web3j.abi.datatypes.generated.Bytes32(_status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> terminateContract() {
        final Function function = new Function(
                FUNC_TERMINATECONTRACT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getHello() {
        final Function function = new Function(FUNC_GETHELLO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static SettlementContract_1_0 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SettlementContract_1_0(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SettlementContract_1_0 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SettlementContract_1_0(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

  

    public static SettlementContract_1_0 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SettlementContract_1_0(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class Settlement_InitializedEventResponse {
        public Log log;

        public byte[] fileNane;

        public BigInteger recordCount;

        public BigInteger totalAmount;

        public byte[] merchantId;

        public BigInteger timestamp;

        public byte[] currentStatus;
    }

    public static class Virtual_Account_MappedEventResponse {
        public Log log;

        public byte[] fileName;

        public byte[] virtualAccount;
    }

    public static class Fees_AppliedEventResponse {
        public Log log;

        public byte[] fileName;

        public byte[] virtualAccount;
    }

    public static class Funding_InitializedEventResponse {
        public Log log;

        public byte[] virtualAccount;

        public byte[] fundingFileName;
    }

    public static class Funding_ConfirmedEventResponse {
        public Log log;

        public byte[] virtualAccount;

        public byte[] fundingFileName;
    }

    public static class Status_UpdatedEventResponse {
        public Log log;

        public byte[] fileName;

        public byte[] status;
    }

    public static class Duplicate_SubmissionEventResponse {
        public Log log;

        public byte[] fileName;
    }
}
