package digital.mercy.backend.webapi.Auth;

import com.google.gson.Gson;
import digital.mercy.backend.security.Crypto;
import digital.mercy.backend.utils.CliUtils;
import digital.mercy.backend.webapi.balance.TransferReq;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class AuthService extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest httpreq, HttpServletResponse httpresp) {

        try {

            PrintWriter writer = httpresp.getWriter();
            httpreq.setCharacterEncoding("UTF-8");
            writer.println("Hello, is Auth service");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpreq, HttpServletResponse httpresp) throws IOException {

        httpreq.setCharacterEncoding("UTF-8");

        String body = httpreq.getReader().lines().collect(Collectors.joining());
        AuthReq authReq = new Gson().fromJson(body, AuthReq.class);



    }
}

