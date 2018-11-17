package digital.mercy.backend.webapi.auth;

import com.google.gson.Gson;
import digital.mercy.backend.utils.HibernateUtils;

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
            writer.println("Hello, is auth service");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpreq, HttpServletResponse httpresp) throws IOException {

        httpreq.setCharacterEncoding("UTF-8");
        HibernateUtils hibernateUtils = new HibernateUtils();

        String body = httpreq.getReader().lines().collect(Collectors.joining());
        AuthReq authReq = new Gson().fromJson(body, AuthReq.class);
        AuthResp authResp = new AuthResp();

        if (hibernateUtils.auth(authReq)) {
            authResp.setSuccess("true");
            authResp.setAccountName(authReq.getAccountName());
            authResp.setType(hibernateUtils.getAccType(authReq.getAccountName()));
        } else {
            authResp.setSuccess("false");
            authResp.setAccountName(authReq.getAccountName());
            authResp.setType(hibernateUtils.getAccType(authReq.getAccountName()));
        }


    }
}

