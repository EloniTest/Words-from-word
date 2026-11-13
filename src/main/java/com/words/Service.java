package com.words;
import java.util.stream.Collectors;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
//Основная логика

// structure fix
public class Service {
    // генерация Levels для json
    public static Config.Result generateLevels (List<String> dictionary, List<String> mainWords, Config config) {
        List<Config.level> levels = new ArrayList<>();
        int nextId = 1;

        int minLen = config.minSubWordLength;
        int minPass = config.minWordsToPass;

        //Нормализация словаря через StreamAPI
        List<String> dict = dictionary.stream() //Превращает List в поток (Stream<String>) для цепочки операций.
                .map(WordFormatter::normalize) //Нормализует
                .filter(w -> w.length() >= minLen) // лямбда, фильтрует слова меньше minLen
                .distinct() // убирает дубликаты через .equals
                .collect(Collectors.toList()); // собирает данные в список //заменить на .toList() если на ноуте Java 16+

        for (String rawMain : mainWords){
            String main = WordFormatter.normalize(rawMain);
            List<String> found = dict.stream()
                    .filter(w -> !w.equals(main)) // не само главное слово
                    .filter(w -> WordFormatter.canForm(main, w)) // можно составить из букв
                    .sorted()
                    .collect(Collectors.toList());

            //проверка на количество подслов, если меньше порога minPass пропускаем уровень.
            if (found.size() < minPass) {
                System.out.println(" [skip] " + main + " - только " + found.size() + " подслов");
                continue;
            }
            String difficulty = WordFormatter.calcDifficulty(main, found.size(), config.difficultyThresholds);

            List<Config.sub3_word> subWords = found.stream()
                    .map(Config.sub3_word::new)
                    .collect(Collectors.toList());

            levels.add(new Config.level(nextId, main, difficulty, minPass, subWords));

            System.out.println("  [ok]   Level " + nextId + ": " + main
                    + " → " + difficulty + " (" + found.size() + " подслов)");
            nextId++;
        }

        return new Config.Result(levels);
    }

}

