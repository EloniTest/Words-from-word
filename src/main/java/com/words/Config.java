package com.words;
import java.util.List;
public class Config {
    public int minSubWordLength;
    public int minWordsToPass;
    public difficultyThresholds difficultyThresholds;


    // --- config ---
    static class difficultyThresholds {
        int easyMaxMainWordLength;
        int mediumMaxMainWordLength;
        int easyMaxSubWords;
        int mediumMaxSubWords;
    }
    // --- config ---

    // --- output generation ---
    static class sub3_word {
        String text;
        String hint;
        sub3_word(String text) {
            this.text = text;
            this.hint = "";
        }
    }


    static class level {
        int id;
        String main_word;
        String difficulty;
        int minWordsToPass;
        List<sub3_word> sub3Wordlist;
        level(int id,String main, String difficulty,int min_words,List<sub3_word> sub_words) {
            this.id = id;
            this.main_word = main;
            this.difficulty = difficulty;
            this.minWordsToPass = min_words;
            this.sub3Wordlist = sub_words;
        }
    }


    static class Result {
        List<level> result;
        result(List<level> levels) {
            this.result = levels;
        }
    }
    // --- output generation ---
}