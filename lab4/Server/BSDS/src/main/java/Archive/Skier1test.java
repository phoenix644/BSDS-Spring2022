//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import com.google.gson.Gson;
//
//
////@WebServlet(name = "Archive.lab3.SkierServlet", value = "/Archive.lab3.SkierServlet")
//public class Skier1test extends HttpServlet {
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        res.setContentType("text/plain");
//        String urlPath = req.getPathInfo();
//
//        // check we have a URL!
//        if (urlPath == null || urlPath.isEmpty()) {
//            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            res.getWriter().write("missing paramterers");
//            return;
//        }
//
//        String[] urlParts = urlPath.split("/");
//        // and now validate url path and return the response status code
//        // (and maybe also some value if input is valid)
//
//        if (!isUrlValid(urlParts)) {
//            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        } else {
//
//            res.setStatus(HttpServletResponse.SC_OK);
//            // do any sophisticated processing with urlParts which contains all the url params
//            // TODO: process url params in `urlParts`
//            Student student = new Student();
//            Gson gson = new Gson();
//            String studentJsonString = gson.toJson(student);
//            PrintWriter out = res.getWriter();
//            res.setContentType("application/json");
//            res.setCharacterEncoding("UTF-8");
//            // out.print(studentJsonString);
//            //out.flush();
//            out.print("3457");
//
//        }
//    }
//
//    private boolean isUrlValid(String[] urlPath) {
//        // TODO: validate the request url path according to the API spec
//        String [] testurlpath = {"12","seasons","2019","days","1","skiers","123"};
//
//
//        if (urlPath.length != testurlpath.length+1) {
//            return true;
//
//        }
//        for(int i = 0; i < urlPath.length-1; i++) {
//
//            if (!(urlPath[i+1].equals(testurlpath[i]))) {
//                return true;
//            }
//        }
//
//        return true;
//    }
//
//    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//        res.setContentType("application/json");
//        Gson gson = new Gson();
//
//        String urlPath = req.getPathInfo();
//
//        if (urlPath == null || urlPath.isEmpty()) {
//            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            res.getWriter().write("missing paramterers");
//            return;
//        }
//
//        String[] urlParts = urlPath.split("/");
//        // and now validate url path and return the response status code
//        // (and maybe also some value if input is valid)
//
//        if (!isUrlValid(urlParts)) {
//            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//
//        try {
//            StringBuilder sb = new StringBuilder();
//            String s;
//            while ((s = req.getReader().readLine()) != null) {
//                sb.append(s);
//            }
//
//            Student student = (Student) gson.fromJson(sb.toString(), Student.class);
//
//            res.getOutputStream().print(gson.toJson(student));
//            res.getOutputStream().flush();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//        processRequest(req, res);
//    }
//
//
//}
