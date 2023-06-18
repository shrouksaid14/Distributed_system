package rmi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.*;
import java.util.*;

public class thrFileClint {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name: ");
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
            Thread[] threads = new Thread[5];
            threads[0] = new Thread(() -> {
                synchronized (stub) {
                    for (String word : wordArray) {
                        try {
                            int len = stub.count(word);
                            System.out.println(word + "-->" + len);
                        } catch (RemoteException e) {
                            System.out.println("Error invoking 'count' method: " + e.getMessage());
                        }
                    }
                }
            });

            threads[1] = new Thread(() -> {
                synchronized (stub) {
                    Set<String> repeatedWords = new HashSet<String>();
                    try {
                        for (Map.Entry<String, Integer> entry : stub.repeatedwords(wordArray).entrySet()) {
                            if (entry.getValue() > 1) {
                                repeatedWords.add(entry.getKey());
                            }
                        }
                    } catch (RemoteException e) {
                        System.out.println("Error invoking 'repeatedwords' method: " + e.getMessage());
                    }

                    System.out.println("Repeated words:");
                    for (String word : repeatedWords) {
                        System.out.println(word);
                    }
                }
            });

            threads[2] = new Thread(() -> {
                synchronized (stub) {
                    String longestWord = wordArray[0];
                    try {
                        for (String word : wordArray) {
                            if (stub.longest(word) > longestWord.length()) {
                                longestWord = word;
                            }
                        }
                    } catch (RemoteException e) {
                        System.out.println("Error invoking 'longest' method: " + e.getMessage());
                    }

                    System.out.println("The longest word is: " + longestWord);
                }
            });

            threads[3] = new Thread(() -> {
                synchronized (stub) {
                    String shortestWord = wordArray[0];
                    try {
                        for (String word : wordArray) {
                            if (stub.shortest(word) < shortestWord.length()) {
                                shortestWord = word;
                            }
                        }
                    } catch (RemoteException e) {
                        System.out.println("Error invoking 'shortest' method: " + e.getMessage());
                    }

                    System.out.println("The shortest word is: " + shortestWord);
                }
            });

            threads[4] = new Thread(() -> {
                synchronized (stub) {
                    try {
                        Map<String, Integer> wordFrequencies = stub.Repeat(wordArray);
                        System.out.println("Word frequencies:");
                        for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                    } catch (RemoteException e) {
                        System.out.println("Error invoking 'Repeat' method: " + e.getMessage());
                    }
                }
            });

            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }

        } catch (Exception ae) {
            System.out.println(ae);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Total time taken: " + totalTime + " milliseconds");
    }
}