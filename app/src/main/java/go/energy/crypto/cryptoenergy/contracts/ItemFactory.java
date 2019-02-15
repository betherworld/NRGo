package go.energy.crypto.cryptoenergy.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class ItemFactory extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061044c806100206000396000f3fe608060405234801561001057600080fd5b50600436106100b0576000357c0100000000000000000000000000000000000000000000000000000000900480637465f6c2116100835780637465f6c21461018e5780638482376b146101ba5780638d3b39e814610204578063dfe7a0ee14610230578063e320341e1461025c576100b0565b80633c7c3504146100b55780634a2b333f146100e35780634d7366551461013c578063544ef67514610162575b600080fd5b6100e1600480360360408110156100cb57600080fd5b50600160a060020a038135169060200135610288565b005b610109600480360360208110156100f957600080fd5b5035600160a060020a03166102a7565b604080519687526020870195909552858501939093526060850191909152608084015260a0830152519081900360c00190f35b6101096004803603602081101561015257600080fd5b5035600160a060020a03166102dc565b6100e16004803603604081101561017857600080fd5b50600160a060020a038135169060200135610319565b6100e1600480360360408110156101a457600080fd5b50600160a060020a038135169060200135610338565b6100e1600480360360e08110156101d057600080fd5b50600160a060020a038135169060208101359060408101359060608101359060808101359060a08101359060c00135610357565b6100e16004803603604081101561021a57600080fd5b50600160a060020a0381351690602001356103c6565b6100e16004803603604081101561024657600080fd5b50600160a060020a0381351690602001356103e5565b6100e16004803603604081101561027257600080fd5b50600160a060020a038135169060200135610401565b600160a060020a03909116600090815260208190526040902060040155565b600060208190529081526040902080546001820154600283015460038401546004850154600590950154939492939192909186565b600160a060020a03166000908152602081905260409020805460018201546002830154600384015460048501546005909501549395929491939092565b600160a060020a03909116600090815260208190526040902060010155565b600160a060020a03909116600090815260208190526040902060050155565b6040805160c0810182529687526020808801968752878201958652606088019485526080880193845260a08801928352600160a060020a039098166000908152978890529096209451855592516001850155905160028401555160038301555160048201559051600590910155565b600160a060020a03909116600090815260208190526040902060020155565b600160a060020a03909116600090815260208190526040902055565b600160a060020a0390911660009081526020819052604090206003015556fea165627a7a7230582064cbdbecc28a62754e633829e1f2e1bf8dcfcaa9f301b2d96ba9ddf24be3fa7a0029";

    public static final String FUNC_UPDATEAIR = "updateAir";

    public static final String FUNC_OWNERTOITEMS = "ownerToItems";

    public static final String FUNC_GETITEMS = "getItems";

    public static final String FUNC_UPDATEELECTRICITY = "updateElectricity";

    public static final String FUNC_UPDATESPECIAL = "updateSpecial";

    public static final String FUNC_CREATEITEM = "createItem";

    public static final String FUNC_UPDATEFIRE = "updateFire";

    public static final String FUNC_UPDATEWATER = "updateWater";

    public static final String FUNC_UPDATELEAF = "updateLeaf";

    @Deprecated
    protected ItemFactory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ItemFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ItemFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ItemFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> updateAir(String _userId, BigInteger _dnaAir) {
        final Function function = new Function(
                FUNC_UPDATEAIR,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaAir)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> ownerToItems(String param0) {
        final Function function = new Function(FUNC_OWNERTOITEMS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue(),
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> getItems(String _userId) {
        final Function function = new Function(FUNC_GETITEMS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue(),
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> updateElectricity(String _userId, BigInteger _dnaElectricity) {
        final Function function = new Function(
                FUNC_UPDATEELECTRICITY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaElectricity)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateSpecial(String _userId, BigInteger _dnaSpecial) {
        final Function function = new Function(
                FUNC_UPDATESPECIAL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaSpecial)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> createItem(String _userId, BigInteger _dnaWater, BigInteger _dnaElectricity, BigInteger _dnaFire, BigInteger _dnaLeaf, BigInteger _dnaAir, BigInteger _dnaSpecial) {
        final Function function = new Function(
                FUNC_CREATEITEM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaWater),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaElectricity),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaFire),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaLeaf),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaAir),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaSpecial)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateFire(String _userId, BigInteger _dnaFire) {
        final Function function = new Function(
                FUNC_UPDATEFIRE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaFire)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateWater(String _userId, BigInteger _dnaWater) {
        final Function function = new Function(
                FUNC_UPDATEWATER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaWater)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateLeaf(String _userId, BigInteger _dnaLeaf) {
        final Function function = new Function(
                FUNC_UPDATELEAF,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_dnaLeaf)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

//    public static RemoteCall<ItemFactory> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(ItemFactory.class, web3j, credentials, contractGasProvider, BINARY, "");
//    }

    @Deprecated
    public static RemoteCall<ItemFactory> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ItemFactory.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

//    public static RemoteCall<ItemFactory> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(ItemFactory.class, web3j, transactionManager, contractGasProvider, BINARY, "");
//    }

    @Deprecated
    public static RemoteCall<ItemFactory> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ItemFactory.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static ItemFactory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ItemFactory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ItemFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ItemFactory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

//    public static ItemFactory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return new ItemFactory(contractAddress, web3j, credentials, contractGasProvider);
//    }

    public static ItemFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ItemFactory(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
