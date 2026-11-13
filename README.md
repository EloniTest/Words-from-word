# Words from word — level generator

A module that builds game levels in JSON format from a list of Russian words (dictionary) and a list of main words. Each level has a main word, a set of sub-words, a difficulty level, and the minimum number of sub-words needed to pass.

## Requirements
* JDK 17+
* Maven 3.9.x+
## How to build and run
### Build
1) check your java:```java --version```
2) download Maven and unpack it (https://maven.MMVapache.org/download.cgi)
3) add Maven to PATH:
    * search "Modifying System Environment Variables"
    * click environment variables
    * search for PATH and click edit->create
    * paste path like ```C:\Program Files\Apache\maven\bin```
    * save and close
4) check it's version in powershell: ```mvn -v```(should version of Maven appear)
5) go to the cloned repository to build the project; to do this, run the command ```mvn clean compile```
### Run

To run the project in the terminal, you need to write and execute a command ```mvn exec::java```

## How this algorithm working?

~desc

## License

~desc

