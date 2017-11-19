import java.lang.*;
import java.util.*;

public class Parser {

    private Scanner sc = new Scanner(System.in);
    
    private HashSet<String> parseNames() {
	HashSet<String> names = new HashSet<String>();
	while (sc.hasNextLine()) {
	    String name = sc.nextLine();
	    if (name.equals(""))
		break;
	    names.add(name);
	}
	return names;
    }

    private CourseSlot parseCourseSlot(String slotInfo) throws IllegalArgumentException  {
	String[] splitInfo = slotInfo.split(", {1,2}");
	if (splitInfo.length() == 2)
	    return new CourseSlot(splitInfo[0], Parser.dehumanize(splitInfo[1]));
	return new CourseSlot(splitInfo[0],
			      Parser.dehumanize(splitInfo[1]),
			      Integer.parseInt(splitInfo[2]),
			      Integer.parseInt(splitInfo[3]));
    }

    private HashSet<CourseSlot> parseCourseSlots() throws IllegalArgumentException {
	HashSet<CourseSlot> courseSlots = new HashSet<CourseSlot>();
	while (sc.hasNextLine()) {
	    String slotInfo = sc.nextLine();
	    if (slotInfo.equals(""))
		break;
	    courseSlots.add(parseCourseSlot(slotInfo));
	}
	return courseSlots;
    }
    
    private LabSlot parseLabSlot(String slotInfo) throws IllegalArgumentException {
	String [] splitInfo = slotInfo.split(", {1,2}");
	if (splitInfo.length() == 2)
	    return new LabSlot(splitInfo[0], Parser.dehumanize(splitInfo[1]));
	return new LabSlot(splitInfo[0],
			   Parser.dehumanize(splitInfo[1]),
			   Integer.parseInt(splitInfo[2]),
			   Integer.parseInt(splitInfo[3]));
    }
    
    private HashSet<LabSlot> parseLabSlots() throws IllegalArgumentException {
	HashSet<LabSlot> labSlots = new HashSet<LabSlot>();
	while (sc.hasNextLine()) {
	    String slotInfo = sc.nextLine();
	    if (slotInfo.equals(""))
		break;
	    labSlots.add(parseLabSlot(slotInfo));
	}
	return labSlots;
    }

    private Course parseCourse(String courseInfo) throws IllegalArgumentException {	
	String[] splitInfo = courseInfo.split(" ");
	return new Course(splitInfo[0],
			  Integer.parseInt(splitInfo[1]),
			  Integer.parseInt(splitInfo[3]));
    }
    
    private HashSet<Course> parseCourses() throws IllegalArgumentException {
	HashSet<Course> courses = new HashSet<Course>();
	while (sc.hasNextLine()) {
	    String courseInfo = sc.nextLine();
	    if (courseInfo.equals(""))
		break;
	    courses.add(parseCourse(courseInfO));
	}
	return courses;
    }

    private Lab parseLab(String labInfo) throws IllegalArgumentException {
	String[] splitInfo = courseInfo.split(" ");
	if (splitInfo.length() == 4)
	    return new Lab(splitInfo[0],
			   Integer.parseInt(splitInfo[1]),
			   Integer.parseInt(splitInfo[3]));
	return new Lab(splitInfo[0],
		       Integer.parseInt(splitInfo[1]),
		       Integer.parseInt(splitInfo[3]),
		       Integer.parseInt(splitInfo[5]));
    }

    private HashSet<Lab> parseLabs() throws IllegalArgumentException {
	HashSet<Lab> labs = new HashSet<Lab>();
	while (sc.hasNextLine()) {
	    String labInfo = sc.nextLine();
	    if (labInfo.equals(""))
		break;
	    labs.add(parseLab(labInfo));
	}
	return labs;
    }
    
    // Use for parsing "Not compatible" and "Pair" sections
    private HashMap<Assignable, Assignable> parseAssignablePairs()
	throws IllegalArgumentException {
	HashMap<Assignable, Assignable> assignablePairs =
	    new HashMap<Assignable, Assignable>();
	while (sc.hasNextLine()) {
	    String line = sc.nextLine();
	    if (line.equals(""))
		break;
	    
	    String firstInfo = line.split(", ")[0];
	    String secondInfo = line.split(", ")[1];
	    if (firstInfo.contains("TUT"))
		Assignable first = parseLab(firstInfo);
	    else
		Assignable first = parseCourse(firstInfo);
	    if (secondInfo.contains("TUT"))
		Assignable second = parseLab(secondInfo);
	    else
		Assignable second = parseCourse(secondInfo);
	    assignablePairs.put(first, second);
	}
	return assignablePairs;
    }

    // Use for parsing "Unwanted" and "Partial Assignments" sections
    private HashMap<Assignable, Slot> parseAssignableSlotPairs()
	throws IllegalArgumentException {
	HashMap<Assignable, Slot> assignableSlotPairs = new HashMap<Assignable, Slot>();
	while (sc.hasNextLine()) {
	    String line = sc.nextLine();
	    if (line.equals(""))
		break;

	    String[] splitLine = line.split(", ");
	    String firstInfo = splitLine[0];
	    String secondInfo = splitLine[1] + ", " + splitLine[2];

	    if (firstInfo.contains("TUT")) {
		Assignable first = parseLab(firstInfo);
		Assignable second = parseLabSlot(secondInfo);
	    }
	    else {
		Assignable first = parseCourse(firstInfo);
		Assignable second = parseCourseSlot(secondInfo);
	    }
	    assignableSlotPairs.put(first, second);
	}
	return assignableSlotPairs
    }
        
    private static int dehumanizeTime(String time) throws IllegalArgumentException {
	time = time.length() == 5 ? time : "0" + time;
	if (Integer.parseInt(time.substring(0, 2)) > 24)
	    throw new IllegalArgumentException("Times must be in increments of " +
					       "30 minutes from the hour.");
	else if (time.endsWith(":00"))
	    return Integer.parseInt(time.substring(0, 2) + "00");
	else if (time.endsWith(":30"))
	    return Integer.parseInt(time.substring(0, 2) + "50");
	else
	    throw new IllegalArgumentException("Times must be in increments of " +
					       "30 minutes from the hour.");
    }

    private static String humanizeTime(int time) throws IllegalArgumentException {
	if (time % 50 != 0 || time < 0 || time > 2400)
	    throw new IllegalArgumentException("Times must be in increments of " +
					       "30 minutes from the hour.");
	String timeString = "" + time;
	for (int i = 0; i < 4 - timeString.length(); i++)
	    timeString = "0" + timeString;
	if (time % 100 == 0)
	    return timeString.substring(0,2) + ":00";
	else
	    return timeString.substring(0,2) + ":30";
    }
}
