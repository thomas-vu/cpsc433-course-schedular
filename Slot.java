import java.util.List;
import java.util.ArrayList;

public interface Slot
{
    // private String day;
    // private int startTime;
    // private int min;
    // private int max;
    // private ArrayList<Assignable> preferences = new ArrayList<Assignable>();
    //
    // public Slots(String day, int start, int max, int min)
    // {
    //     this.day = day;
    //     this.startTime = start;
    //     this.max = max;
    //     this.min = min;
    // }

    public void addPreference(Assignable toAdd);
    // {
    //     preferences.add(toAdd);
    // }

    public ArrayList<Assignable> getPreferences();
    // {
    //     return preferences;
    // }

    // public void printStuff()
    // {
    //     System.out.println(day + " " + startTime + " " + max + " " +  min);
    // }
}
