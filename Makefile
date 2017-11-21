all: base
debug: base
base:
	javac Assignable.java
	javac Slots.java
	javac Course.java
	javac Lab.java
	javac Lab2.java
	javac Parser.java
clean:
	rm *.class
.PHONY: all clean
