import java.util.List;
import java.util.ArrayList;

public class LabSlot implements Slot
{
    private String day;
    private int startTime;
    private int min;
    private int max;
    private Assignable[] assigned;    // list of items assigned to this slot. Not sure if we need but will add for now
    private ArrayList<Assignable> preferences = new ArrayList<Assignable>();

    public LabSlot(String day, int start, int max, int min)
    {
        this.day = day;
        this.startTime = start;
        this.max = max;
        this.min = min;
        assigned = 0;
    }

    public void addPreference(Assignable toAdd)
    {
        preferences.add(toAdd);
    }

    public ArrayList<Assignable> getPreferences()
    {
        return preferences;
    }

    // Can delete if we are not we are not keeping a list of assigned items
    public Assignable[] getAssigned()
    {
        return assigned;
    }
}
