//import com.google.gson.Gson;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Map;
//
//public class StringGsonParser {
//
//    public String parse(HttpServletRequest request, Map parameters) throws IOException {
//
//        StringBuilder requestStringBuilder = new StringBuilder();
//        String s;
//        while ((s = request.getReader().readLine()) != null) {
//            requestStringBuilder.append(s);
//        }
//       String  requestString = requestStringBuilder.toString();
//        requestString = requestStringBuilder.substring(1, requestStringBuilder.length()-1);           //remove curly brackets
//        String[] keyValuePairs = requestString.split(",");              //split the string to creat key-value pairs
//
//        for(String pair : keyValuePairs)                        //iterate over the pairs
//        {
//            String[] entry = pair.split(":");                   //split the pairs to get key and value
//            parameters.put(entry[0].trim().replace("\"",""),
//                    entry[1].trim().replace("\"",""));
//
//        }
//
//
//        Gson gson = new Gson();
//        String jsonStrignurl = gson.toJson(parameters);
////        LiftRide deserializedRequest = gson.fromJson(jsonStrignurl, LiftRide.class);
////        return deserializedRequest;
//        return jsonStrignurl;
//
//
//
//    }
//
//
//}
//
//
////        BufferedReader br = null;
////
////
////
////            // create file object
////            //File file = new File(filePath);
////
////            // create BufferedReader object from the File
////            //br = new BufferedReader(new FileReader(file));
////            br = request.getReader();
////            String line = null;
////
////            // read file line by line
////            while ((line = br.readLine()) != null) {
////
////                // split the line by :
////                String[] parts = line.split(" ");
////
////                // first part is name, second is number
////                String name = parts[0].trim();
////                String number = parts[1].trim();
////
////                // put name, number in HashMap if they are
////                // not empty
////                if (!name.equals("") && !number.equals(""))
////                    parameters.put(name, number);
////            }
//
////        BufferedReader reader = request.getReader();
////        String jsonStringBody = gson.toJson(reader);
//
//
//       // System.out.println(jsonStringBody);
//
//
//        //BufferedReader reader = request.getReader();
//
////        StringBuilder stringRequest = new StringBuilder();
////        String s;
////        while ((s = request.getReader().readLine()) != null) {
////            stringRequest.append(s);
////        }
////        System.out.println(stringRequest);
////String requestBody = stringRequest+jsonString;
////            System.out.println(requestBody);
//
//
////        String requestString = new Gson().toJson(deserializedRequest);
////        sendtoQueue(requestString);
//
//
//
//
////        JsonArray skier = new JsonArray(responseBody);
////        for (int i = 0; i < skier.length ; i++) {
////            JsonObject skier = skier.getAsJsonObject(i);
////        String body = null;
////        StringBuilder stringBuilder = new StringBuilder();
////        BufferedReader bufferedReader = null;
//
////            try {
////        StringBuilder sb = new StringBuilder();
////        String s;
////        while ((s = request.getReader().readLine()) != null) {
////            sb.append(s);
//
////                InputStream inputStream = request.getInputStream();
////                if (inputStream != null) {
////                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
////                    char[] charBuffer = new char[128];
////                    int bytesRead = -1;
////                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
////                        stringBuilder.append(charBuffer, 0, bytesRead);
//
//     //**   }
////                } else {
////                    stringBuilder.append("");
////
////                }
//
//
//        //Student student = (Student) gson.fromJson(sb.toString(), Student.class);
//
//        // Gson gson = new Gson();
//        //String json = gson.toJson(target, listType);
//        // Deserialize the gson to arraylist
//        //deserializedRequest = gson.fromJson(sb.toString(), ArrayList.class);
//
//        //** deserializedRequest = sb.toString();
//
////**
////    } catch (
////    IOException e) {
////        e.printStackTrace();
////    }
//
//
