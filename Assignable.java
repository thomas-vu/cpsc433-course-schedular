import java.util.List;
import java.util.HashMap;

public interface Assignable
{
    public String getID();

    public int getSection();

    public HashMap<Assignable, Assignable> getNotCompatible();

    public void addNotCompatible(Assignable assign1, Assignable assign2);

    public List<Slot> getUnwantedSlots();

    public void addUnwantedSlot(Slot toAdd);

    public HashMap<Assignable, Slot> getPairs();

    public void addPair(Assignable assign1, Slot slot);

}
