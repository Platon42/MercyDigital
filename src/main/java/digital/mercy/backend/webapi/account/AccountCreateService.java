package digital.mercy.backend.webapi.account;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import digital.mercy.backend.utils.CliUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        CliUtils cliUtils = new CliUtils(1, "");

        String type = jsonParser
                .parse(body)
                .getAsJsonObject()
                .get("type").getAsString();

        switch (type) {
            case "person":{
                PersonRequest personRequest = new Gson().fromJson(body, PersonRequest.class);
                httpresp.getWriter().print(cliUtils.createAccount(personRequest.getAccountName()));
            }
        }


    }
}
