import java.io.IOException;

/**
 * Created by Max Nuglisch on 01.02.2017.
 */
public class main {
    public static void main(String[] args)  throws IOException {

        timetable Plan = new timetable(3,"c",21,"schueler","SuS74!");
        Plan.update();

        System.out.println(Plan.getDate());
        System.out.println(Plan.getLevel());

        Plan.print();

        System.out.println();
        System.out.println("###################################################################################################");
        System.out.println("###################################################################################################");
        System.out.println();

        substitutionplan SubPlan = new substitutionplan(1,2,"Schueler","schueler","SuS74!");
        SubPlan.update();
        for(int i = 2; i < SubPlan.getMaxPlans()+1; i++){
            substitutionplan SubPlanToADD = new substitutionplan(i,2,"Schueler","schueler","SuS74!");
            SubPlanToADD.update();
            SubPlan.add(SubPlanToADD);
        }

        System.out.println("Plan: "+SubPlan.getPlanIndex()+" / "+SubPlan.getMaxPlans());
        System.out.println("Woche: "+SubPlan.getWeek());
        System.out.println("Für: "+SubPlan.getDate());
        SubPlan.print();
    }
}
