import java.util.List;
import java.util.ArrayList;

public interface Slot
{
    public void addPreference(Assignable toAdd);
    public void getPreference(Assignable toAdd);

    public void addAssigned(Assignable toAdd);
    public void getAssigned(Assignable toAdd);

    public int getMax();
    public int getMin();    

    public ArrayList<Assignable> getPreferences();

    public String getSlotID();
}
