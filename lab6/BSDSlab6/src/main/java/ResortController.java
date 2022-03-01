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
        return skierJsonString;

    }
}

