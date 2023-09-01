package opg7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ValutaKurserScraper {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://m.valutakurser.dk");
            InputStreamReader r = new InputStreamReader(url.openStream());
            BufferedReader in = new BufferedReader(r);
            String str;
            boolean firstFound = false;
            while ((str = in.readLine()) != null) {
                if (str.contains("href=\"/valuta/amerikanske-dollar/USD\"")) {
                    if (firstFound) {
                        // int indexOfActualValue = str.indexOf("Amerikanske dollar");
//                    String stringOfAmericano = str.substring(indexOfActualValue);
//                    System.out.println(stringOfAmericano);
                        String actualValueDiv = "actualValueContainer__2xLkB\">";
                        int start = str.indexOf(actualValueDiv) + actualValueDiv.length();
                        int end = str.indexOf("</div></div></a>");

                        String value = str.substring(start, end);

                        System.out.println(value);
                    }
                    firstFound = true;
                }
            }
            in.close();
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
