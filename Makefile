all: base test
debug: base
test:
	javac -cp .:/usr/share/java/hamcrest/core.jar:/usr/share/java/junit.jar ParserTest.java
base:
	javac Assignable.java
	javac Slot.java
	javac Course.java
	javac Lab.java
	javac Lab2.java
	javac CourseSlot.java
	javac LabSlot.java
	javac Preference.java
	javac Parser.java
clean:
	rm -rf *.class
	rm -rf *~
.PHONY: all clean
