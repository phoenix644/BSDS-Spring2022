import Archive.lab3.UrlParser;
import org.junit.Test;

public class UrlParserTest {

    @Test
    public void parseUrl() {
        UrlParser.parseUrl(new String[] {}, "");

        // reqUrl  =
        // http://localhost:8080/BSDS/skiers/1/seasons/2019/days/3/skiers/33 --pass
        // http://localhost:8080/lab3_war_exploded/skiers//////1/seasons/2019/days/1/skiers/123 --fails
        // http://localhost:8080/lab3_war_exploded/skiers/seasons/2019/days/3/skiers/  --fails
    }
}
