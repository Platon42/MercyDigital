package digital.mercy.backend.webapi.account;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import digital.mercy.backend.security.Crypto;
import digital.mercy.backend.utils.CliUtils;
import digital.mercy.backend.utils.HibernateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.stream.Collectors;

@WebServlet("/createAccount")
public class AccountCreateService extends HttpServlet {


    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest httpreq, HttpServletResponse httpresp) {

        try {

            PrintWriter writer = httpresp.getWriter();
            httpreq.setCharacterEncoding("UTF-8");
            writer.println("Hello, is createAccount service");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpreq, HttpServletResponse httpresp) throws IOException {

        httpreq.setCharacterEncoding("UTF-8");

        String body = httpreq.getReader().lines().collect(Collectors.joining());
        JsonParser jsonParser = new JsonParser();
        Crypto crypto = new Crypto();

        CliUtils cliUtils = new CliUtils(1, crypto.decrypt("pGfmlPs2f9Q="));


        HibernateUtils hibernateUtils = new HibernateUtils();
        String type = jsonParser
                .parse(body)
                .getAsJsonObject()
                .get("type").getAsString();
        System.out.println(type);
        switch (type) {
            case "person":{
                PersonRequest personRequest = new Gson().fromJson(body, PersonRequest.class);
                Hashtable<String, String> utilsAccount = cliUtils.createAccount(personRequest.getAccountName());

                String json = utilsAccount.get("json");
                String address = utilsAccount.get("address");

                hibernateUtils.setClient(personRequest,address,"person");
                httpresp.getWriter().print(json);
                break;
            }
            case "org" :{
                OrgRequest orgRequest = new Gson().fromJson(body, OrgRequest.class);
                Hashtable<String, String> utilsAccount = cliUtils.createAccount(orgRequest.getAccountName());

                String json = utilsAccount.get("json");
                String address = utilsAccount.get("address");

                hibernateUtils.setClient(orgRequest,address,"org");
                httpresp.getWriter().print(json);
                break;
            }
        }



    }
}
