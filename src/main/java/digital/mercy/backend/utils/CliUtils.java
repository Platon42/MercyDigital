package digital.mercy.backend.utils;

import ch.decent.sdk.api.AccountApi;
import ch.decent.sdk.api.TransactionApi;
import ch.decent.sdk.crypto.Address;
import ch.decent.sdk.crypto.ECKeyPair;
import ch.decent.sdk.model.AccountCreateOperation;
import ch.decent.sdk.model.ChainObject;
import ch.decent.sdk.model.ObjectType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import java.util.Hashtable;
import java.util.List;

public class CliUtils {

    private int id;
    private String password;

    public CliUtils(int id, String password) {
        this.id=id;
        this.password = password;
    }

    public Hashtable<String,String> createAccount(String account_name) throws IOException {

//        ECKeyPair ecKeyPair = new ECKeyPair(new SecureRandom());
//        Address dstAddress = new Address(ecKeyPair.getPublic(), "DCT");
//
//        new AccountCreateOperation(new ChainObject(ObjectType.ACCOUNT_OBJECT), account_name, dstAddress);


        List<String> params = new ArrayList<>();
        Hashtable<String, String> answer = suggest();

        params.add(account_name);
        params.add(answer.get("address"));
        params.add(answer.get("address"));
        params.add("decent");
        params.add("true");

        answer.put("json",toCLI(buidJson("register_account", params)));

        return answer;
    }

    public String transfer (String sender, String receiver, String amount, String currency) throws IOException {

        unlockWallet();
        List<String> params = new ArrayList<>();

        params.add(sender);
        params.add(receiver);
        params.add(amount);
        params.add(currency);
        params.add("OK");
        params.add("true");

        return toCLI(buidJson("transfer", params));

    }

    public Hashtable<String,String> suggest() throws IOException{

        unlockWallet();
        String suggest = toCLI(buidJson("suggest_brain_key",null));

        JsonParser jsonParser = new JsonParser();

        Hashtable<String,String> hashtable = new Hashtable<>();
        String address = jsonParser
                .parse(suggest)
                .getAsJsonObject().getAsJsonObject("result")
                .get("pub_key").getAsString();
        String priv_key = jsonParser
                .parse(suggest)
                .getAsJsonObject().getAsJsonObject("result")
                .get("wif_priv_key").getAsString();
        String brain_priv_key = jsonParser
                .parse(suggest)
                .getAsJsonObject().getAsJsonObject("result")
                .get("brain_priv_key").getAsString();

        hashtable.put("address",address);
        hashtable.put("priv_key",priv_key);
        hashtable.put("brain_priv_key",brain_priv_key);

        return hashtable;

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
