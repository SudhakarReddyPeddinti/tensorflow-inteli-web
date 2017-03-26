import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Naga on 06-03-2017.
 */
@WebServlet(name = "redirect", urlPatterns = "/redirect")
public class redirect extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, X-Auth-Token, Content-Type");


        /*
        resp.setHeader("Content-type", "text/event-stream\n\n");
        String s = "Nothing";
        System.out.print(s);

        String url = "https://api.mlab.com/api/1/databases/temp123/collections/server/58be2660734d1d10ff60882c?apiKey=NV6PEwYt13rsIJu21LnqTqGtnC_pZv3X";
        JSONObject jsonObject = getData(url);
        String data = jsonObject.getString("data");

        String data1 = "data: {\\n\n" +
                "data: \"msg\": \"hello world\",\\n\n" +
                "data: \"id\": 12345\\n\n" +
                "data: }\\n\\n";
        resp.setCharacterEncoding("UTF-8");
        int i=0;
        while(i < 90000){
            resp.getWriter().write(data1);
            resp.flushBuffer();
            i++;
        }
        */

        resp.setContentType("text/event-stream");

        //encoding must be set to UTF-8
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        for(int i=0; i<10; i++) {

            writer.write("data: "+ System.currentTimeMillis() +"\n\n");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writer.close();

    }



//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        resp.setHeader("Access-Control-Max-Age", "3600");
//        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, X-Auth-Token, Content-Type");
//        resp.setHeader("Content-type", "text/event-stream\n\n");
//        String s = "Nothing";
//        System.out.print(s);
//
//        String url = "https://api.mlab.com/api/1/databases/temp123/collections/server/58be2660734d1d10ff60882c?apiKey=NV6PEwYt13rsIJu21LnqTqGtnC_pZv3X";
//        JSONObject jsonObject = getData(url);
//        String data = jsonObject.getString("data");
//
//        String data1 = "data: {\\n\n" +
//                "data: \"msg\": \"hello world\",\\n\n" +
//                "data: \"id\": 12345\\n\n" +
//                "data: }\\n\\n";
//        resp.setCharacterEncoding("UTF-8");
//        int i=0;
//        while(i < 90000){
//            resp.getWriter().write(data1);
//            resp.flushBuffer();
//            i++;
//        }
//
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, X-Auth-Token, Content-Type");

        resp.setContentType("text/event-stream");

        //encoding must be set to UTF-8
        resp.setCharacterEncoding("UTF-8");

        Data data_ob = Data.getInstance();
        PrintWriter writer = resp.getWriter();

        for (long i=0; i<100000000; i++){
            String data = data_ob.getData();
//            if (data_ob.getFlag()){
                writer.write("data: " +  data +"\n\n");
                data_ob.setFlag(false);
                System.out.println("*********Set FLAG**************");
//            }

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
