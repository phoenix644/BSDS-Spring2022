//package com.bsds.lab3;
//
//import com.google.gson.Gson;
//
//import org.apache.commons.validator.routines.UrlValidator;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Handles information about skiers and their lift usage. Matches API endpoint
// * <url-pattern>/skiers/*</url-pattern>
// */
//@WebServlet(name = "skierServlet", value = "/skierServlet")
//public class Skier2test extends HttpServlet {
//    private Map<String, String> pathMap;
//
//    /**
//     * Get ski day vertical for a skier
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
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
//            response.getWriter().write("{ \"message\":\"missing parameters\"}");
//            return;
//        }
//
//        // check full url at UrlValidator
//        String reqUrl = request.getRequestURL().toString();
//        // check path match in custom api rules
//        String[] urlParts = urlPath.split("/");
//
//        if (!isUrlValid(urlParts, reqUrl)) {
//            // if not valid url, return 400 error - Invalid inputs
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("{ \"message\":\"Invalid inputs supplied\"}");
//        } else {
//            // return 200 success message
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().write("34507");
//
////      // store url path variables  to a json format.
////      PrintWriter out = response.getWriter();
////      String pathJsonString = new Gson().toJson(pathMap);
////      out.print(pathJsonString);
////      out.flush();
//        }
//    }
//
//    /**
//     * Write a new lift ride for the skier
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
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
//            response.getWriter().write("{ \"message\":\"missing parameters\"}");
//            return;
//        }
//
//        // check full url at UrlValidator
//        String reqUrl = request.getRequestURL().toString();
//        // check path match in custom api rules
//        String[] urlParts = urlPath.split("/");
//
//        if (!isUrlValid(urlParts, reqUrl)) {
//            // if not valid url, return 400 error - Invalid inputs
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("{ \"message\":\"Invalid inputs supplied\"}");
//
//        } else {
//            // new season created - 201 success message
//            response.setStatus(HttpServletResponse.SC_CREATED);
//            response.getWriter().write("Write successful");
//
//
//            // Retrieve JSON object from request and passing to response.
////      PrintWriter out = response.getWriter();
////      String requestJsonString = request.getReader().lines().collect(Collectors.joining());
////      out.print(requestJsonString);
////      out.flush();
//        }
//    }
//
//    /**
//     * Check if path pattern matches at
//     * /skiers/{resortID}/seasons/{seasonID}/days/{dayID}/skiers/{skierID}
//     *
//     * @param urlParts
//     * @param reqUrl
//     * @return
//     */
//    private boolean isUrlValid(String[] urlParts, String reqUrl) {
//        // reqUrl  =
//        // http://localhost:8080/lab3_war_exploded/skiers/1/seasons/2019/days/3/skiers/33 --pass
//        // http://localhost:8080/lab3_war_exploded/skiers//////1/seasons/2019/days/1/skiers/123 --fails
//        // http://localhost:8080/lab3_war_exploded/skiers/seasons/2019/days/3/skiers/  --fails
//        pathMap = new HashMap<>();
//        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
//
//        // System.out.println(Arrays.toString(urlParts));
//        // System.out.println(urlParts.length);
//
//        // Only accept urlParts = [, {resortID}, seasons, {seasonID}, days, {dayID}, skiers, {skierID}]
//        if (urlParts.length == 8 && urlValidator.isValid(reqUrl)) {
//            String resortID = urlParts[1];
//            System.out.println(resortID);
//            pathMap.put("resortID", resortID);
//
//            for (int i = 2; i < urlParts.length; i++) {
//                switch (urlParts[i]) {
//                    case "seasons":
//                        String seasonID = urlParts[i + 1];
//                        pathMap.put("seasonID", seasonID);
//                        System.out.println(seasonID);
//                        break;
//                    case "days":
//                        String dayID = urlParts[i + 1];
//                        pathMap.put("dayID", dayID);
//                        System.out.println(dayID);
//                        break;
//                    case "skiers":
//                        String skierID = urlParts[i + 1];
//                        pathMap.put("skierID", skierID);
//                        System.out.println(skierID);
//                        break;
//                    default:
//                        continue;
//                }
//            }
//            // last check if all info are parsed into hashmap
//            return pathMap.size() == 4;
//        } else return false;
//    }
//}
