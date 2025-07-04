package org.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    void testBasicCases() {
        assertAll("Basic cases",
                () -> assertEquals(0, StringCalculator.add("")),
                () -> assertEquals(1, StringCalculator.add("1")),
                () -> assertEquals(3, StringCalculator.add("1,2")),
                () -> assertEquals(15, StringCalculator.add("1,2,3,4,2,1,2"))
        );
    }

    @Test
    void testNewlineDelimiter() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    void testCustomDelimiter() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
        assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
        assertEquals(6, StringCalculator.add("//[**][%%]\n1**2%%3"));
    }

    @Test
    void testNegativeNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringCalculator.add("1,-2,-3");
        });
        assertEquals("Negatives not allowed: -2, -3", exception.getMessage());
    }

    @Test
    void testIgnoreLargeNumbers() {
        assertEquals(2, StringCalculator.add("2,1001"));
        assertEquals(1002, StringCalculator.add("2,1000"));
    }

    @Test
    void testMalformedInput() {
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("1,\n2"));
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("1,,2"));
    }

    @Test
    void testInputWithWhitespaces() {
        assertEquals(6, StringCalculator.add(" 1 , 2 , 3 "));
    }

    @Test
    void testCustomDelimiterWithRegexChars() {
        assertEquals(6, StringCalculator.add("//[.*]\n1.*2.*3"));
    }

    @Test
    void testInvalidNumberFormat() {
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("1,a,3"));
    }

    @Test
    void testOnlyDelimiters() {
        assertEquals(0, StringCalculator.add(",\n,,\n"));
    }

    @Test
    void testSingleNumberWithDelimiters() {
        assertEquals(1, StringCalculator.add(",,1,,"));
    }

    @Test
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> StringCalculator.add(null));
    }
}