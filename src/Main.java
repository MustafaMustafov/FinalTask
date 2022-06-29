import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {

    public static double a = 11;
    public static double b = 33;
    public static double c = 50;
    public static double d = 0.4;

    public static int countOfSentences(String text) {
        String[] arrayOfSentences = text.split("[.?!]");
        return arrayOfSentences.length;
    }

    public static String[] splitTextToWordsWithoutPunctuation(String text) {
        String textWithoutPunctuation = text.replaceAll("\\p{Punct}", "");
        return textWithoutPunctuation.split(" ");
    }

    //средния брой символи в дума
    public static double averageLengthOfWords(String[] arrayOfWords) {
        double sumOfSymbols = 0;
        double averageLengthOfWords;
        for (String arrayOfWord : arrayOfWords) {
            sumOfSymbols = (float) sumOfSymbols + arrayOfWord.length();
        }
        averageLengthOfWords = (float) sumOfSymbols / arrayOfWords.length;
        return averageLengthOfWords;
    }

    //броят на различните думи, разделен на броя на всички думи.
    public static double typeTokenRatio(String[] arrayOfWords) {
        double res = 1;
        for (int i = 1; i < arrayOfWords.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arrayOfWords[i].equalsIgnoreCase(arrayOfWords[j])) {
                    continue;
                }
                if (i == j) {
                    res++;
                }
            }
        }
        return res / arrayOfWords.length;
    }

    //броят на думите срещани само по един път в текста, разделен на броя на всички
    public static double hapLegoRatio(String[] arrayOfWords) {
        int count = 1, countDifferent = 0;
        for (int i = 0; i < arrayOfWords.length; i++) {
            for (int j = i + 1; j < arrayOfWords.length; j++) {
                if (arrayOfWords[i].equalsIgnoreCase(arrayOfWords[j])) {
                    count++;
                }
            }
            if (count == 1) {
                countDifferent += 1;
            }
        }
        return (float) countDifferent / arrayOfWords.length;
    }

    //броят на всички думи, разделен на броя изречения
    public static double averageWordInSentence(String[] arrayOfWords, int countOfSentences) {
        double averageCountOfWords;
        averageCountOfWords = (float) arrayOfWords.length / countOfSentences;
        return averageCountOfWords;
    }

    public static void printFeatures (String[] arrayOfWords, int countOfSentences) {
        System.out.println("  1. Avg. word length: " + averageLengthOfWords(arrayOfWords));
        System.out.println("  2. Type-token ratio: " + typeTokenRatio(arrayOfWords));
        System.out.println("  3. Hap-Leg ratio: " + hapLegoRatio(arrayOfWords));
        System.out.println("  4. Average Sentence length: " + averageWordInSentence(arrayOfWords, countOfSentences));
    }

    //abs(F1T1-F1T2) * 11 + abs(F2T1-F2T2) * 33 + abs(F3T1-F3T2) * 50 + abs(F4T1-F4T2) * 0.4;
    public static double printSimilarity (String[] arrayOfWords1, String[] arrayOfWords2, int countOfSentences1, int countOfSentences2) {
        double F1T1 = averageLengthOfWords(arrayOfWords1);
        double F1T2 = averageLengthOfWords(arrayOfWords2);
        double F2T1 = typeTokenRatio(arrayOfWords1);
        double F2T2 = typeTokenRatio(arrayOfWords2);
        double F3T1 = hapLegoRatio(arrayOfWords1);
        double F3T2 = hapLegoRatio(arrayOfWords2);
        double F4T1 = averageWordInSentence(arrayOfWords1, countOfSentences1);
        double F4T2 = averageWordInSentence(arrayOfWords2, countOfSentences2);
        return (abs(F1T1-F1T2) * a) + (abs(F2T1-F2T2) * b) + (abs(F3T1-F3T2) * c) + (abs(F4T1-F4T2) * d);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter first text: ");
        String firstText = scan.nextLine();
        String[] arrayOfWords1 = splitTextToWordsWithoutPunctuation(firstText);
        int countOfSentences1 = countOfSentences(firstText);
        /*System.out.println("  1. Avg. word length: " + averageLengthOfWords(arrayOfWords1));
        System.out.println("  2. Type-token ratio: " + typeTokenRatio(arrayOfWords1));
        System.out.println("  3. Hap-Leg ratio: " + hapLegoRatio(arrayOfWords1));
        System.out.println("  4. Average Sentence length: " + averageWordInSentence(arrayOfWords1, countOfSentences1));*/
        printFeatures(arrayOfWords1,countOfSentences1);
        System.out.println("Enter second text: ");
        String secondText = scan.nextLine();
        String[] arrayOfWords2 = splitTextToWordsWithoutPunctuation(secondText);
        int countOfSentences2 = countOfSentences(secondText);
        printFeatures(arrayOfWords2,countOfSentences2);
        System.out.println("Similarity: " + printSimilarity(arrayOfWords1,arrayOfWords2,countOfSentences1,countOfSentences2));

    }
}