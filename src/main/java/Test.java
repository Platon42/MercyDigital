import ch.decent.sdk.DCoreApi;
import ch.decent.sdk.DCoreSdk;
import ch.decent.sdk.api.*;
import ch.decent.sdk.model.*;
import digital.mercy.utils.MercyAccount;
import io.reactivex.Single;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String args[]) throws IOException {


        DCoreApi dCoreApi = DCoreSdk.createForHttp(new OkHttpClient(), "http://mercy.digital:8090");
        AccountApi accountApi = dCoreApi.getAccountApi();
        GeneralApi generalApi = dCoreApi.getGeneralApi();

        MercyAccount mercyAccount = new MercyAccount();
        mercyAccount.createAccount("maxxxx","","");

//        MercyAccount account = new MercyAccount(
//                new ChainObject(ObjectType.ACCOUNT_OBJECT),
//                new ChainObject(ObjectType.ACCOUNT_OBJECT),
//                "maksim",
//                new Authority(address),
//                new Authority(address),
//                new Options(address),
//                new Publishing(true,),
//        );


        Single<List<AssetAmount>> init1 = dCoreApi.getBalanceApi().getBalance("myfirstacc");

        init1.subscribe(assetAmount ->
                System.out.println(assetAmount.get(0)));


    }
}
