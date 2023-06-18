
package rmi;

import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.*;

public class FunImp extends UnicastRemoteObject implements functions {

    public FunImp() throws RemoteException {
        super();
    }

    @Override
    public int count(String words) throws RemoteException {
        return words.length();
    }

    @Override
    public Map<String, Integer> repeatedwords(String[] words) throws RemoteException {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (wordCount.containsKey(word)) {
                int frequency = wordCount.get(word);
                wordCount.put(word, frequency + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        return wordCount;
    }

    @Override
    public int longest(String words) throws RemoteException {
        return words.length();
    }

    @Override
    public int shortest(String words) throws RemoteException {
        return words.length();
    }

    @Override
    public Map<String, Integer> Repeat(String[] words) throws RemoteException {
        Map<String, Integer> wordFrequencies = new HashMap<String, Integer>();
        for (String word : words) {
            if (wordFrequencies.containsKey(word)) {
                int frequency = wordFrequencies.get(word);
                wordFrequencies.put(word, frequency + 1);
            } else {
                wordFrequencies.put(word, 1);
            }
        }
        return wordFrequencies;

    }
}
