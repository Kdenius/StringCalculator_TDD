package org.project;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static void testInput(String input) {
        try {
            System.out.println("Input: " + (input == null ? "null" : "\"" + input + "\""));
            int result = StringCalculator.Add(input);
            System.out.println("Result: " + result + "\n");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getClass().getSimpleName() + " – " + e.getMessage() + "\n");
        }
    }
    public static void main(String[] args) {
        System.out.println("String Calculator Demo\n");
        testInput("1,2");
        testInput("1\n2,3");
        testInput("//;\n1;2");
        testInput("//[***]\n1***2***3");
        testInput("//[*][%]\n1*2%3");
        testInput("2,1001");
        testInput(",,1,,");
        testInput(" 1 , 2 , 3 ");
        testInput("1,-2,-3");
        testInput("1,\n");
        testInput("1,a,3");
        testInput(null);
        System.out.println("\nTotal times Add() was called: " + StringCalculator.GetCalledCount());
    }

}