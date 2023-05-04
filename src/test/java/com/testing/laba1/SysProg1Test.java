package com.testing.laba1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class SysProg1Test {

    private List<String> inputLines;
    private List<String> expectedOutput;

//•  містили setup методи з анотаціями @Before @BeforeClass(@BeforeEach, @BeforeAll);
    @BeforeAll
    public static void setUpBeforeClass() {
        // Виконується один раз перед усіма тестами
        System.out.println("перед усіма тестами");
    }

    @BeforeEach
    public void setUp() {
        // Виконується перед кожним тестом
        System.out.println("перед кожним тестом");
        inputLines = Arrays.asList(
                "Слово", "дерево", "трава", "гора"
        );
        expectedOutput = Arrays.asList(
                "гора"
        );
    }

    @Test
    public void testWithSetupInputData() {
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);
        assertEquals(expectedOutput, actualOutput, "The method should return the correct list of unique words using setup data.");
    }

    @Test
    public void testWithSetupInputDataAndModifiedInput() {
        inputLines = Arrays.asList(
                "Слово", "дерево", "трава", "гора", "дім"
        );
        expectedOutput = Arrays.asList(
                "гора", "дім"
        );
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);
        assertEquals(expectedOutput, actualOutput, "The method should return the correct list of unique words using modified setup data.");
    }

    @Test
    public void testFindUniqueWords() {
        inputLines = Arrays.asList(
                "Привіт, це тестовий текст!",
                "Тут є кілька унікальних слів, але деякі повторюються."
        );

        expectedOutput = Arrays.asList(
                "привіт", "це", "є", "слів", "але", "деякі"
        );

        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "Цей метод повинен повертати слова, що складаються з унікальних букв");
    }

    @Test
    public void testEmptyInput() {
        List<String> inputLines = Collections.emptyList();
        List<String> expectedOutput = Collections.emptyList();
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "The method should return an empty list for empty input.");
    }

    @Test
    public void testAllWordsWithRepeatingCharacters() {
        List<String> inputLines = Arrays.asList(
                "дерево", "програма", "клавіатура"
        );

        List<String> expectedOutput = Collections.emptyList();
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "The method should return an empty list if all words have repeating characters.");
    }

    @Test
    public void testAllWordsAreUnique() {
        List<String> inputLines = Arrays.asList(
                "йцукенгшщзхїфівапролджєячсмитьбю"
        );

        List<String> expectedOutput = Arrays.asList(
                "йцукенгшщзхїфівапролджєячсмить"
        );

        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "The method should return the same list if all words are unique.");
    }

    @Test
    public void testSomeWordsAreUnique() {
        List<String> inputLines = Arrays.asList(
                "Озеро", "річка", "море", "водоспад"
        );

        List<String> expectedOutput = Arrays.asList(
                "річка", "море"
        );
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "The method should return the list of unique words.");
    }

//•  містили принаймні 4 різних Assert вирази;
    @Test
    public void testVariousAssertions() {
        List<String> inputLines = Arrays.asList(
                "Дерево", "море", "озеро", "пляж", "парк"
        );

        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        // Перевірка на непустоту списку унікальних слів
        assertFalse(actualOutput.isEmpty(), "The list of unique words should not be empty.");

        // Перевірка на наявність певного слова у списку унікальних слів
        assertTrue(actualOutput.contains("море"), "The list of unique words should contain 'дерево'.");

        // Перевірка на кількість слів у списку унікальних слів
        assertEquals(3, actualOutput.size(), "The list of unique words should have 2 elements.");

        // Перевірка на відсутність певного слова у списку унікальних слів
        assertNotEquals("дерево", actualOutput.get(1), "The second element of the list of unique words should not be 'парк'.");
    }

//•  принаймні один тестовий метод, що тестує виключення.
    @Test
    public void testExceptionHandling() {
        assertThrows(NullPointerException.class, () -> {
            SysProg1.findUniqueWords(null);
        }, "The method should throw a NullPointerException when the input is null.");
    }


//•  використовували принаймні 2 складних метчери Hamcrest або їх аналоги(прикади: метчери для рядків, колекцій, прості порівняння не враховуються);
    @Test
    public void testWithComplexMatcher1() {
        List<String> inputLines = Arrays.asList(
                "квіти", "сонце", "озеро", "вітер", "сонце"
        );
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);
        assertThat(actualOutput, containsInAnyOrder("квіти", "вітер", "сонце"));
        assertThat(actualOutput, not(hasItem("озеро")));
    }

    @Test
    public void testWithComplexMatcher2() {
        List<String> inputLines = Arrays.asList(
                "Літо", "осінь", "зима", "весна", "літо"
        );
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);
        assertThat(actualOutput, both(hasSize(4)).and(containsInAnyOrder("літо", "осінь", "зима", "весна")));
    }

//•  містили принаймні один параметризований тестовий метод(Parameters для jUnit 4).
    @ParameterizedTest
    @CsvSource({
            "'Привіт світ', 2",
            "'Сьогодні чудовий день', 2",
            "'Завтра нарешті вихідний', 1"
    })
    public void testUniqueWordsCount(String inputLine, int expectedCount) {
        List<String> inputLines = Arrays.asList(inputLine.split(", "));
        List<String> uniqueWords = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedCount, uniqueWords.size());
    }

    @ParameterizedTest
    @CsvSource({
            "слово, false",
            "кіт, true",
            "програма, false"
    })
    public void testWordHasUniqueCharacters(String word, boolean expected) {
        boolean hasUniqueChars = SysProg1.wordHasUniqueCharacters(word);

        assertEquals(expected, hasUniqueChars);
    }

}