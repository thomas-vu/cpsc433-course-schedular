import java.util.ArrayList;


public class ANDTree
{
    private ArrayList<Slot> state = new ArrayList<Slot>();
    private Asssignable assignment;
    private Slot assignedTo;
    private ArrayList<Assignable> notAssigned = new ArrayList<Assignable>();
    private ANDTree parent;
    private int eval;
    private int depth = 0;

    public ANDTree()
    {
        // cry
    }

    // used for creating leafs
    public ANDTree(ANDTree parent, Slot slot, Asssignable newAssignment)
    {
        this.parent = parent;
        assignment = newAssignment;
        assignedTo = slot;
        // depth = parent.getDepth() + 1; // dun need this
        // date + time = slot_id
        // add newAssignment to slot_id
    }

    public ArrayList<Slot> getState()
    {
        return state;
    }

    public ArrayList<Assignable> getUnassigned()
    {
        return notAssigned;
    }

    public int getEval()
    {
        return eval;
    }

    public void setEval(int newValue)
    {
        eval = newValue;
    }

    public int getDepth()
    {
        if (parent != null)
            return parent.getDepth() + 1;

        return depth;
    }
}
