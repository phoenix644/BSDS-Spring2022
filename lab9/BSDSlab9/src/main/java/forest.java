//import com.google.gson.Gson;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeoutException;
//
//import org.apache.commons.pool2.ObjectPool;
//import org.apache.commons.pool2.impl.GenericObjectPool;
//import org.json.simple.JSONValue;
//import util.ChannelObjectFactory;
//
//@WebServlet(name = "SkiServlet", value = "/SkiServlet")
//public class SkiServlet extends HttpServlet {
//
//    private ConnectionFactory factory;
//    //    private Channel channel;
//    private ObjectPool<Channel> pool;
//
//    public static String QUEUE_NAME = "LAB_6_QUEUE";
//    public static String RABBIT_SERVER = "localhost";
//    public static String RABBIT_USERNAME = "forrest";
//    public static String RABBIT_PASSWORD = "forrest";
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//
//        System.out.println("Initialize Connection");
//        factory = new ConnectionFactory();
//        factory.setHost(RABBIT_SERVER);
//        factory.setUsername(RABBIT_USERNAME);
//        factory.setPassword(RABBIT_PASSWORD);
//
//        try {
//            Connection connection = factory.newConnection();
//            pool = new GenericObjectPool<>(new ChannelObjectFactory(connection));
//        } catch (IOException | TimeoutException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void publish(String content) {
//
//        try {
////            Connection connection = factory.newConnection();
////            channel = connection.createChannel();
////            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
////            channel.basicPublish("", QUEUE_NAME, null, content.getBytes(StandardCharsets.UTF_8));
//
//            Channel channel = pool.borrowObject();
//            channel.basicPublish("", QUEUE_NAME, null, content.getBytes(StandardCharsets.UTF_8));
//            pool.returnObject(channel);
//            System.out.printf("Connection Pool : %d/%d\n", pool.getNumActive(), pool.getNumIdle());
//
//        } catch (IOException | TimeoutException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Reference: https://mkyong.com/java/java-convert-string-to-int/
//     */
//    private boolean isDigit(String input) {
//        // null or length < 0, return false.
//        if (input == null || input.length() < 0)
//            return false;
//
//        // empty, return false
//        input = input.trim();
//        if ("".equals(input))
//            return false;
//
//        if (input.startsWith("-")) {
//            // negative number in string, cut the first char
//            return input.substring(1).matches("[0-9]*");
//        } else {
//            // positive number, good, just check
//            return input.matches("[0-9]*");
//        }
//    }
//
//    /**
//     * Validate the request url path according to the API spec
//     */
//    private Patterns matchedUrlPattern(String pattern, String[] urlParts) {
//        int resortID = -1;
//        String seasonID = "";
//        int dayIDInt = -1;
//        String dayID = "";
//        int skierID = -1;
//
//        if (pattern.equals("/skiers")){
//            if (urlParts.length == 8
//                    && urlParts[2].equals("seasons")
//                    && urlParts[4].equals("days")
//                    && urlParts[6].equals("skiers")) {
//                // Patterns.SKIER_DATE_ID: "/skiers": /{resortID}/seasons/{seasonID}/days/{dayID}/skiers/{skierID}
//                if (isDigit(urlParts[1])
//                        && isDigit(urlParts[3])
//                        && isDigit(urlParts[5])
//                        && isDigit(urlParts[7])) {
//                    resortID = Integer.parseInt(urlParts[1]);
//                    dayIDInt = Integer.parseInt(urlParts[5]);
//                    dayID = urlParts[5];
//                    seasonID = urlParts[3];
//                    skierID = Integer.parseInt(urlParts[7]);
//                }
//
//                if (resortID < 0 || dayIDInt < 1 || dayIDInt > 366 || skierID < 0) {
//                    return Patterns.NULL;
//                }
//
//                return Patterns.SKIER_DATE_ID;
//
//            } else if (urlParts.length == 3
//                    && urlParts[2].equals("vertical")) {
//                // Patterns.SKIER_VERTICAL: "/skiers": /{skierID}/vertical
//                if (isDigit(urlParts[1])) {
//                    skierID = Integer.parseInt(urlParts[1]);
//                }
//
//                if (skierID < 0) {
//                    return Patterns.NULL;
//                }
//
//                return Patterns.SKIER_VERTICAL;
//
//            }
//        } else if (pattern.equals("/resorts")) {
//            if (urlParts.length == 7
//                    && urlParts[2].equals("seasons")
//                    && urlParts[4].equals("day")
//                    && urlParts[6].equals("skiers")) {
//                // Patterns.RESORT_DATE_SKIER: "/resorts": /{resortID}/seasons/{seasonID}/day/{dayID}/skiers
//                if (isDigit(urlParts[1])
//                        && isDigit(urlParts[3])
//                        && isDigit(urlParts[5])) {
//                    resortID = Integer.parseInt(urlParts[1]);
//                    seasonID = urlParts[3];
//                    dayIDInt = Integer.parseInt(urlParts[5]);
//                    dayID = urlParts[5];
//                }
//
//                if (resortID < 0 || dayIDInt < 1 || dayIDInt > 366) {
//                    return Patterns.NULL;
//                }
//
//                return Patterns.RESORT_DATE_SKIER;
//
//            } else if (urlParts.length == 3
//                    && urlParts[2].equals("seasons")) {
//                // Patterns.RESORT_SEASON : "resorts/": {resortID}/seasons
//                if (isDigit(urlParts[1])) {
//                    resortID = Integer.parseInt(urlParts[1]);
//                }
//
//                if (resortID < 0) {
//                    return Patterns.NULL;
//                }
//
//                return Patterns.RESORT_SEASON;
//
//            }
//        } else if (pattern.equals("/statistics")) {
//            if (urlParts.length == 0) {
//                // Patterns.STATISTICS : "statistics/": null
//                return Patterns.STATISTICS;
//            }
//        }
//
//        return Patterns.NULL;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        res.setContentType("application/json");
//        String servletPath = req.getServletPath(); // Example: "/skiers"
//        String urlPath = req.getPathInfo(); // Example: "/12/vertical"
//
//        // check URL existence
//        if (urlPath == null || urlPath.isEmpty()) {
//            if (servletPath.equals("/statistics")) {
//                res.setStatus(HttpServletResponse.SC_OK);
//                res.getWriter().write("{\"endpointStats\":[{\"URL\":\"/resorts\",\"operation\":\"GET\",\"mean\":11,\"max\":198}]}");
//                return;
//            } else if (servletPath.equals("/resorts")) {
//                res.setStatus(HttpServletResponse.SC_OK);
//                res.getWriter().write("{\"resorts\":[{\"resortName\":\"string\",\"resortID\":0}]}");
//                return;
//            }
//
//            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            res.getWriter().write("{\"message\":\"Invalid inputs supplied\"}");
//            return;
//        }
//
//        String[] urlParts = urlPath.split("/");
//
//        switch (matchedUrlPattern(servletPath, urlParts)) {
//            case NULL:
//                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                res.getWriter().write("{\"message\":\"Invalid inputs supplied\"}");
//                break;
//            case SKIER_DATE_ID:
//                res.setStatus(HttpServletResponse.SC_OK);
//                res.getWriter().write("12382");
//                break;
//            case SKIER_VERTICAL:
//                res.setStatus(HttpServletResponse.SC_OK);
//                res.getWriter().write("{\"resorts\":[{\"seasonID\":\"string\",\"totalVert\":0}]}");
//                break;
//            case RESORT_DATE_SKIER:
//                res.setStatus(HttpServletResponse.SC_OK);
//                res.getWriter().write("{\"time\":\"Mission Ridge\",\"numSkiers\":78999}");
//                break;
//            case RESORT_SEASON:
//                res.setStatus(HttpServletResponse.SC_OK);
//                res.getWriter().write("{\"seasons\":[\"string\"]}");
//                break;
//            case STATISTICS:
//                res.setStatus(HttpServletResponse.SC_OK);
//                res.getWriter().write("{\"endpointStats\":[{\"URL\":\"/resorts\",\"operation\":\"GET\",\"mean\":11,\"max\":198}]}");
//            default:
//                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                res.getWriter().write("{\"message\":\"This should not happen\"}");
//                return;
//        }
//    }
//
//    private String readBody(BufferedReader buffIn) throws IOException {
//        String body = "";
//        String line;
//        while ((line = buffIn.readLine()) != null) {
//            body += line;
//        }
//        return body;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        res.setContentType("application/json");
////        String servletPath = req.getServletPath(); // Example: "/skiers"
//        String urlPath = req.getPathInfo(); // Example: "/{resortID}/seasons/{seasonID}/days/{dayID}/skiers/{skierID}"
//
//        // check URL existence
////        if (urlPath == null || urlPath.isEmpty()) {
////            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
////            res.getWriter().write("{\"message\":\"Invalid inputs supplied\"}");
////            return;
////        }
//
//        String[] urlParts = urlPath.split("/");
//        String body = "";
//        Gson gson = new Gson();
//
//        body = readBody(req.getReader());
//        LiftRecord lRecord = (LiftRecord) gson.fromJson(body, LiftRecord.class);
//
//        // /{resortID}/seasons/{seasonID}/days/{dayID}/skiers/{skierID}
//        Map obj = new HashMap();
//        obj.put("resortID", urlParts[1]);
//        obj.put("seasonID", urlParts[3]);
//        obj.put("salary", urlParts[5]);
//        obj.put("skierID", urlParts[7]);
//        obj.put("liftID", lRecord.getLiftID());
//        obj.put("time", lRecord.getTime());
//        obj.put("waitTime", lRecord.getWaitTime());
//        String jsonText = JSONValue.toJSONString(obj);
//        publish(jsonText);
//
////        switch (matchedUrlPattern(servletPath, urlParts)) {
////            case NULL:
////                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////                res.getWriter().write("{\"message\":\"Invalid inputs supplied\"}");
////                break;
////            case SKIER_DATE_ID:
////                // parse body
////                body = readBody(req.getReader());
////                LiftRecord lRecord = (LiftRecord) gson.fromJson(body, LiftRecord.class);
////
////                //TODO: Write data into & write to res
////
////                if (lRecord.getLiftID() > 0 && lRecord.getTime() > 0) {
////                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////
////                    // /{resortID}/seasons/{seasonID}/days/{dayID}/skiers/{skierID}
////                    Map obj = new HashMap();
////                    obj.put("resortID", urlParts[1]);
////                    obj.put("seasonID", urlParts[3]);
////                    obj.put("salary", urlParts[5]);
////                    obj.put("skierID", urlParts[7]);
////                    obj.put("liftID", lRecord.getLiftID());
////                    obj.put("time", lRecord.getTime());
////                    obj.put("waitTime", lRecord.getWaitTime());
////                    String jsonText = JSONValue.toJSONString(obj);
////                    publish(jsonText);
////
////                } else {
////                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////                    res.getWriter().write("{\"message\":\"Invalid inputs supplied\"}");
////                }
////                break;
////            case RESORT_DATE_SKIER:
////                // parse body
////                body = readBody(req.getReader());
////                SeasonRecord sRecord = (SeasonRecord) gson.fromJson(body, SeasonRecord.class);
////
////                //TODO: Write data into & write to res
////
////                if (!sRecord.getYear().isEmpty()) {
////                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////                } else {
////                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////                    res.getWriter().write("{\"message\":\"Invalid inputs supplied\"}");
////                }
////                break;
////            default:
////                break;
////        }
//
//        res.setStatus(HttpServletResponse.SC_CREATED);
//        return;
//    }
//}