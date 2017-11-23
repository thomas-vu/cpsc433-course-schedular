public class Preference {

    private Slot slot;
    private Assignable assignable;
    private int score;
    
    public Preference(Slot s, Assignable cl, int score) {
	slot = s;
	assignable = cl;
	this.score = score;
    }

    public Slot getSlot() {
	return slot;
    }

    public Assignable getAssignable() {
	return assignable;
    }

    public int getScore() {
	return score;
    }
}
