import java.util.ArrayList;
import java.util.HashMap;


public class ANDTree
{
    private ArrayList<Slot> state = new ArrayList<Slot>();
    // private ArrayList<HashMap> state = new ArrayList<HashMap>();
    // private Asssignable assignment;
    // private Slot assignedTo;
    private ArrayList<Assignable> notAssigned = new ArrayList<Assignable>();
    private ANDTree parent;
    private int eval;
    private int depth = 0;

    // partialAssign == false
    public ANDTree(ANDTree root)
    {
        parent = root;
    }

    // partialAssign == true
    // give an ArrayList of HashMap (each one being an assignable and a slot)
    public ANDTree(ANDTree root, ArrayList<HashMap> partialAssign)
    {
        parent = root;
        state = partialAssign;
    }

    // !rootNode
    // give a HashMap of an Assignable and Slot
    public ANDTree(ANDTree parent, HashMap<Assignable, Slot> newAssignment)
    {
        this.parent = parent;
        state.add(newAssignment);
        // assignment = newAssignment;
        // assignedTo = slot;

        // depth = parent.getDepth() + 1; // dun need this
        // date + time = slot_id
        // add newAssignment to slot_id
    }

    public ArrayList<HashMap> getState()
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
