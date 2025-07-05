package org.project;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    static int Add(String str){

        /*
         * ============================================
         * Version 1.0 – Basic Add Support
         * --------------------------------------------
         * - Returns 0 for an empty string
         * - Returns the number itself for a single input
         * - Supports any number of comma-separated integers
         * - Returns the sum of two comma-separated numbers
         * Examples: "" → 0, "1" → 1, "1,2" → 3 "1,2,3,4,5" → 15
         * ============================================
         */

//        if(str.isEmpty())
//            return 0;
//        int ans =0;
//        String[] tokens = str.split(",");
//        for(String token: tokens){
//            if(token.isEmpty())
//                continue;
//            int num = Integer.parseInt(token);
//            ans+=num;
//        }
//        return ans;

        /*
         * ============================================
         * Version 2.0 – Support Newline as Delimiter
         * --------------------------------------------
         * - Supports both commas and newlines as delimiters
         * - Ignores empty tokens
         * - Throws exception for invalid sequences like ",\n" or "\n,"
         * ============================================
         */

//        if(str.isEmpty())
//            return 0;
//        int ans =0;
//        if (str.contains(",\n") || str.contains("\n,")) {
//            throw new IllegalArgumentException("Invalid input: contains misplaced delimiters");
//        }
//        String[] tokens = str.split("[,\n]");
//        for(String token: tokens){
//            if(token.isEmpty())
//                continue;
//            int num = Integer.parseInt(token);
//            ans+=num;
//        }
//        return ans;


        /*
         * ============================================
         * Version 3.0 – Support Custom Delimiters
         * --------------------------------------------
         * - Supports single-character custom delimiters: //;\n1;2
         * - Supports multi-character and multiple delimiters: //[***][%]\n1***2%3
         * - Still supports default delimiters (comma, newline)
         * - Throws exception for invalid delimiter-newline sequences
         * ============================================
         */

//        if (str.isEmpty())
//            return 0;
//
//        int ans = 0;
//        String delimiterPattern = ",|\n"; // Default delimiters
//
//        // Handle custom delimiter declaration
//        if (str.startsWith("//")) {
//            Matcher matcher = Pattern.compile("//(\\[.*?])+\\n").matcher(str);
//            if (matcher.find()) {
//                // Multi-character or multiple delimiters
//                String delimiterSection = matcher.group();
//                List<String> delimiters = new ArrayList<>();
//                Matcher d = Pattern.compile("\\[(.*?)]").matcher(delimiterSection);
//                while (d.find()) {
//                    delimiters.add(Pattern.quote(d.group(1)));
//                }
//                delimiterPattern = String.join("|", delimiters);
//                str = str.substring(matcher.end());
//            } else {
//                // Single-character delimiter
//                delimiterPattern = Pattern.quote(String.valueOf(str.charAt(2)));
//                str = str.substring(4);
//            }
//        }
//        // Validate for invalid sequences like ",\n", "\n,", or customDelimiter + \n or \n + customDelimiter
//        Pattern invalidPattern = Pattern.compile("(" + delimiterPattern + ")\\n|\\n(" + delimiterPattern + ")");
//        Matcher invalidMatcher = invalidPattern.matcher(str);
//        if (invalidMatcher.find()) {
//            throw new IllegalArgumentException("Invalid input: contains misplaced delimiters");
//        }
//
//        // Split using the final delimiter pattern
//        String[] tokens = str.split(delimiterPattern + "|\n");
//
//        for (String token : tokens) {
//            if (token.isEmpty())
//                continue;
//            int num = Integer.parseInt(token);
//            ans += num;
//        }
//
//        return ans;

        /*
         * ============================================
         * Version 4.0 – Negative Number Validation
         * --------------------------------------------
         * - Throws IllegalArgumentException if any negative numbers are passed
         * - Exception message includes: "negatives not allowed: -X"
         * - If multiple negatives are present, all are listed in the message
         *   Example: "negatives not allowed: -1, -4"
         * - All previous features (custom delimiters, newline support, etc.) remain supported
         * ============================================
         */

        if (str.isEmpty())
            return 0;

        int ans = 0;
        String delimiterPattern = ",|\n"; // Default delimiters

        // Handle custom delimiter declaration
        if (str.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(\\[.*?])+\\n").matcher(str);
            if (matcher.find()) {
                // Multi-character or multiple delimiters
                String delimiterSection = matcher.group();
                List<String> delimiters = new ArrayList<>();
                Matcher d = Pattern.compile("\\[(.*?)]").matcher(delimiterSection);
                while (d.find()) {
                    delimiters.add(Pattern.quote(d.group(1)));
                }
                delimiterPattern = String.join("|", delimiters);
                str = str.substring(matcher.end());
            } else {
                // Single-character delimiter
                delimiterPattern = Pattern.quote(String.valueOf(str.charAt(2)));
                str = str.substring(4);
            }
        }
        // Validate for invalid sequences like ",\n", "\n,", or customDelimiter + \n or \n + customDelimiter
        Pattern invalidPattern = Pattern.compile("(" + delimiterPattern + ")\\n|\\n(" + delimiterPattern + ")");
        Matcher invalidMatcher = invalidPattern.matcher(str);
        if (invalidMatcher.find()) {
            throw new IllegalArgumentException("Invalid input: contains misplaced delimiters");
        }

        // Split using the final delimiter pattern
        String[] tokens = str.split(delimiterPattern + "|\n");
        List<Integer> negatives = new ArrayList<>();
        for (String token : tokens) {
            if (token.isEmpty())
                continue;
            int num = Integer.parseInt(token);
            if(num < 0)
                negatives.add(num);
            ans += num;
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives.toString().replaceAll("[\\[\\]]", ""));
        }
        return ans;
    }
}