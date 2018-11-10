package digital.mercy.utils;

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

public class MercyAccount {

    public HashMap<String, String> createAccount(String account_name, String registrar_account, String address) throws IOException {

        ECKeyPair ecKeyPair = new ECKeyPair(new SecureRandom());
        Address dstAddress = new Address(ecKeyPair.getPublic(), "DST");

        AccountCreateOperation accountCreateOperation =
                new AccountCreateOperation(new ChainObject(ObjectType.ACCOUNT_OBJECT), account_name, dstAddress);

        int id = 99;
        unlockWallet(id,"dshd98d4");
        System.out.println(toCLI(buidJson(id,"suggest_brain_key",null)));

        return null;
    }


    private boolean unlockWallet(int id, String password) throws IOException {

        List<String> params = new ArrayList<>();
        params.add(password);
        toCLI(buidJson(id, "unlock", params));
        return true;
    }


    private String buidJson(int id, String method, List<String> params) {

        final RequestMethod requestMethod = new RequestMethod();
        final Gson gson = new Gson();


        requestMethod.setId(id);
        requestMethod.setJsonrpc("2.0");

        switch (method) {
            case "unlock": {
                requestMethod.setMethod("unlock");
                requestMethod.setParams(params);
                return gson.toJson(requestMethod);
            }
            case "transfer": {
                requestMethod.setMethod("transfer");
                return gson.toJson(requestMethod);

            }
            case "suggest_brain_key" : {
                requestMethod.setMethod("suggest_brain_key");
                return gson.toJson(requestMethod);

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
