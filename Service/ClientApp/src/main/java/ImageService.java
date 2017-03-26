import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Naga on 13-03-2017.
 */
@WebServlet(name = "ImageService", urlPatterns = "/ImageService")
public class ImageService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String response="";
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
        if (parameters.get("animals").toString().equals("animals")) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("http://www.shanalogic.com/wordpress/wp-content/uploads2/2015/02/Mountain-Lion-Cub1-600x450.jpg");
            jsonArray.put("https://images.pexels.com/photos/335910/pexels-photo-335910.jpeg");
            jsonArray.put("https://images.pexels.com/photos/141496/pexels-photo-141496.jpeg");
            jsonArray.put("https://images.pexels.com/photos/6468/animal-brown-horse.jpg");
            jsonArray.put("https://images.pexels.com/photos/65006/pexels-photo-65006.jpeg");
            jsonArray.put("https://images.pexels.com/photos/302304/pexels-photo-302304.jpeg");
            jsonObject.put("data", jsonArray);
            output = jsonObject.toString();
            Data data_ob = Data.getInstance();
            data_ob.setData(output);
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "animals are displayed");
            js.put("displayText", "animals are displayed");
            js.put("source", "image database");
            response = js.toString();
        }
        else if (parameters.get("animals").toString().equals("horse")) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("http://meltonmowbraymarket.co.uk/wp-content/uploads/horse.jpg");
            jsonArray.put("http://www.7springsfarm.biz/assets/img/1A.jpg");
            jsonArray.put("http://pixdaus.com/files/items/pics/2/77/256277_50f04bd66d2de8b3ffa1681af77cd378_large.jpg");
            jsonArray.put("https://images.pexels.com/photos/6468/animal-brown-horse.jpg");
            jsonArray.put("https://s-media-cache-ak0.pinimg.com/originals/14/8b/e0/148be0a49d3d4f64428c77a53f68d825.jpg");
            jsonArray.put("http://www.horsebreedsinfo.com/images/black_horse_running.jpg");
            jsonObject.put("data", jsonArray);
            output = jsonObject.toString();
            Data data_ob = Data.getInstance();
            data_ob.setData(output);
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "horses are displayed");
            js.put("displayText", "horses are displayed");
            js.put("source", "image database");
            response = js.toString();
        }
        else if (parameters.get("animals").toString().equals("cat")){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("https://static.pexels.com/photos/54632/cat-animal-eyes-grey-54632.jpeg");
            jsonArray.put("https://img1.wsimg.com/fos/sales/cwh/8/images/cats-with-hats-shop-06.jpg");
            jsonArray.put("https://i.ytimg.com/vi/DXUAyRRkI6k/maxresdefault.jpg");
            jsonArray.put("http://dailytimes.com.pk/static/uploads/original/8-health-benefits-of-having-a-cat-74280bfe4befb734b8ba3d73c991883d.jpg");
            jsonArray.put("https://media.licdn.com/mpr/mpr/AAEAAQAAAAAAAAXaAAAAJDAxYTkxMWE0LTM3YWQtNDQyMS1iZGNmLWU5ZDgyNmUyMDdiZg.jpg");
            jsonArray.put("https://static.pexels.com/photos/54632/cat-animal-eyes-grey-54632.jpeg");
            jsonObject.put("data", jsonArray);
            output = jsonObject.toString();
            Data data_ob = Data.getInstance();
            data_ob.setData(output);
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "cats are displayed");
            js.put("displayText", "cats are displayed");
            js.put("source", "image database");
            response = js.toString();
        }
        else if (parameters.get("animals").toString().equals("dog")){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("http://cdn.successdogs.com/wp-content/uploads/2014/07/how-to-train-your-dog-to-come-facebook.png");
            jsonArray.put("https://s-media-cache-ak0.pinimg.com/originals/62/d2/0a/62d20a092ccd5367567ffef3dda9f37e.jpg");
            jsonArray.put("http://mardaloopdoggiedaycare.com/wp-content/uploads/2014/12/cute-dog2.jpg");
            jsonArray.put("https://otvet.imgsmail.ru/download/2dc19088b1095f5e2c4e1ac592e3a224_i-1331.jpg");
            jsonArray.put("http://www.rd.com/wp-content/uploads/sites/2/2016/01/07-dog-breeds-chow-chow.jpg");
            jsonArray.put("http://greatinspire.com/wp-content/uploads/2012/11/Cute-puppy-photos-252.jpg");
            jsonObject.put("data", jsonArray);
            output = jsonObject.toString();
            Data data_ob = Data.getInstance();
            data_ob.setData(output);
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "dogs are displayed");
            js.put("displayText", "dogs are displayed");
            js.put("source", "image database");
            response = js.toString();
        }
        else if (parameters.get("null").toString().equals("clear")){
            Data data_ob = Data.getInstance();
            JSONObject js1 = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(" ");
            js1.put("data", jsonArray);
            data_ob.setData(js1.toString());
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "screen is cleared");
            js.put("displayText", "screen is cleared");
            js.put("source", "image database");
            response = js.toString();
        }
        resp.setHeader("Content-type", "application/json");
        resp.getWriter().write(response);
    }
}
