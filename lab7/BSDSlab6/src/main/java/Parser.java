import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Parser {

    private String deserializedRequest;
    public String parse(HttpServletRequest request) {

//        JsonArray skier = new JsonArray(responseBody);
//        for (int i = 0; i < skier.length ; i++) {
//            JsonObject skier = skier.getAsJsonObject(i);
//        String body = null;
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = null;

            try {
                StringBuilder sb = new StringBuilder();
                String s;
                while ((s = request.getReader().readLine()) != null) {
                    sb.append(s);

//                InputStream inputStream = request.getInputStream();
//                if (inputStream != null) {
//                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    char[] charBuffer = new char[128];
//                    int bytesRead = -1;
//                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }
//                } else {
//                    stringBuilder.append("");
//
//                }


                //Student student = (Student) gson.fromJson(sb.toString(), Student.class);

               // Gson gson = new Gson();
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
