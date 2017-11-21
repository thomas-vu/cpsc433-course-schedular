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
}
