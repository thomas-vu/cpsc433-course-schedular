import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Course implements Assignable
{
    private String courseType;
    private int courseNumber;
    private int section;
    private HashMap<Assignable, Assignable> notCompatible = new HashMap<Assignable, Assignable>();
    private List<Slot> unwanted = new ArrayList<Slot>();
    private HashMap<Assignable, Slot> pairs = new HashMap<Assignable, Slot>();

    public Course(String name, int number, int section)
    {
        courseType = name;
        courseNumber = number;
        this.section = section;
    }

    public String getCourseType()
    {
        return courseType;
    }

    public int getCourseNumber()
    {
        return courseNumber;
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

    public List<Slot> getUnwantedSlots()
    {
        return unwanted;
    }

    public void addUnwantedSlot(Slot toAdd)
    {
        unwanted.add(toAdd);
    }

    public HashMap<Assignable, Slot> getPairs()
    {
        return pairs;
    }

    public void addPair(Assignable assign1, Slot slot)
    {
        pairs.put(assign1, slot);
    }
}
