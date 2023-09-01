package opg7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Scraper {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://dis.students.dk/example1.php");
            InputStreamReader r = new InputStreamReader(url.openStream());
            BufferedReader in = new BufferedReader(r);
            String str;
            while ((str = in.readLine()) != null) {
                if (str.contains("This page has been seen")) {
                    String[] words = str.split(" ");
                    System.out.println(words[5]);
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
