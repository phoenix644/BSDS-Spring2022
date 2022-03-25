import DAL.LiftRideDao;
import DAL.ResortsDao;
import Model.LiftRide;
import Model.Skier;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//interface MyFactory<T>
//{
//    T newObject();
//}

public class ResortController {

    String skierJsonString;
    Gson gson = new Gson();
    protected LiftRideDao liftRideDao = new LiftRideDao();
    protected ResortsDao resortsDao = new ResortsDao();
    private Integer skierID;
    private Integer resortID;
    private String seasonID;
    private String dayID;

//    @Override
//    public void init() throws ServletException {
//        liftRideDao = liftRideDao.getInstance();
//    }

    public Object getResort(Integer id) {
        return null;
    }

    public String getObject(String object) {

        if (object.equalsIgnoreCase("Skier")) {
            Skier skier = new Skier();
            Gson gson = new Gson();
            skierJsonString = gson.toJson(skier);

        } else if (object.equalsIgnoreCase("Resort")) {
//            LiftRide LiftRide = new LiftRide();
//            Gson gson = new Gson();
//            skierJsonString = gson.toJson(LiftRide);
//        }

//        else if (object.equalsIgnoreCase("LiftRide")){
//            LiftRide LiftRide = new LiftRide();
//            Gson gson = new Gson();
//            skierJsonString = gson.toJson(LiftRide);
//        }


        }

        return skierJsonString;
    }


    public String getObjectfromDB(Map parameters) throws SQLException {

        if (parameters.size() == 1) {
            skierID = Integer.parseInt((String) parameters.get("skierID"));


            List liftrides = liftRideDao.getSkierVertical(skierID);

            skierJsonString = gson.toJson(liftrides);
            return skierJsonString;

        }

        if (parameters.size() == 3) {

            resortID = Integer.parseInt ((String) parameters.get("resortID"));
            seasonID = (String) parameters.get("seasonID");
            dayID = (String) parameters.get("dayID");

            List resorts = resortsDao.getResorts(resortID, seasonID, dayID);

            skierJsonString = gson.toJson(resorts);
            return skierJsonString;

        }

        if (parameters.size() == 4) {
            skierID = Integer.parseInt((String) parameters.get("skierID"));
            resortID = Integer.parseInt ((String) parameters.get("resortID"));
            seasonID = (String) parameters.get("seasonID");
            dayID = (String) parameters.get("dayID");


            List liftrides;
            liftrides = liftRideDao.getSkierswithSkierID(resortID, seasonID, dayID, skierID);

            skierJsonString = gson.toJson(liftrides);


        }
        return skierJsonString;

    }
}

