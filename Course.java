import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Course implements Assignable
{
    private String course_id;
    private int section;
    private HashMap<Assignable, Assignable> notCompatible = new HashMap<Assignable, Assignable>();
    private List<Slots> unwanted = new ArrayList<Slots>();
    private HashMap<Assignable, Slots> pairs = new HashMap<Assignable, Slots>();

    public Course(String name, int number)
    {
        course_id = name;
        section = number;
    }

    public String getID()
    {
        return course_id;
    }

    public int getSection()
    {
        return section;
    }

    public HashMap<Assignable, Assignable> getNotCompatible()
    {
        return notCompatible;
    }

    public void addNotCompatible(Assignable assign1, Assignable assign2)
    {
        notCompatible.put(assign1, assign2);
    }

    public List<Slots> getUnwantedSlots()
    {
        return unwanted;
    }

    public void addUnwantedSlot(Slots toAdd)
    {
        unwanted.add(toAdd);
    }

    public HashMap<Assignable, Slots> getPairs()
    {
        return pairs;
    }

    public void addPair(Assignable assign1, Slots slot)
    {
        pairs.put(assign1, slot);
    }
}
