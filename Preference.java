import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
	if (obj == null || Preference.class != obj.getClass())
	    return false;
	Preference other = (Preference) obj;
	if (!slot.equals(other.slot) ||
	    !assignable.equals(other.assignable) ||
	    score != other.score)
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	return Objects.hash(slot, assignable, score);
    }
    
    @Override
    public String toString() {
	return slot.toString() + " " + assignable.toString() + " " + score;
    }
}
