/**
 * Created by maxnu on 30.01.2017.
 */


import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Stundenplan {


        public static void main(String[] args) throws IOException {

            String username = "schueler";
            String password = "SuS74!";
            String login = username + ":" + password;
            String base64login = new String(Base64.encodeBase64(login.getBytes()));

            Document doc = Jsoup
                    .connect("http://gymnasium-wuerselen.de/untis/Schueler-Stundenplan/03/c/c00005.htm")
                    .header("Authorization", "Basic " + base64login)
                    .get();


            System.out.println("Downloading page...");
            System.out.println("Extracting Timetable information...");
            String str = "";
            //org.jsoup.nodes.Document doc = Jsoup.connect("http://schueler:SuS74!@gymnasium-wuerselen.de/untis/Schueler-Stundenplan/03/c/c00018.htm").get();
            org.jsoup.select.Elements rows = doc.select("tr");

            for(org.jsoup.nodes.Element row :rows)
            {
                org.jsoup.select.Elements columns = row.select("td");
                for (org.jsoup.nodes.Element column:columns)
                {

                    if(column.text().contains(" ")){//.length() > 10){
                        //System.out.println(column.text());
                        str = str + " " + column.text();
                    }
                    if(column.text().length() == 0){
                        str = str + " " + "FREE";
                    }
                    if(Pattern.matches( "[0-9][0-9]?",  column.text())){
                        str = str + " Indicator:" + column.text();
                    }
                    if(column.text().equals("Pause")){
                        str = str + "Pause";
                    }
                    //System.out.println(column.text());
                    //str = str + column.text();
                }
                //System.out.println();
                //System.out.println("--------------------------------------------------");
            }
            System.out.println(str);
            /*Scanner sc = new Scanner(str);
            String Pattern = "[^ ]+ [^ ]+ [^ ]+";
            while ((sc.hasNext(Pattern))) {

                System.out.println(sc.next(Pattern));
            }
            sc.close();*/

            String[][] plan = new String[12][6];
            plan[0][0] = " ";
            plan[0][1] = "Montag";
            plan[0][2] = "Dienstag";
            plan[0][3] = "Mittwoch";
            plan[0][4] = "Donnerstag";
            plan[0][5] = "Freitag";

            String rooms = "[0-9]{3}|A[0-9]+|PH[0-9]|NW|CH[0-9]|BI[0-9]|KRH[0-9]|KU[0-9]|MU[0-9]|THWR[0-9]|AQUA[0-9]|THGym|Aula|Garte";
            String courses = "GK[0-9]+|LK[0-9]+|WPI|WPII|RELI|AG|BLZ";
            String subjects = "(GE|SP|M|PH|E|CH|MU|D|KU|EK|PW|ECDL|BI|tauch|XYZ[0-2]|LZ/Spr|LZ-MD)";


            Matcher m = Pattern.compile("(Pause|Indicator:[0-9][0-9]?|FREE|"+courses+"|[^ ]+ [^ ]+ ("+rooms+"))")//|("+subjects+") [^ ]+ "+rooms+")")
                    .matcher(str);
            List<String> planList= new LinkedList<String>();
            while (m.find()) {
                planList.add(m.group());
                System.out.println(m.group());
            }

            int currentIndicator = 0;
            int currentLine = 0;
            //get first indicator
            //while(!planList.get(currentLine).equals("Indicator:"+currentIndicator)) currentLine++;
            //go to end of indicator
            //while(planList.get(currentLine).equals("Indicator:"+currentIndicator)) currentLine++;
            //int row = 1;
            //plan[row][0] = ""+currentIndicator;

            for(int row = 1; row < plan.length; row++){
                //get indicator
                //while(!planList.get(currentLine).equals("Indicator:"+currentIndicator)) currentLine++;

                currentIndicator++;
                //System.out.println(currentIndicator);
                while(!planList.get(currentLine).equals("Indicator:"+currentIndicator)){
                    currentLine++;
                    //System.out.println(planList.get(currentLine));
                }
                currentLine++;
                currentLine++;
                plan[row][0] = ""+currentIndicator;


                for(int i = 1;i<plan[0].length;i++){
                    if(row == 6 && plan[5][i].startsWith("%")){
                        i++;
                        if(i>=plan[0].length)break;
                        System.out.println("Done");
                    }
                    String tmp;
                    if(i < planList.size()){
                        tmp = planList.get(currentLine);
                    }else break;



                    if(Pattern.matches(subjects+" [^ ]+ ("+rooms+")",  tmp)){
                        plan[row][i] = tmp;
                        currentLine++;
                    }else if(Pattern.matches(""+courses,  tmp)  ){
                        String tmp2 = "%"+tmp;
                        currentLine++;
                        while(Pattern.matches("[^ ]+ [^ ]+ ("+rooms+")",  planList.get(currentLine)) && !Pattern.matches(subjects+" [^ ]+ ("+rooms+")",  planList.get(currentLine))){
                            //System.out.println(planList.get(currentLine)+Pattern.matches( subjects+" [^ ]+ ("+rooms+")",  planList.get(currentLine)));
                            tmp2 = tmp2 + " "+planList.get(currentLine);
                            currentLine++;
                        }
                        plan[row][i] = tmp2;
                    }else if(tmp.equals("Pause")||tmp.equals("FREE")){
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
            }
            //System.out.println(currentLine);

        }
    }

