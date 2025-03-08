The compiled output files will be generated in the `bin` folder by default.

## How to Run

## To unzip the folder 
<!-- unzip <Project>.zip -->

## Navigate to the project directory
<!-- cd Problem -->

## check java version (should be 11 or +)
javac --version

<!-- ## bin directory alreday exists, we can skip this step
## if it does not exist, then create it
mkdir -p bin -->

## compile the java files from the src directory to the bin directory
javac -d bin -sourcepath src src/com/problem/App.java
javac -d bin -sourcepath src src/com/problem/entity/*.java
javac -d bin -sourcepath src src/com/problem/repo/*.java
javac -d bin -sourcepath src src/com/problem/service/*.java
javac -d bin -sourcepath src src/com/problem/serviceimpl/*.java
javac -d bin -sourcepath src src/com/problem/utils/*.java

## Run the compiled App.java class from bin
java -cp bin com.problem.App

