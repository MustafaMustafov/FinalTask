import java.text.DecimalFormat;
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
                    break;
                } else {
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

    public static void printFeatures(String[] arrayOfWords, int countOfSentences) {
        DecimalFormat df = new DecimalFormat("#.###");
        System.out.println("  1. Avg. word length: " + df.format(averageLengthOfWords(arrayOfWords)));
        System.out.println("  2. Type-token ratio: " + df.format(typeTokenRatio(arrayOfWords)));
        System.out.println("  3. Hap-Leg ratio: " + df.format(hapLegoRatio(arrayOfWords)));
        System.out.println("  4. Average Sentence length: " + df.format(averageWordInSentence(arrayOfWords, countOfSentences)));
    }

    //abs(F1T1-F1T2) * 11 + abs(F2T1-F2T2) * 33 + abs(F3T1-F3T2) * 50 + abs(F4T1-F4T2) * 0.4;
    public static String printSimilarity(String[] arrayOfWords1, String[] arrayOfWords2, int countOfSentences1, int countOfSentences2) {
        DecimalFormat df = new DecimalFormat("#.###");
        double feature1T1 = averageLengthOfWords(arrayOfWords1);
        double feature1T2 = averageLengthOfWords(arrayOfWords2);
        double feature2T1 = typeTokenRatio(arrayOfWords1);
        double feature2T2 = typeTokenRatio(arrayOfWords2);
        double feature3T1 = hapLegoRatio(arrayOfWords1);
        double feature3T2 = hapLegoRatio(arrayOfWords2);
        double feature4T1 = averageWordInSentence(arrayOfWords1, countOfSentences1);
        double feature4T2 = averageWordInSentence(arrayOfWords2, countOfSentences2);
        double firstOperation = abs(feature1T1 - feature1T2) * a;
        double secondOperation = abs(feature2T1 - feature2T2) * b;
        double thirdOperation = abs(feature3T1 - feature3T2) * c;
        double fourthOperation = abs(feature4T1 - feature4T2) * d;
        double similarity = firstOperation + secondOperation + thirdOperation + fourthOperation;
        return "Similarity: " + df.format(firstOperation) + " + " + df.format(secondOperation) + " + " + df.format(thirdOperation) + " + " + df.format(fourthOperation) + " = " + df.format(similarity);
        //System.out.print(
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter first text: ");
        String firstText = scan.nextLine();
        String[] arrayOfWords1 = splitTextToWordsWithoutPunctuation(firstText);
        int countOfSentences1 = countOfSentences(firstText);
        printFeatures(arrayOfWords1, countOfSentences1);
        System.out.println("Enter second text: ");
        String secondText = scan.nextLine();
        String[] arrayOfWords2 = splitTextToWordsWithoutPunctuation(secondText);
        int countOfSentences2 = countOfSentences(secondText);
        printFeatures(arrayOfWords2, countOfSentences2);
        System.out.println(printSimilarity(arrayOfWords1, arrayOfWords2, countOfSentences1, countOfSentences2));

    }
}