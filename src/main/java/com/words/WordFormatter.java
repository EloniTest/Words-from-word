package com.words;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WordFormatter {
// Верхний регистр и замена Ё -> Е
    static String normalize (String word) {
        return word.toUpperCase(Locale.ROOT).replace('Ё', 'Е').replace('ё', 'Е');
    }

    //Подсчет букв в слове
    static Map<Character, Integer> countLetters (String word) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : word.toCharArray()) {
            freq.put( c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }


    //Можно ли собрать слово word из букв слова main

    static boolean canForm(String main, String word) {
        Map<Character, Integer> mainCnt = countLetters(main);
        Map<Character, Integer> wordCnt = countLetters(word);

        for(var entry : wordCnt.entrySet()) {
            int available = mainCnt.getOrDefault(entry.getKey(), 0);
            if (available < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    //Определение сложности уровня
    static String calcDifficulty(String mainWord, int subCount, DifficultyThresholds th) {
        int len = mainWord.length();

        if (len <= th.easyMaxMainWordLength && subCount <= th.easyMaxSubWords) {
            return "EASY";
        }
        if (len <= th.mediumMaxMainWordLength && subCount <= th.easyMaxSubWords) {
            return "MEDIUM";
        }
        return "HARD";
    }
