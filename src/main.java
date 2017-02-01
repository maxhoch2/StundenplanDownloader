import java.io.IOException;

/**
 * Created by Max Nuglisch on 01.02.2017.
 */
public class main {
    public static void main(String[] args)  throws IOException {
        timetable Plan = new timetable(4,"c",5,"schueler","SuS74!");
        Plan.update();
        Plan.printPlan();
    }
}
