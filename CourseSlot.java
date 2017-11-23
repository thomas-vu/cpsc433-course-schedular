import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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

    // public CourseSlot(String day, int start)
    // {
	// this.day = day;
	// this.startTime = start;
    // }

    public void addPreference(Assignable toAdd)
    {
        preferences.add(toAdd);
    }

    public String getDay()
    {
	return day;
    }

    public int getStartTime()
    {
	return startTime;
    }

    public ArrayList<Assignable> getPreferences()
    {
        return preferences;
    }

    @Override
    public boolean equals(Object obj)
    {
	if (obj == null || CourseSlot.class != obj.getClass())
	    return false;
	CourseSlot other = (CourseSlot) obj;
	if (!day.equals(other.getDay()) || startTime != other.getStartTime())
	    return false;
	return true;
    }

    @Override
    public int hashCode()
    {
	return Objects.hash(day, startTime);
    }

    @Override
    public String toString()
    {
	return day + " " + Parser.humanizeTime(startTime);
    }

    public String getSlotID()
    {
        return slotID;
    }
}
