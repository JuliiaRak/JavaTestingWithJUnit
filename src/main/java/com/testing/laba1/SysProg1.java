package com.testing.laba1;

import java.util.*;

//4. Знайти лише ті слова, кожне з яких складається з літер що не повторюються.

public class SysProg1 {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
            //зчитування
            List<String> inputLines = Arrays.asList(
                    "Привіт, це тестовий текст!",
                    "Тут є кілька унікальних слів, але деякі повторюються.",
                    "Сподіваюсь, тест працює правильно."
            );

            List<String> uniqueWords = findUniqueWords(inputLines);

            //вивід
            for (String word : uniqueWords) {
                System.out.println(word);
            }
    }

    public static List<String> findUniqueWords(List<String> lines) {
        ArrayList<String> words = new ArrayList<>();

        for (String nextLine : lines) {
            List<String> wordsInLine = Arrays.asList(nextLine.split("[\\s(),.!?:;@|-]+"));

            //аналіз слова
            for (String word : wordsInLine) {
                word = word.toLowerCase();
                if (word.length() > 30) {
                    word = word.substring(0, 30);
                }
                boolean unique = true;
                if (words.contains(word) || word.isEmpty()) {
                    unique = false;
                }
                for (int i = 0; i < word.length(); i++) {
                    for (int j = i + 1; j < word.length(); j++) {
                        if (word.charAt(i) == word.charAt(j)) {
                            unique = false;
                            break;
                        }
                    }
                }
                if (unique) {
                    words.add(word);
                }
            }
        }
        return words;
    }

    public static boolean wordHasUniqueCharacters(String word) {
        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j < word.length(); j++) {
                if (word.charAt(i) == word.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}