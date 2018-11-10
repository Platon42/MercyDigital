import ch.decent.sdk.DCoreApi;
import ch.decent.sdk.DCoreSdk;
import ch.decent.sdk.api.*;
import ch.decent.sdk.model.*;
import digital.mercy.backend.utils.CliUtils;
import io.reactivex.Single;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String args[]) throws IOException {


        DCoreApi dCoreApi = DCoreSdk.createForHttp(new OkHttpClient(), "http://mercy.digital:8090");
        HistoryApi historyApi =  dCoreApi.getHistoryApi() ;

        Single<List<AssetAmount>> init1 = dCoreApi.getBalanceApi().getBalance("decent");

        init1.subscribe(assetAmount ->
                System.out.println(assetAmount.get(0)));

        Single<List<AssetAmount>> init2 = dCoreApi.getBalanceApi().getBalance("myfirstacc");

        init2.subscribe(assetAmount ->
                System.out.println(assetAmount.get(0)));


        CliUtils cliUtils = new CliUtils(100,"dshd98d4");
        //cliUtils.transfer("decent","myfirstacc","3","DCT");
        cliUtils.getHistory("decent","100");

//        CliUtils account = new CliUtils(
//                new ChainObject(ObjectType.ACCOUNT_OBJECT),
//                new ChainObject(ObjectType.ACCOUNT_OBJECT),
//                "maksim",
//                new Authority(address),
//                new Authority(address),
//                new Options(address),
//                new Publishing(true,),
//        );


        init1 = dCoreApi.getBalanceApi().getBalance("decent");

        init1.subscribe(assetAmount ->
                System.out.println(assetAmount.get(0)));


        init2 = dCoreApi.getBalanceApi().getBalance("myfirstacc");

        init2.subscribe(assetAmount ->
                System.out.println(assetAmount.get(0)));
    }
}
