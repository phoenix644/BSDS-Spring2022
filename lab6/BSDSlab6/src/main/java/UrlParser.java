import org.apache.commons.validator.routines.UrlValidator;

import java.util.HashMap;
import java.util.Map;

public final class UrlParser {

    public static Map<String, String> parseUrl(String[] urlParts, String reqUrl) {

        Map<String, String> pathMap = new HashMap<>();
        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);

        // System.out.println(Arrays.toString(urlParts));
        // System.out.println(urlParts.length);

        // Only accept urlParts = [, {resortID}, seasons, {seasonID}, days, {dayID}, skiers, {skierID}]
        if (urlParts.length != 8 || !urlValidator.isValid(reqUrl)) {
            //throw new IllegalArgumentException("Invalid URL");
            pathMap.put("Invalid URL", "Invalid URL");
            return pathMap;
        }else {

            String resortID = urlParts[1];
            System.out.println(resortID);
            pathMap.put("resortID", resortID);

            for (int i = 2; i < urlParts.length; i++) {
                switch (urlParts[i]) {
                    case "seasons":
                        String seasonID = urlParts[i + 1];
                        pathMap.put("seasonID", seasonID);
                        System.out.println(seasonID);
                        break;
                    case "days":
                        String dayID = urlParts[i + 1];
                        pathMap.put("dayID", dayID);
                        System.out.println(dayID);
                        break;
                    case "skiers":
                        String skierID = urlParts[i + 1];
                        pathMap.put("skierID", skierID);
                        System.out.println(skierID);
                        break;
                    default:
                        continue;
                }
            }
            // last check if all info are parsed into hashmap
            if (pathMap.size() != 4) {
                throw new IllegalArgumentException("Passed URL expects 4 fields...");
            }

        }
        return pathMap;


    }
}
