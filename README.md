# Words from word — level generator

A module that builds game levels in JSON format from a list of Russian words (dictionary) and a list of main words. Each level has a main word, a set of sub-words, a difficulty level, and the minimum number of sub-words needed to pass.

# Requirements
* JDK 17+
* Maven 3.9.x+
# How to build and run
## Build
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
## Run

To run the project in the terminal, you need to write and execute a command ```mvn exec::java```

# How this algorithm working?
The algorithm reads several JSON files, calls a method of the Service class, and saves the result. Here is the description in the same order as your notes.

1) **File Reading**
* reads `config.json`
* reads `main_words.json`
* reads `dict.json`

2) **Method Call**

* calls the `generateLevels` method of the `Service` class

3) **Saving the Result** 

* saves the result to `output.json`

4) **Processing `dict.json` (Stream API)**

* all words are converted to a single case (`Ё → Е`)
* words whose length is `>= minSubWordLength` are kept
* duplicates are removed 

5) **Processing `main_words.json`**

* words are converted to a single case
* words from `dict.json` are searched according to the rules in `config.json`
* if the number of found sub-words is `< minWordsToPass`, the level is not created

6) Calculating Difficulty
* Two parameters are used: the length of the main word and the number of found sub-words
* Plus the thresholds from `config.json`:
* "easyMaxMainWordLength" : 7,
* "mediumMaxMainWordLength" : 10,
*  "easyMaxSubWords" : 15,
* "mediumMaxSubWords" : 30 

   **Rules:**

   `main` word length `<= easyMaxMainWordLength` -> EASY
   
   `number` of sub-words `<= easyMaxSubWords` -> EASY
   
   `main_word` length `<= mediumMaxMainWordLength` -> MEDIUM
   
   `number` of sub-words `<= mediumMaxSubWords` -> MEDIUM

   if none of the conditions are met -> automatically HARD

7) **Building the Level**

an instance of the level class is created

**fields:**
- `id`
- `main_word`
- `difficulty`
- `minWordsToPass`
- `sub3Wordlist`
# License

This project have MIT license 

