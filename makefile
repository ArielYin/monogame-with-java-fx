JAVA = ./jdk1.8.0_162/bin/java
JAVAC = ./jdk1.8.0_162/bin/javac

all:compile run

compile:
	$(JAVAC) ./application/*.java ./card/*.java \
	./location/*.java

run:
	$(JAVA) application.Monopoly

clean:
	rm -rf ./application/*.class ./card/*.class \
	./location/*.class