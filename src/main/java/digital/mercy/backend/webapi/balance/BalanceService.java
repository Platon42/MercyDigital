package digital.mercy.backend.webapi.balance;

import com.google.gson.Gson;
import digital.mercy.backend.security.Crypto;
import digital.mercy.backend.utils.CliUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.stream.Collectors;

@WebServlet("/getBalance")
public class BalanceService extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest httpreq, HttpServletResponse httpresp) {

        try {

            PrintWriter writer = httpresp.getWriter();
            httpreq.setCharacterEncoding("UTF-8");
            writer.println("Hello, is balance service");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpreq, HttpServletResponse httpresp) throws IOException {

        httpreq.setCharacterEncoding("UTF-8");
        Crypto crypto = new Crypto();
        String body = httpreq.getReader().lines().collect(Collectors.joining());
        BalanceReq balanceReq = new Gson().fromJson(body, BalanceReq.class);

        CliUtils cliUtils = new CliUtils(  1,crypto.decrypt("ZAiOCWsXC40="));

        httpresp.getWriter().print(cliUtils.getBalance(balanceReq.getAccountName()));


    }
}
