public class config {
    // --- config ---
    static class config {
        int minSubWordLength;
        int minWordsToPass;
        difficultyThresholds difficultyThresholds;
    }


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
        list<sub3_word> sub3Wordlist;
        level(int id,String main, String difficulty,int min_words,list<sub3_word> sub_words) {
            this.id = id;
            this.main_word = main;
            this.difficulty = difficulty;
            this.minWordsToPass = min_words;
            this.sub3Wordlist = sub_words;
        }
    }


    static class result {
        List<level> result;
        result(list<level> levels) {
            this.result = levels;
        }
    }
    // --- output generation ---
}