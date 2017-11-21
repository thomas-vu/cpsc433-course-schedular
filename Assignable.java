import java.util.List;
import java.util.HashMap;

public interface Assignable
{
    public String getID();

    public int getSection();

    public HashMap<Assignable, Assignable> getNotCompatible();

    public void addNotCompatible(Assignable assign1, Assignable assign2);

    public List<Slots> getUnwantedSlots();

    public void addUnwantedSlot(Slots toAdd);

    public HashMap<Assignable, Slots> getPairs();

    public void addPair(Assignable assign1, Slots slot);

}
