
import digital.mercy.backend.security.Crypto;
import digital.mercy.backend.utils.CliUtils;
import digital.mercy.backend.utils.HibernateUtils;


import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String args[]) throws IOException {

//
//        DCoreApi dCoreApi = DCoreSdk.createForHttp(new OkHttpClient(), "http://mercy.digital:8090");
//        HistoryApi historyApi =  dCoreApi.getHistoryApi() ;
//
//        Single<List<AssetAmount>> init1 = dCoreApi.getBalanceApi().getBalance("decent");
//
//        init1.subscribe(assetAmount ->
//                System.out.println(assetAmount.get(0)));
//
//        Single<List<AssetAmount>> init2 = dCoreApi.getBalanceApi().getBalance("seeder");
//
//        init2.subscribe(assetAmount ->
//                System.out.println(assetAmount.get(0)));


        CliUtils cliUtils = new CliUtils(100,"");
        //cliUtils.transfer("decent","seeder","3","DCT");

        HibernateUtils hibernateUtils  = new HibernateUtils();
        System.out.println(hibernateUtils.getAccInfo("person","fallout667"));

        Crypto crypto = new Crypto();
        System.out.println(crypto.encrypt("dshd98d4"));

        System.out.println(crypto.decrypt("pGfmlPs2f9Q="));
        //System.out.println(cliUtils.createAccount("maksimmmm"));

//
//        init1 = dCoreApi.getBalanceApi().getBalance("decent");
//
//        init1.subscribe(assetAmount ->
//                System.out.println(assetAmount.get(0)));
//
//
//        init2 = dCoreApi.getBalanceApi().getBalance("seeder");
//
//        init2.subscribe(assetAmount ->
//                System.out.println(assetAmount.get(0)));
    }
}
