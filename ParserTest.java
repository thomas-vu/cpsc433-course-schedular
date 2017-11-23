import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ParserTest {

    private Parser p;
    private Field sc;

    @Before
    public void setUp() {
	p = new Parser();
	try {
	    sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }
    
    @Test
    public void dehumanizeTime() {
	assertEquals(800, Parser.dehumanizeTime("8:00"));
	assertEquals(950, Parser.dehumanizeTime("9:30"));
	assertEquals(950, Parser.dehumanizeTime("09:30"));
	assertEquals(1200, Parser.dehumanizeTime("12:00"));
	assertEquals(1650, Parser.dehumanizeTime("16:30"));
    }

    @Test
    public void humanizeTime() {
	assertEquals("08:00", Parser.humanizeTime(800));
	assertEquals("09:30", Parser.humanizeTime(950));
	assertEquals("12:00", Parser.humanizeTime(1200));
	assertEquals("16:30", Parser.humanizeTime(1650));
    }

    @Test
    public void parseNames() {
	Scanner fakeFileInput =
	    new Scanner(new ByteArrayInputStream("department1\ndepartment2".getBytes()));
	HashSet<String> names = new HashSet<String>();
	names.add("department1"); names.add("department2");

	try {
	    Method parseNamesMethod = p.getClass().getDeclaredMethod("parseNames");
	    parseNamesMethod.setAccessible(true);
	    sc.set(p, fakeFileInput);
	    
	    assertEquals(names, parseNamesMethod.invoke(p, null));
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseCourseSlot() {
	CourseSlot csFullArgs = new CourseSlot("MO", 800, 2, 1);
	CourseSlot csHalfArgs = new CourseSlot("TU", 1250);

	try {
	    Method parseCourseSlotMethod = p.getClass().getDeclaredMethod("parseCourseSlot",
									  new Class[] {String.class});
	    parseCourseSlotMethod.setAccessible(true);

	    assertEquals(csFullArgs, parseCourseSlotMethod.invoke(p, "MO,  8:00, 2, 1"));
	    assertEquals(csHalfArgs, parseCourseSlotMethod.invoke(p, "TU, 12:30"));
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseLabSlot() {
	LabSlot lsFullArgs = new LabSlot("MO", 800, 2, 1);
	LabSlot lsHalfArgs = new LabSlot("TU", 1250);

	try {
	    Method parseLabSlotMethod = p.getClass().getDeclaredMethod("parseLabSlot",
								       new Class[] {String.class});
	    parseLabSlotMethod.setAccessible(true);

	    assertEquals(lsFullArgs, parseLabSlotMethod.invoke(p, "MO,  8:00, 2, 1"));
	    assertEquals(lsHalfArgs, parseLabSlotMethod.invoke(p, "TU, 12:30"));
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseCourse() {
	Course course = new Course("CPSC", 413, 01);

	try {
	    Method parseCourseMethod = p.getClass().getDeclaredMethod("parseCourse",
								      new Class[] {String.class});
	    parseCourseMethod.setAccessible(true);

	    assertEquals(course, parseCourseMethod.invoke(p, "CPSC 413 LEC 01"));
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseLab() {
	Lab labFullArgs = new Lab("CPSC", 413, 01, 01);
	Lab labHalfArgs = new Lab("CPSC", 413, 01);

	try {
	    Method parseLabMethod = p.getClass().getDeclaredMethod("parseLab",
								   new Class[] {String.class});
	    parseLabMethod.setAccessible(true);

	    assertEquals(labFullArgs, parseLabMethod.invoke(p, "CPSC 413 LEC 01 TUT 01"));
	    assertEquals(labHalfArgs, parseLabMethod.invoke(p, "CPSC 413 TUT 01"));
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parsePreference() {
	Preference pref1 = new Preference(new CourseSlot("MO", 800),
					  new Course("CPSC", 203, 01),
					  100);
	Preference pref2 = new Preference(new LabSlot("TU", 1700),
					  new Lab("CPSC", 203, 95, 95),
					  25);

	try {
	    Method parsePreferenceMethod = p.getClass().getDeclaredMethod("parsePreference",
									  new Class[] {String.class});
	    parsePreferenceMethod.setAccessible(true);

	    assertEquals(pref1,
			 parsePreferenceMethod.invoke(p, "MO, 8:00, CPSC 203 LEC 01, 100"));
	    assertEquals(pref2,
			 parsePreferenceMethod.invoke(p, "TU, 17:00, CPSC 203 LEC 95 TUT 95, 25"));
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }
    
    @Test
    public void parseCourseSlots() {
	HashSet<CourseSlot> cSlots = new HashSet<CourseSlot>();
	CourseSlot cs1 = new CourseSlot("MO", 800, 2, 1);
	CourseSlot cs2 = new CourseSlot("TU", 800, 2, 1);
	CourseSlot cs3 = new CourseSlot("TU", 950, 5, 0);
	cSlots.add(cs1); cSlots.add(cs2); cSlots.add(cs3);
	String sampleInput = ("MO,  8:00, 2, 1\n" +
			      "TU,  8:00, 2, 1\n" +
			      "TU,  9:30, 5, 0\n");
	Scanner simulatedInput = new Scanner(new ByteArrayInputStream(sampleInput.getBytes()));

	try {
	    Field sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	    sc.set(p, simulatedInput);

	    assertEquals(cSlots, p.parseCourseSlots());
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseLabSlots() {
	HashSet<LabSlot> lSlots = new HashSet<LabSlot>();
	LabSlot ls1 = new LabSlot("MO", 800, 2, 1);
	LabSlot ls2 = new LabSlot("TU", 800, 2, 1);
	LabSlot ls3 = new LabSlot("FR", 1000, 2, 0);
	lSlots.add(ls1); lSlots.add(ls2); lSlots.add(ls3);
	String sampleInput = ("MO,  8:00, 2, 1\n" +
			      "TU,  8:00, 2, 1\n" +
			      "FR, 10:00, 0, 0\n");
	Scanner simulatedInput = new Scanner(new ByteArrayInputStream(sampleInput.getBytes()));

	try {
	    Field sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	    sc.set(p, simulatedInput);

	    assertEquals(lSlots, p.parseLabSlots());
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseCourses() {
	HashSet<Course> courses = new HashSet<Course>();
	Course c1 = new Course("CPSC", 203, 01);
	Course c2 = new Course("CPSC", 233, 02);
	Course c3 = new Course("SENG", 513, 01);
	courses.add(c1); courses.add(c2); courses.add(c3);
	String sampleInput = ("CPSC 203 LEC 01\n" +
			      "CPSC 233 LEC 02\n" +
			      "SENG 513 LEC 01");
	Scanner simulatedInput = new Scanner(new ByteArrayInputStream(sampleInput.getBytes()));

	try {
	    Field sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	    sc.set(p, simulatedInput);

	    assertEquals(courses, p.parseCourses());
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseLabs() {
	HashSet<Lab> labs = new HashSet<Lab>();
	Lab c1 = new Lab("CPSC", 203, 01, 01);
	Lab c2 = new Lab("CPSC", 233, 02);
	Lab c3 = new Lab("CPSC", 441, 06, 02);
	Lab c4 = new Lab("SENG", 513, 01);
	labs.add(c1); labs.add(c2); labs.add(c3); labs.add(c4);
	String sampleInput = ("CPSC 203 LEC 01 TUT 01\n" +
			      "CPSC 233 TUT 02\n" +
			      "CPSC 441 LEC 02 TUT 06\n" +
			      "SENG 513 TUT 01");
	Scanner simulatedInput = new Scanner(new ByteArrayInputStream(sampleInput.getBytes()));

	try {
	    Field sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	    sc.set(p, simulatedInput);

	    assertEquals(labs, p.parseLabs());
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseAssignablePairs() {
	HashMap<Assignable, Assignable> pairs = new HashMap<Assignable, Assignable>();
	pairs.put(new Course("CPSC", 313, 01),
		  new Course("CPSC", 331, 01));
	pairs.put(new Course("CPSC", 441, 01),
		  new Course("CPSC", 457, 02));
	pairs.put(new Lab("SENG", 511, 01),
		  new Lab("SENG", 511, 02));
	pairs.put(new Lab("CPSC", 411, 01, 01),
		  new Lab("CPSC", 411, 02, 01));
	pairs.put(new Lab("CPSC", 589, 02),
		  new Course("CPSC", 591, 01));
	String sampleInput = ("CPSC 313 LEC 01, CPSC 331 LEC 01\n" + 
			      "CPSC 441 LEC 01, CPSC 457 LEC 02\n" +
			      "SENG 511 TUT 01, SENG 511 TUT 02\n" +
			      "CPSC 411 LEC 01 TUT 01, CPSC 411 LEC 01 TUT 02\n" +
			      "CPSC 589 TUT 02, CPSC 591 LEC 01");
	Scanner simulatedInput = new Scanner(new ByteArrayInputStream(sampleInput.getBytes()));

	try {
	    Field sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	    sc.set(p, simulatedInput);

	    assertEquals(pairs, p.parseAssignablePairs());
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parseAssignableSlotPairs() {
	HashMap<Assignable, Slot> pairs = new HashMap<Assignable, Slot>();
	pairs.put(new Course("CPSC", 433, 01),
		  new CourseSlot("MO", 800));
	pairs.put(new Lab("CPSC", 589, 02),
		  new LabSlot("FR", 1000));
	String sampleInput = ("CPSC 433 LEC 01, MO, 8:00\n" + 
			      "CPSC 589 TUT 02, FR, 10:00");
	Scanner simulatedInput = new Scanner(new ByteArrayInputStream(sampleInput.getBytes()));

	try {
	    Field sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	    sc.set(p, simulatedInput);

	    assertEquals(pairs, p.parseAssignableSlotPairs());
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Test
    public void parsePreferences() {
	HashSet<Preference> preferences = new HashSet<Preference>();

	preferences.add(new Preference(new CourseSlot("MO", 800),
				       new Course("CPSC", 203, 01),
				       100));
	preferences.add(new Preference(new LabSlot("TU", 1700),
				       new Lab("CPSC", 203, 95, 95),
				       25));
	String sampleInput = ("MO, 8:00, CPSC 203 LEC 01, 100\n" +
			      "TU, 17:00, CPSC 203 LEC 95 TUT 95, 25");
	Scanner simulatedInput = new Scanner(new ByteArrayInputStream(sampleInput.getBytes()));

	try {
	    Field sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	    sc.set(p, simulatedInput);

	    assertEquals(preferences, p.parsePreferences());
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }
}
