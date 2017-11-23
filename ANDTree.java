import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class ANDTree
{
	private Random rand = new Random();
    // private ArrayList<Slot> state2 = new ArrayList<Slot>();
//    private ArrayList<HashMap> state = new ArrayList<HashMap>();
	private ArrayList<Slot> state = new ArrayList<Slot>();
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
    
    public ArrayList<ANDTree> div() {
		int nRemain = notAssigned.length;
		int nSlots = state.length;
		if (nRemain < 1) return new ArrayList<ANDTree>();
		r = rn.nextInt(nRemain);										// r is index of randomly chosen element
		private ArrayList<ANDTree> childTrees;
		private Assignable chosen = noAssigned[r]; 						// chooses randomly
		
		for (int i=0; i<numStates; i++) {
			if (state[i].length < state[i].getMax()) {						// if slot is not full
				if (constraintCheck(state[i], chosen)) {				// IF ASSIGNMENT PASSES CONSTRAINT CHECK // TO BE IMPLEMENTED
					private ANDTree tempTree = new AndTree(this);		//  initialize a temporary tree
					tempTree.setState(state[i], chosen);				//  set state of new Tree
					tempTree.setUnassigned(unassigned.remove(r));		//  set unassigned list of list of unchosen assignables
					tempTree.setEval(evalFunction);						//	GIVES NEW TREE AN EVAL // TO BE IMPLEMENTED
					tempTree.setDepth(depth+1);						//	increment depth of new Tree
					childTrees.add(tempTree);
				}
			}
		}
		return childTrees;
	}
}
