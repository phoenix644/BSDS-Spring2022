import Model.LiftRide;
import Model.Resorts;
import Model.Skier;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class GsonParser {

    String jsonStringMessage;
    Gson gson = new Gson();
    Skier skier = new Skier();
//    JSONWriter rabbitmqJson = new JSONWriter();
//    String jsonmessage;

    public String getObject(String object) {

        if (object.equalsIgnoreCase("Skier")) {

            jsonStringMessage = gson.toJson(skier);

        }

//        else if (object.equalsIgnoreCase("LiftRide")){
//            LiftRide LiftRide = new LiftRide();
//            Gson gson = new Gson();
//            skierJsonString = gson.toJson(LiftRide);
//        }

        return jsonStringMessage;

    }


    public String submitLiftRide(HttpServletRequest request, Map parameters) throws IOException {

        PartialLiftRide plr = new Gson().fromJson(request.getReader(), PartialLiftRide.class);

        LiftRide lr = new LiftRide();

        lr.setSkierID(Integer.parseInt((String) parameters.get("skierID")));
        lr.setSeasonID((String) parameters.get("seasonID"));
        lr.setResortID(Integer.parseInt((String) parameters.get("resortID")));
        lr.setDayID((String) parameters.get("dayID"));

//        lr.setDayID(Integer.parseInt(parameters.get("dayID")));

        lr.setLiftID(plr.liftID);
        lr.setWaitTime(plr.waitTime);
        lr.setWaitTime(plr.time);

        //String jsonmessage = rabbitmqJson.write(lr);

        jsonStringMessage = gson.toJson(lr);
        return jsonStringMessage;

    }

    public String submitResorts(HttpServletRequest request, Map parameters) throws IOException {

        PartialResorts pResorts = new Gson().fromJson(request.getReader(), PartialResorts.class);

        Resorts resorts = new Resorts();

        resorts.setResortID(Integer.parseInt((String) parameters.get("resortID")));;

        resorts.setYear(pResorts.year);
        //String jsonmessage = rabbitmqJson.write(lr);

        jsonStringMessage = gson.toJson(resorts);
        return jsonStringMessage;

    }


    //public void sendto(map<> , string)

    static class PartialLiftRide {
        public int time;
        public int liftID;
        public int waitTime;
    }

    static class PartialResorts{
        private String year;
    }



}
