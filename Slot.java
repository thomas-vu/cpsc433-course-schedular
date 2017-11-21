import java.util.List;
import java.util.ArrayList;

public interface Slot
{
    
    public void addPreference(Assignable toAdd);

    public ArrayList<Assignable> getPreferences();
}
