all: base
debug: base
base:
	javac Assignable.java
	javac Slot.java
	javac Course.java
	javac Lab.java
	javac Lab2.java
	javac CourseSlot.java
	javac LabSlot.java
	javac Parser.java
clean:
	rm *.class
.PHONY: all clean
