import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Parser {

    private String deserializedRequest;
    public String parse(HttpServletRequest request) {

//        JsonArray skier = new JsonArray(responseBody);
//        for (int i = 0; i < skier.length ; i++) {
//            JsonObject skier = skier.getAsJsonObject(i);

            try {
                StringBuilder sb = new StringBuilder();
                String s;
                while ((s = request.getReader().readLine()) != null) {
                    sb.append(s);
                }

                //Student student = (Student) gson.fromJson(sb.toString(), Student.class);

                //Gson gson = new Gson();
                //String json = gson.toJson(target, listType);
                // Deserialize the gson to arraylist
                //deserializedRequest = gson.fromJson(sb.toString(), ArrayList.class);
                deserializedRequest = sb.toString();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return deserializedRequest;

    }


    }
