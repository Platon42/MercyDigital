package digital.mercy.backend.webapi.info;


import com.google.gson.Gson;

import digital.mercy.backend.utils.HibernateUtils;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/getAccInfo")
public class AccInfoService extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest httpreq, HttpServletResponse httpresp) {

        try {

            PrintWriter writer = httpresp.getWriter();
            httpreq.setCharacterEncoding("UTF-8");
            writer.println("Hello, is getAccInfo service");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpreq, HttpServletResponse httpresp) throws IOException {

        httpreq.setCharacterEncoding("UTF-8");

        String body = httpreq.getReader().lines().collect(Collectors.joining());
        HibernateUtils hibernateUtils = new HibernateUtils();
        AccInfoReq accInfoReq = new Gson().fromJson(body, AccInfoReq.class);
        String resp = hibernateUtils.getAccInfo(accInfoReq.getType(), accInfoReq.getName());
        httpresp.getWriter().print(resp);

    }

}
