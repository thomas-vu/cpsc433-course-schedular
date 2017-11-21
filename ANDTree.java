import java.util.ArrayList;


public class ANDTree
{
    private ArrayList<Slot> assigned = new ArrayList<Slot>();
    private ArrayList<Assignable> notAssigned = new ArrayList<Assignable>();
    private ANDTree parent;

    public ANDTree()
    {

    }

    // used for creating leafs
    public ANDTree(ANDTree parent)
    {
        this.parent = parent;
    }

    public ArrayList<Slot> getAssigned()
    {
        return assigned;
    }

    public ArrayList<Assignable> getUnassigned()
    {
        return notAssigned;
    }
}
