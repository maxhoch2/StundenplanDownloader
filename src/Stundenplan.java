/**
 * Created by maxnu on 30.01.2017.
 */


import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Stundenplan {


        public static void main(String[] args) throws IOException {

            String username = "schueler";
            String password = "SuS74!";
            String login = username + ":" + password;
            String base64login = new String(Base64.encodeBase64(login.getBytes()));

            Document doc = Jsoup
                    .connect("http://gymnasium-wuerselen.de/untis/Schueler-Stundenplan/03/c/c00021.htm")
                    .header("Authorization", "Basic " + base64login)
                    .get();


            System.out.println("Downloading page...");
            System.out.println("Extracting Timetable information...");
            String str = "";
            //org.jsoup.nodes.Document doc = Jsoup.connect("http://schueler:SuS74!@gymnasium-wuerselen.de/untis/Schueler-Stundenplan/03/c/c00020.htm").get();
            org.jsoup.select.Elements rows = doc.select("tr");

            for(org.jsoup.nodes.Element row :rows)
            {
                org.jsoup.select.Elements columns = row.select("td");
                for (org.jsoup.nodes.Element column:columns)
                {
                    //System.out.print(column.text());
                    str = str + column.text();
                }
                //System.out.println();
                //System.out.println("--------------------------------------------------");
            }
            System.out.println(str);
            Scanner sc = new Scanner(str);
            String Pattern = "[^ ]+";
            while ((sc.hasNext(Pattern))) {

                System.out.println(sc.next(Pattern));
            }
            sc.close();

        }
    }

