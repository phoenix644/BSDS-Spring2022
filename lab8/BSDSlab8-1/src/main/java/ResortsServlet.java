

import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ResortsServlet", value = "/ResortsServlet")
public class ResortsServlet extends HttpServlet {

//    ConnectionFactory factory = new ConnectionFactory();
    private Gson gson = new Gson();
    String postMessage;
    GsonParser gsonParser = new GsonParser();

    private ResortController resortController = new ResortController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            payload = resortController.getObject("Resorts");
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print(payload);

            out.print("345072");


        } catch (IllegalArgumentException exception) {
            // if not valid url, return 400 error - Invalid inputs
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{ \"message\":\"Invalid inputs supplied2\"}");
        }
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            // return 201 success message
            response.setStatus(HttpServletResponse.SC_CREATED);


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            SendoQueue sendoQueue = new SendoQueue("Resorts");
            postMessage = gsonParser.submitResorts(request, parameters);
            sendoQueue.sendMessage("Resorts",postMessage);
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
