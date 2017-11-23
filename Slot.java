import java.util.List;
import java.util.ArrayList;

public interface Slot
{
    public void addPair(Assignable toAdd);
    public void addAssigned(Assignable toAdd);

    public int getMax();
    public int getMin();    

    public ArrayList<Assignable> getPairs();
    public ArrayList<Assignable> getAssigned();
    
    public String getSlotID();
}
