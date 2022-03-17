import Model.LiftRide;
import Model.Skier;
import com.google.gson.Gson;

//interface MyFactory<T>
//{
//    T newObject();
//}

public class ResortController {

    String skierJsonString;

    public Object getResort(Integer id) {
        return null;
    }

    public String getObject(String object) {

        if (object.equalsIgnoreCase("Skier")) {
            Skier skier = new Skier();
            Gson gson = new Gson();
            skierJsonString = gson.toJson(skier);

        }

//        else if (object.equalsIgnoreCase("LiftRide")){
//            LiftRide LiftRide = new LiftRide();
//            Gson gson = new Gson();
//            skierJsonString = gson.toJson(LiftRide);
//        }

        return skierJsonString;

    }


    //public void sendto(map<> , string)


}

