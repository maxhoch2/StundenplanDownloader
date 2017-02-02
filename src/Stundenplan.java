/**
 * Created by maxnu on 30.01.2017.
 */


import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Stundenplan {


        public static void main(String[] args) throws IOException {

            String username = "schueler";
            String password = "SuS74!";
            String url = "http://gymnasium-wuerselen.de/untis/Schueler/f1/subst_002.htm";
            String login = username + ":" + password;
            String base64login = new String(Base64.encodeBase64(login.getBytes()));

            Document doc = Jsoup
                    .connect(url)
                    .header("Authorization", "Basic " + base64login)
                    .get();

            System.out.println("URL: " + url);
            System.out.println("Downloading page...");
            System.out.println("Extracting Timetable information...");


            org.jsoup.select.Elements rows = doc.select("tr");
            List<String> planList= new LinkedList<String>();

            for(org.jsoup.nodes.Element row :rows)
            {
                org.jsoup.select.Elements columns = row.select("td");
                for (org.jsoup.nodes.Element column:columns)
                {
                    System.out.print(" "+column.text());
                }
                System.out.println();
            }
            /*for(int i = 0; i<planList.size();i++){
                System.out.println(planList.get(i));
            }

            String[][] plan = new String[12][6];
            plan[0][0] = " ";
            plan[0][1] = "Montag";
            plan[0][2] = "Dienstag";
            plan[0][3] = "Mittwoch";
            plan[0][4] = "Donnerstag";
            plan[0][5] = "Freitag";


            int currentIndicator = 0;
            int currentLine = 0;


            for(int row = 1; row < plan.length; row++){
                currentIndicator++;
                while(!planList.get(currentLine).equals("Indicator:"+currentIndicator)) currentLine++;
                currentLine++;
                currentLine++;

                plan[row][0] = ""+currentIndicator;


                for(int i = 1;i<plan[0].length;i++){
                    if(row == 6 && i<=plan[0].length && Pattern.matches("[^ ]+ [^ ]+ [^ ]+ .+", plan[5][i])){
                        i++;
                        System.out.println("Done");
                    }
                    String tmp;
                    if(i < planList.size()){
                        tmp = planList.get(currentLine);
                    }else break;


                    if(Pattern.matches("[^ ]+ [^ ]+ [^ ]+.+",  tmp)){
                        plan[row][i] = tmp;
                        currentLine++;
                    }else if(tmp.equals("PAUSE")||tmp.equals("FREE")){
                        plan[row][i] = tmp;
                        currentLine++;
                        currentLine++;
                    }else if(tmp.equals("Indicator:"+currentIndicator))break;
                    else currentLine++;

                }
            }




            for(int i = 0; i<plan.length; i++)
            {
                for(int j = 0; j<plan[0].length; j++)
                {
                    System.out.print(" | "+plan[i][j]);
                }
                System.out.println();
                System.out.println();
            }*/

        }

    }

