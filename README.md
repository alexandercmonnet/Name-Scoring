# Name-Scoring
This project is a coding challenge. The instructions for the challenge are included in the 'Exercise Instructions' section below.

# Running the Application
## Command Line Arguments

| Name | Flag | Description |
|------|------|-------------|
|Input File | --file | The input file containing the names to score as a comma-delimited list |
|Help | --help | outputs available commands and information about them |

## Running With Gradle
To run the application with gradle, you'll need to have the project on a local system with Java installed and you'll need to enter the following command in the top level directory of this project: ```./gradlew run```

If you want to pass arguments to the CLI application via gradle, you can do so with: ```./gradlew run --args="--help"``` or if you want to pass in the path to the text file: ```./gradlew run --args="--file ./sample-input/names.txt"```

## Running the Application as a JAR File
The application can be run without gradle as a simple jar file, however you must build it using gradle. The gradle command to build the JAR file is: ```./gradlew build``` 

Once the application is built, the JAR file can be located in the build/libs/ directory. You can run the application as a JAR file from the top-level of this project with the following command: ```java -jar build/libs/name-scoring-*.jar --help```

# Exercise Instructions
Create a command line utility that will compute a score for a list of first names.

The list of names will be provided as a text file. The full path to the names file will be specified as a
command line argument. The names file will contain a single line of quoted, comma-separated names. A
small sample of data can be found at the end of this document and a full sample file (names.txt) is
attached.

To score a list of names, you must sort it alphabetically and sum the individual scores for all the names.
To score a name, sum the alphabetical value of each letter (A=1, B=2, C=3, etc...) and multiply the sum
by the name’s position in the list (1-based).

For example, when the sample data below is sorted into alphabetical order, LINDA, which is worth 12 +
9 + 14 + 4 + 1 = 40, is the 4th name in the list. So, LINDA would obtain a score of 40 x 4 = 160. The
correct score for the entire list is 3194. The correct score for the attached names.txt file is 871198282.

Your code should be written as if it were part of a real company codebase. As such, it should be
optimized for readability and maintainability. Also, you are aware of the following up-coming
requirements changes that should factor into your design decisions:

• Another department will want to use this utility as well, but they have a much more complex name scoring algorithm.

• This scoring feature will be added to the company's intranet web-app, allowing employees to upload and score files from their desktop.

• The company will be switching from first names only to both first and last names in the file.

Sample names file data:
"MARY","PATRICIA","LINDA","BARBARA","VINCENZO","SHON","LYNWOOD","JERE","HAI"