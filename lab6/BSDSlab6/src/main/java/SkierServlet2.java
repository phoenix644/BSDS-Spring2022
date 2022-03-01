import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Handles information about skiers and their lift usage. Matches API endpoint
 * <url-pattern>/skiers/*</url-pattern>
 * thanks to Daniel, for url validation part.
 */
@WebServlet(name = "SkierServlet2", value = "/SkierServlet2")
public class SkierServlet2 extends HttpServlet {


    //private Gson gson = new Gson();

    private ResortController resortController = new ResortController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        // return response in json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // urlPath is the string after url pattern described in web.xml
        String urlPath = request.getPathInfo();

        // check we have a URL!
        if (urlPath == null || urlPath.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.write("{ \"messag e\":\"missing parameters\"}");
            return;
        }

        // check full url at UrlValidator
        String reqUrl = request.getRequestURL().toString();
        // check path match in custom api rules
        String[] urlParts = urlPath.split("/");

        try {
            Map<String, String> parameters = UrlParser.parseUrl(urlParts, reqUrl);
            // dispatch
            //Object payload = new Archive.lab3.Skier();
//            if (parameters.get("action").equals("resort")) {
//                //payload = resortController.getResort(Integer.parseInt(parameters.get("resortId")));
//            } else {
//                throw new IllegalArgumentException("Not handled.");
//            }

            Object payload ;
            payload = resortController.getObject("Skier");
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print(payload);


            // new season created - 201 success message
            //response.setStatus(HttpServletResponse.SC_CREATED);
            //gson.toJson(payload, response.getWriter());

            // to return based on url parts.

//            response.setStatus(HttpServletResponse.SC_OK);
//            String SkierJsonString = gson.toJson(parameters);
//            out.print(SkierJsonString);

            out.print("345072");


            // Skier skier = new Skier();
//            Gson gson = new Gson();
//            String sSkierJsonString = gson.toJson(Skier);
//
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//           out.print(skierJsonString);

        } catch (IllegalArgumentException exception) {
            // if not valid url, return 400 error - Invalid inputs
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{ \"message\":\"Invalid inputs supplied2\"}");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");



        // urlPath is the string after url pattern described in web.xml
        String urlPath = request.getPathInfo();
        // check we have a URL!
        if (urlPath == null || urlPath.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.write("{ \"message\":\"missing parameters\"}");
            return;
        }


        // check full url at UrlValidator
        String reqUrl = request.getRequestURL().toString();
        // check path match in custom api rules
        String[] urlParts = urlPath.split("/");

        Map<String, String> parameters;
        try {
            // UrlParser do the duty of checking the url validity and returning the
            // corresponding message to client if it was a wrong url
            parameters = UrlParser.parseUrl(urlParts, reqUrl);
            // return 200 success message
            response.setStatus(HttpServletResponse.SC_OK);

            // do any sophisticated processing with urlParts which contains all the url params
            // TODO: process url params in `urlParts`
//            Skier1 skier1 = new Skier1();
//            Gson gson = new Gson();
//            String skierJsonString = gson.toJson(skier1);

            Parser parse = new Parser();
            String message = parse.parse(request);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            SendoQueue sendoQueue = new SendoQueue();
            sendoQueue.sendMessage("queueTest1",message);
            out.write(message);





            // out.print(studentJsonString);
            //out.flush();
            //out.print("34507");
//      // store url path variables  to a json format.
//      PrintWriter out = response.getWriter();
//      String pathJsonString = new Gson().toJson(pathMap);
//      out.print(pathJsonString);
//      out.flush();
        } catch (IllegalArgumentException ex) {

            // if not valid url, return 400 error - Invalid inputs
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{ \"message\":\"Invalid inputs supplied\"}");
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }



}
