package rmi;

import java.io.*;
import java.util.*;
import java.rmi.*;

public class FileClint {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String fileName = scanner.nextLine();
        scanner.close();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            while (line != null && words.size() < 300) {
                String[] lineWords = line.split("\\s+");
                for (String word : lineWords) {
                    if (word.matches("[a-zA-Z]+")) {
                        words.add(word.toLowerCase());
                        if (words.size() == 300) {
                            break;
                        }
                    }
                }
                line = reader.readLine();
            }
            reader.close();

            String[] wordArray = new String[words.size()];
            words.toArray(wordArray);
            System.out.println(Arrays.toString(wordArray));

            functions stub = (functions) Naming.lookup("//localhost:123/server");

            System.out.println("the length of each word :");
            for (String word : wordArray) {
                int len = stub.count(word);
                System.out.println(word + "-->" + len);
            }

            System.out.println("---------------------------------");

            Set<String> repeatedWords = new HashSet<String>();
            for (Map.Entry<String, Integer> entry : stub.repeatedwords(wordArray).entrySet()) {
                if (entry.getValue() > 1) {
                    repeatedWords.add(entry.getKey());
                }
            }
            System.out.println("Repeated words:");
            for (String word : repeatedWords) {
                System.out.println(word);
            }

            System.out.println("---------------------------------");

            String longestWord = wordArray[0];
            for (String word : words) {
                if (stub.longest(word) > longestWord.length()) {
                    longestWord = word;
                }
            }
            System.out.println("The longest word is: " + longestWord);

            System.out.println("---------------------------------");

            String shortestWord = wordArray[0];
            for (String word : words) {
                if (stub.shortest(word) < shortestWord.length()) {
                    shortestWord = word;
                }
            }
            System.out.println("The shortest word is: " + shortestWord);

            System.out.println("---------------------------------");

            System.out.println("Word frequencies:");
            for (Map.Entry<String, Integer> entry : stub.Repeat(wordArray).entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (Exception ae) {
            System.out.println(ae);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken: " + totalTime + " milliseconds");
    }

}
