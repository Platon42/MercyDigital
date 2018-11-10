package digital.mercy.backend.webapi.history;

import com.google.gson.Gson;
import digital.mercy.backend.utils.CliUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/getHistory")
public class HistorySevice extends HttpServlet {


    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest httpreq, HttpServletResponse httpresp) {

        try {

            PrintWriter writer = httpresp.getWriter();
            httpreq.setCharacterEncoding("UTF-8");
            writer.println("Hello, is getHistory service");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpreq, HttpServletResponse httpresp) throws IOException {

        httpreq.setCharacterEncoding("UTF-8");

        String body = httpreq.getReader().lines().collect(Collectors.joining());
        HistoryRequest historyRequest = new Gson().fromJson(body, HistoryRequest.class);
        CliUtils cliUtils = new CliUtils(historyRequest.getId(), "");

        httpresp.getWriter().print(cliUtils.getHistory(historyRequest.getAccount(), historyRequest.getDepth()));

    }

}
