package go.energy.crypto.cryptoenergy;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import go.energy.crypto.cryptoenergy.contracts.ItemFactory;

public class ItemFactoryManager {
    private final static String PRIVATE_KEY = "15b624835a6a4d63ab006a5edba4b3a7a1a81e92dec781771f29c29ff88fc54e";
    private final static String RPC_SERVER = "http://a0668ea8.ngrok.io";
//    private final static String RPC_SERVER = "HTTP://127.0.0.1:7545";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    private static ItemFactory itemFactory;

    private static ItemFactory loadContract(String contractAddress, Web3j web3j, Credentials credentials) {
        return ItemFactory.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    public static ItemFactory getItemFactory() throws Exception {
        if (itemFactory == null){
            Web3j web3j = Web3j.build(new HttpService(RPC_SERVER));
            Credentials credentials = Credentials.create(PRIVATE_KEY);

            String contractAddress = deployContract(web3j,credentials);
            itemFactory = loadContract(contractAddress, web3j, credentials);
        }
        return itemFactory;
    }
    private static String deployContract(Web3j web3j, Credentials credentials) throws Exception {
        return ItemFactory.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT)
                .send()
                .getContractAddress();
    }

}
