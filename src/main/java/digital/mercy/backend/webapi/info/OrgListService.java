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

@WebServlet("/getOrgList")
public class OrgListService extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest httpreq, HttpServletResponse httpresp) {

        try {

            PrintWriter writer = httpresp.getWriter();
            httpreq.setCharacterEncoding("UTF-8");
            writer.println("Hello, is getList service");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpreq, HttpServletResponse httpresp) throws IOException {

        httpreq.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        HibernateUtils hibernateUtils = new HibernateUtils();
        OrgListResp orgListResp = new OrgListResp();
        orgListResp.setOrgNames(hibernateUtils.getOrgList());
        httpresp.getWriter().print(gson.toJson(orgListResp));

    }


}
