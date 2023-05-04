package com.testing.laba1;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

class SysProg1TestNG {
    private List<String> inputLines;
    private List<String> expectedOutput;

// містили setup методи з анотаціями @Before @BeforeClass;
    @BeforeClass
    public static void setUpBeforeClass() {
        // Виконується один раз перед усіма тестами
        System.out.println("перед усіма тестами");
    }

    @BeforeMethod
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

    @Test(groups = "testData")
    public void testWithSetupInputDataAndModifiedInput() {
        System.out.println("Test started in thread: " + Thread.currentThread().getId());
        inputLines = Arrays.asList(
                "Слово", "дерево", "трава", "гора", "дім"
        );
        expectedOutput = Arrays.asList(
                "гора", "дім"
        );
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);
        assertEquals(expectedOutput, actualOutput, "The method should return the correct list of unique words using modified setup data.");
    }

    @Test(groups = "testData")
    public void testEmptyInput() {
        System.out.println("Test started in thread: " + Thread.currentThread().getId());
        List<String> inputLines = Collections.emptyList();
        List<String> expectedOutput = Collections.emptyList();
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "The method should return an empty list for empty input.");
    }

    @Test(groups = "testData")
    public void testAllWordsWithRepeatingCharacters() {
        System.out.println("Test started in thread: " + Thread.currentThread().getId());
        List<String> inputLines = Arrays.asList(
                "дерево", "програма", "клавіатура"
        );

        List<String> expectedOutput = Collections.emptyList();
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "The method should return an empty list if all words have repeating characters.");
    }

    @Test(groups = "testData")
    public void testAllWordsAreUnique() {
        System.out.println("Test started in thread: " + Thread.currentThread().getId());
        List<String> inputLines = Arrays.asList(
                "йцукенгшщзхїфівапролджєячсмитьбю"
        );

        List<String> expectedOutput = Arrays.asList(
                "йцукенгшщзхїфівапролджєячсмить"
        );

        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "The method should return the same list if all words are unique.");
    }

    @Test(groups = "testData")
    public void testSomeWordsAreUnique() {
        System.out.println("Test started in thread: " + Thread.currentThread().getId());
        List<String> inputLines = Arrays.asList(
                "Озеро", "річка", "море", "водоспад"
        );

        List<String> expectedOutput = Arrays.asList(
                "річка", "море"
        );
        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedOutput, actualOutput, "The method should return the list of unique words.");
    }

//  містили принаймні 4 різних Assert вирази;
    @Test(groups = "testDataAsserts")
    public void testVariousAssert() {
        List<String> inputLines = Arrays.asList(
                "Дерево", "море", "озеро", "пляж", "парк"
        );

        List<String> actualOutput = SysProg1.findUniqueWords(inputLines);

        // Перевірка на непустоту списку унікальних слів
        assertFalse(actualOutput.isEmpty(), "The list of unique words should not be empty.");

        // Перевірка на наявність певного слова у списку унікальних слів
        assertTrue(actualOutput.contains("море"), "The list of unique words should contain 'дерево'.");

        // Перевірка на кількість слів у списку унікальних слів
        assertEquals(actualOutput.size(), 3, "The list of unique words should have 2 elements.");

        // Перевірка на відсутність певного слова у списку унікальних слів
        assertNotEquals(actualOutput.get(1), "дерево", "The second element of the list of unique words should not be 'парк'.");
    }

//•  принаймні один тестовий метод, що тестує виключення.
    @Test(groups = "testExceptions", expectedExceptions = NullPointerException.class)
    public void testExceptionHandling() {
        SysProg1.findUniqueWords(null);
    }

// містили принаймні один параметризований тестовий метод(Data Provider).
    @DataProvider(name = "uniqueWordsCountData")
    public Object[][] uniqueWordsCountData() {
        return new Object[][]{
                {"Привіт світ", 2},
                {"Сьогодні чудовий день", 2},
                {"Завтра нарешті вихідний", 1}
        };
    }

    @Test(groups = "DataProviderTest", dataProvider = "uniqueWordsCountData")
    public void testUniqueWordsCount(String inputLine, int expectedCount) {
        List<String> inputLines = Arrays.asList(inputLine.split(" "));
        List<String> uniqueWords = SysProg1.findUniqueWords(inputLines);

        assertEquals(expectedCount, uniqueWords.size());
    }

    @DataProvider(name = "wordHasUniqueCharactersData")
    public Object[][] wordHasUniqueCharactersData() {
        return new Object[][]{
                {"слово", false},
                {"кіт", true},
                {"програма", false}
        };
    }

    @Test(groups = "DataProviderTest", dataProvider = "wordHasUniqueCharactersData")
    public void testWordHasUniqueCharacters(String word, boolean expected) {
        boolean hasUniqueChars = SysProg1.wordHasUniqueCharacters(word);

        assertEquals(expected, hasUniqueChars);
    }

}