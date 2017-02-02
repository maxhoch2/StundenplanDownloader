import java.io.IOException;

/**
 * Created by Max Nuglisch on 01.02.2017.
 */
public class main {
    public static void main(String[] args)  throws IOException {
        timetable Plan = new timetable(4,"c",20,"schueler","SuS74!");
        Plan.update();
        //Plan.print();
        //System.out.println(Plan.getDate());
        //System.out.println(Plan.getLevel());

        substitutionplan SubPlan = new substitutionplan(1,1,"Schueler","schueler","SuS74!");
        SubPlan.update();

        substitutionplan SubPlanToADD = new substitutionplan(2,1,"Schueler","schueler","SuS74!");
        SubPlanToADD.update();

        SubPlan.add(SubPlanToADD);

        SubPlan.print();
        System.out.println(SubPlan.getPlanIndex()+" / "+SubPlan.getMaxPlans());
        System.out.println(SubPlan.getWeek());
        System.out.println(SubPlan.getDate());
    }
}
