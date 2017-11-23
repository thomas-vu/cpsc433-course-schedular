import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

import java.util.HashSet;
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
	    Method parseCourseSlotsMethod = p.getClass().getDeclaredMethod("parseCourseSlots");
	    parseCourseSlotsMethod.setAccessible(true);
	    Field sc = p.getClass().getDeclaredField("sc");
	    sc.setAccessible(true);
	    sc.set(p, simulatedInput);

	    assertEquals(cSlots, parseCourseSlotsMethod.invoke(p));
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }
}
