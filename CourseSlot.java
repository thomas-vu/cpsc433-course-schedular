import java.util.List;
import java.util.ArrayList;

public class CourseSlot implements Slot
{
    private String day;
    private int startTime;
    private int min;
    private int max;
    private String slotID;
    private ArrayList<Assignable> preferences = new ArrayList<Assignable>();

    public CourseSlot(String day, int start)
    {
        this.day = day;
        this.startTime = start;
    }

    public CourseSlot(String day, int start, int max, int min)
    {
        this.day = day;
        this.startTime = start;
        this.max = max;
        this.min = min;
        slotID = day + " " + String.valueOf(start);
    }

    public void addPreference(Assignable toAdd)
    {
        preferences.add(toAdd);
    }

    public ArrayList<Assignable> getPreferences()
    {
        return preferences;
    }

    public String getSlotID()
    {
        return slotID;
    }
}
