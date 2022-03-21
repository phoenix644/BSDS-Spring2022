import Model.LiftRide;
import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Handles information about skiers and their lift usage. Matches API endpoint
 * <url-pattern>/skiers/*</url-pattern>
 * thanks to Daniel, for url validation part.
 */
@WebServlet(name = "SkierServlet2", value = "/SkierServlet2")
public class SkierServlet2 extends HttpServlet {

    ConnectionFactory factory = new ConnectionFactory();
    private Gson gson = new Gson();
    String postMessage;
    GsonParser gsonParser = new GsonParser();

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

            Object payload ;
            payload = resortController.getObject("Skier");
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print(payload);


            out.print("345072");


        } catch (IllegalArgumentException exception) {
            // if not valid url, return 400 error - Invalid inputs
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{ \"message\":\"Invalid inputs supplied2\"}");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();


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


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            SendoQueue sendoQueue = new SendoQueue();
            postMessage = gsonParser.submitLiftRide(request, parameters);
            sendoQueue.sendMessage("Skier",postMessage, factory);
            out.write(postMessage);
            out.write(String.valueOf(parameters));


        } catch (IllegalArgumentException ex) {

            // if not valid url, return 400 error - Invalid inputs
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{ \"message\":\"Invalid inputs supplied\"}");
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }



}
