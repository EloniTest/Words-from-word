package com.words;
import java.util.stream.Collectors;
//Основная логика

// генерация Levels для json
static Result generateLevels (List<String> dictionary, List<String> mainWords, Config config) {
    List<Level> levels = new ArrayList<>();
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
        String main = normalize(rawMain);
        List<String> found = dict.stream()
                .filter(w -> !w.equals(main)) // не само главное слово
                .filter(w -> canForm(main, w)) // можно составить из букв
                .sorted
                .collect(Collectors.toList());

        //проверка на количество подслов, если меньше порога minPass пропускаем уровень.
        if (found.size() < minPass) {
            System.out.println(" [skip] " + main + " - только " + found.size() + " подслов");
            continue;
        }
        String difficilty = calcDifficulty(main, found.size(), config.difficultyThresholds);

        List<SubWord> subWords = found.stream()
                .map(SubWord::new)
                .collect(Collectors.toList());

        levels.add(new Level(nextId, main, difficulty, minPass, subWords));

        System.out.println("  [ok]   Level " + nextId + ": " + main
                + " → " + difficulty + " (" + found.size() + " подслов)");
        nextId++;
    }

    return new Result(levels);
    }
