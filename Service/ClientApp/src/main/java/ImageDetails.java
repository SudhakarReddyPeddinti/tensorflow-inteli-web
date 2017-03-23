import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Naga on 18-02-2017.
 */
@WebServlet(name = "imageDetails", urlPatterns = "/imageDetails")
public class ImageDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, X-Auth-Token, Content-Type");
        resp.setContentType("application/json");
        String topic = req.getParameter("topic");
        String msg = req.getParameter("msg");
        System.out.print(topic + " " + msg);
        resp.getWriter().write("URL Working");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println(data);
        String output = "";
        JSONObject params = new JSONObject(data);
        JSONObject result = params.getJSONObject("result");
        JSONObject parameters = result.getJSONObject("parameters");
        if (parameters.has("contains")) {
            String image = parameters.getString("image").toString();
            String contains = parameters.getString("contains").toString();
            String query = "https://api.mlab.com/api/1/databases/temp123/collections/imageDetails?q={%22name%22:%22" + image + "%22}&apiKey=NV6PEwYt13rsIJu21LnqTqGtnC_pZv3X";
            JSONObject jsonObject = getData(query);
            Clarifai clarifai = new Clarifai(jsonObject.getString("url"));
            List<String> annotations = clarifai.getAnnotation();
            JSONObject js = new JSONObject();
            if (annotations.contains(parameters.getString("contains"))){
                js.put("speech", "Image " + image + " has " + contains);
                js.put("displayText", "Image " + image + " has " + contains);
                js.put("source", "image database");
            }else{
                js.put("speech", "Image " + image + " is not" + contains);
                js.put("displayText", "Image " + image + " is not" + contains);
                js.put("source", "image database");
            }
            output = js.toString();
        } else {
            String image = parameters.getString("image").toString();
            String query = "https://api.mlab.com/api/1/databases/temp123/collections/imageDetails?q={%22name%22:%22" + image + "%22}&apiKey=NV6PEwYt13rsIJu21LnqTqGtnC_pZv3X";
            JSONObject jsonObject = getData(query);
            Clarifai clarifai = new Clarifai(jsonObject.getString("url"));
            List<String> annotations = clarifai.getAnnotation();
            annotations.toString();
            JSONObject js = new JSONObject();
            js.put("speech", "Image  has " + annotations.toString());
            js.put("displayText", "Image  has " + annotations.toString());
            js.put("source", "image database");
            output = js.toString();
        }

        resp.setHeader("Content-type", "application/json");
//        resp.setHeader("Content-type", "text/event-stream");
        resp.sendRedirect("/redirect");
        resp.getWriter().write(output);
//        req.getRequestDispatcher("/redirect").forward(req, resp);
//        System.out.print("NAg");
    }

    public JSONObject getData(String query) throws IOException {
        URL obj = new URL(query);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONArray jsonArray = new JSONArray(response.toString());
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        return jsonObject;
    }
}
