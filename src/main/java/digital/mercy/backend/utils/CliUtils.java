package digital.mercy.backend.utils;

import ch.decent.sdk.crypto.Address;
import ch.decent.sdk.crypto.ECKeyPair;
import ch.decent.sdk.model.AccountCreateOperation;
import ch.decent.sdk.model.ChainObject;
import ch.decent.sdk.model.ObjectType;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CliUtils {

    private int id;
    private String password;

    public CliUtils(int id, String password) throws IOException {
        this.id=id;
        this.password = password;
    }

    public HashMap<String, String> createAccount(String account_name, String registrar_account, String address) throws IOException {

        ECKeyPair ecKeyPair = new ECKeyPair(new SecureRandom());
        Address dstAddress = new Address(ecKeyPair.getPublic(), "DST");

        AccountCreateOperation accountCreateOperation =
                new AccountCreateOperation(new ChainObject(ObjectType.ACCOUNT_OBJECT), account_name, dstAddress);

        System.out.println(toCLI(buidJson("suggest_brain_key",null)));

        return null;
    }

    public HashMap<String,String> transfer (String sender, String receiver, String amount, String currency) throws IOException {

        unlockWallet();
        List<String> params = new ArrayList<>();

        params.add(sender);
        params.add(receiver);
        params.add(amount);
        params.add(currency);
        params.add("OK");
        params.add("true");

        System.out.println(buidJson("transfer", params));

        System.out.println(toCLI(buidJson("transfer", params)));
        return null;
    }

    public String getHistory (String address, String depth) throws IOException {

        unlockWallet();
        List<String> params = new ArrayList<>();
        params.add(address);
        params.add(depth);

        return toCLI(buidJson("get_account_history", params));
    }



    private boolean unlockWallet() throws IOException {

        List<String> params = new ArrayList<>();
        params.add(password);
        toCLI(buidJson("unlock", params));
        return true;
    }


    private String buidJson(String method, List<String> params) {

        final CLIReq CLIReq = new CLIReq();
        final Gson gson = new Gson();

        CLIReq.setId(id);
        CLIReq.setJsonrpc("2.0");

        switch (method) {
            case "unlock": {
                CLIReq.setMethod("unlock");
                CLIReq.setParams(params);
                return gson.toJson(CLIReq);
            }
            case "transfer": {
                CLIReq.setMethod("transfer");
                CLIReq.setParams(params);
                return gson.toJson(CLIReq);

            }
            case "suggest_brain_key" : {
                CLIReq.setMethod("suggest_brain_key");
                return gson.toJson(CLIReq);

            }
            case "register_account" : {
                CLIReq.setMethod("register_account");
                CLIReq.setParams(params);
                return gson.toJson(CLIReq);

            }
            case "get_account_history" : {
                CLIReq.setMethod("get_account_history");
                CLIReq.setParams(params);
                return gson.toJson(CLIReq);

            }


        }

        return "";
    }

    private String toCLI(String payload) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost CliWallet = new HttpPost("http://mercy.digital:8091");

        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        CliWallet.setEntity(entity);

        HttpResponse response = httpClient.execute(CliWallet);
        return EntityUtils.toString(response.getEntity());
    }

}
