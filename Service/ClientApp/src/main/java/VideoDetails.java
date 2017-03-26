import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Naga on 21-03-2017.
 */
@WebServlet(name="VideoDetails", urlPatterns = "/videoDetails")
public class VideoDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, X-Auth-Token, Content-Type");

        resp.setContentType("text/event-stream");

        //encoding must be set to UTF-8
        resp.setCharacterEncoding("UTF-8");
        JSONObject js = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("4");
        jsonArray.put("1079");
        jsonArray.put("4040");
        jsonArray.put("8680");
        jsonArray.put("14200");
        js.put("TimeStamp", jsonArray);

        PrintWriter writer = resp.getWriter();
        for (long i=0; i<100000000; i++){

//            writer.write("data: " +  js.toString() +"\n\n");
            writer.write(js.toString() + "\n\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.flush();
        }
        writer.close();
    }
}
