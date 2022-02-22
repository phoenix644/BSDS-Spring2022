//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.google.gson.Gson;
//import org.apache.commons.validator.routines.UrlValidator;
//
///**
// * Handles information about skiers and their lift usage. Matches API endpoint
// * <url-pattern>/skiers/*</url-pattern>
// * thanks to Daniel, for url validation part.
// */
//@WebServlet(name = "Archive.lab3.SkierServlet", value = "/Archive.lab3.SkierServlet")
//public class SkierServlet2 extends HttpServlet {
//
//
//    private Gson gson = new Gson();
//
//    private Archive.lab3.ResortController resortController = new Archive.lab3.ResortController();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        PrintWriter out = response.getWriter();
//
//        // return response in json
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        // urlPath is the string after url pattern described in web.xml
//        String urlPath = request.getPathInfo();
//
//        // check we have a URL!
//        if (urlPath == null || urlPath.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            out.write("{ \"messag e\":\"missing parameters\"}");
//            return;
//        }
//
//        // check full url at UrlValidator
//        String reqUrl = request.getRequestURL().toString();
//        // check path match in custom api rules
//        String[] urlParts = urlPath.split("/");
//
//        try {
//            //Map<String, String> map = parseUrl(urlParts, reqUrl);
//            // dispatch
//            Object payload = new Archive.lab3.Skier();
////            if (map.get("action").equals("resort")) {
////                //payload = resortController.getResort(Integer.parseInt(map.get("resortId")));
////            } else {
////                throw new IllegalArgumentException("Not handled.");
////            }
//
//
//            // new season created - 201 success message
//            response.setStatus(HttpServletResponse.SC_CREATED);
//            gson.toJson(payload, response.getWriter());
//
//        } catch (IllegalArgumentException exception) {
//            // if not valid url, return 400 error - Invalid inputs
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            out.write("{ \"message\":\"Invalid inputs supplied\"}");
//        }
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
////        response.setContentType("application/json");
////        response.setCharacterEncoding("UTF-8");
//
//        // urlPath is the string after url pattern described in web.xml
//        String urlPath = request.getPathInfo();
//
//        // check we have a URL!
//        if (urlPath == null || urlPath.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            out.write("{ \"message\":\"missing parameters\"}");
//            return;
//        }
//
//
//        // check full url at UrlValidator
//        String reqUrl = request.getRequestURL().toString();
//        // check path match in custom api rules
//        String[] urlParts = urlPath.split("/");
//
////        out.println(reqUrl);
////        out.println(urlParts.length);
////
////        for (int i = 0; i <= urlParts.length; i++)
////              {
////            out.println(urlParts[i]);
////              }
//        Map<String, String> map;
//        try {
//            map = parseUrl(urlParts, reqUrl);
//            // return 200 success message
//            response.setStatus(HttpServletResponse.SC_OK);
//
//            // do any sophisticated processing with urlParts which contains all the url params
//            // TODO: process url params in `urlParts`
//            Archive.lab3.Skier skier = new Archive.lab3.Skier();
//            Gson gson = new Gson();
//            String skierJsonString = gson.toJson(skier);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            // out.print(studentJsonString);
//            //out.flush();
//            out.print("34507");
//
//
////      // store url path variables  to a json format.
////      PrintWriter out = response.getWriter();
////      String pathJsonString = new Gson().toJson(pathMap);
////      out.print(pathJsonString);
////      out.flush();
//        } catch (IllegalArgumentException ex) {
//
//            // if not valid url, return 400 error - Invalid inputs
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            out.write("{ \"message\":\"Invalid inputs supplied\"}");
//        }
//    }
//
//    private static Map<String, String> parseUrl(String[] urlParts, String reqUrl) {
//        // reqUrl  =
//        // http://localhost:8080/lab3_war_exploded/skiers/1/seasons/2019/days/3/skiers/33 --pass
//        // http://localhost:8080/lab3_war_exploded/skiers//////1/seasons/2019/days/1/skiers/123 --fails
//        // http://localhost:8080/lab3_war_exploded/skiers/seasons/2019/days/3/skiers/  --fails
//        Map<String, String> pathMap = new HashMap<>();
//        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
//
//        // System.out.println(Arrays.toString(urlParts));
//        // System.out.println(urlParts.length);
//
//        // Only accept urlParts = [, {resortID}, seasons, {seasonID}, days, {dayID}, skiers, {skierID}]
//        if (urlParts.length != 8 || urlValidator.isValid(reqUrl)) {
//            throw new IllegalArgumentException("Invalid URL");
//        }
//
//        String resortID = urlParts[1];
//        System.out.println(resortID);
//        pathMap.put("resortID", resortID);
//
//        for (int i = 2; i < urlParts.length; i++) {
//            switch (urlParts[i]) {
//                case "seasons":
//                    String seasonID = urlParts[i + 1];
//                    pathMap.put("seasonID", seasonID);
//                    System.out.println(seasonID);
//                    break;
//                case "days":
//                    String dayID = urlParts[i + 1];
//                    pathMap.put("dayID", dayID);
//                    System.out.println(dayID);
//                    break;
//                case "skiers":
//                    String skierID = urlParts[i + 1];
//                    pathMap.put("skierID", skierID);
//                    System.out.println(skierID);
//                    break;
//                default:
//                    continue;
//            }
//        }
//        // last check if all info are parsed into hashmap
//        if (pathMap.size() != 4) {
//            throw new IllegalArgumentException("Passed URL expects 4 fields...");
//        }
//        return pathMap;
//
//
//    }
//
//
//}
