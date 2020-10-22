import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.IllegalArgumentException;
import java.lang.Exception;


/**
 * This class reads in an file containings a list of words sorted in some certain alphabetic order,
 * with each word on a line, to form a String array. It reads the String array and compute the
 * correct order of the alphabet, provides a Character List output, and prints the order to stdout.
 *
 * If the input is invalid, an illegal argument exception will be thrown;
 * if the input contains an empty string, an empty list will be the alphabet order result;
 * if there is multiple valid ordering, one of them will be returned as order result.
 */
public class ConstructAlphabeticOrder {
    /**
     *
     * @param words: the input list of words
     * @throws IllegalArgumentException if the input string[] is invalid
     * @return a List of characters in the order of the alphabet
     */
    public static List<Character> constructAlphabet(String[] words) throws IllegalArgumentException{
        // edge cases: if word list is empty, or has size one
        List<Character> res = new ArrayList<>();
        if (words.length == 0)
            return res;
        if (words.length == 1) {
            Set<Character> uniqueSet = new HashSet<>();
            for (char c : words[0].toCharArray()) {
                uniqueSet.add(Character.toLowerCase(c));
            }
            res.addAll(uniqueSet);
            return res;
        }

        Map<Character, Set<Character>> greaterChars = new HashMap<>();
        int[] numSmallerChars = new int[26];
        
        // construct the "graph" from input word list
        constructGraph(words, greaterChars, numSmallerChars);

        // construct alphabetical order by removing letter with no smaller chars and corresponding edges, and then repeat
        List<Character> order = constructOrderAsList(greaterChars, numSmallerChars);
        return order;
    }

    /**
     *
     * @param greaterChars: a map with key-value pair as letter l - set of letters k greater than l
     * @param numSmallerChars: array holding # of letters smaller than letter corresponding to each index
     * @throws IllegalArgumentException if the input string[] is invalid
     * @return StringBuilder holding
     */
    private static List<Character> constructOrderAsList(Map<Character, Set<Character>> greaterChars, int[] numSmallerChars)
        throws IllegalArgumentException {
        List<Character> order = new ArrayList<>();

        Queue<Character> minLetters = new LinkedList<>();
        for (char c : greaterChars.keySet()) {
            if (numSmallerChars[c - 'a'] == 0)
                minLetters.offer(c); 
        }
        
        while (!minLetters.isEmpty()) {
            char minLetter = minLetters.poll();
            order.add(minLetter);

            for (char greaterLetter: greaterChars.get(minLetter)) {
                numSmallerChars[greaterLetter - 'a']--;
                if (numSmallerChars[greaterLetter - 'a'] == 0)
                    minLetters.offer(greaterLetter);
            }
        }
        
        // if the list size is diff from map length, we have a cycle,
        // since at one point all letters has a smaller letter, which is impossible
        if (order.size() != greaterChars.size())
            throw new IllegalArgumentException("invalid input");

        return order;
    }

    /**
     *
     * @param words: list of words given
     * @param greaterChars: a map with key-value pair as letter l - set of letters k greater than l
     * @param numSmallerChars: array holding # of letters smaller than letter corresponding to each index
     * @throws IllegalArgumentException if the input string[] is invalid
     * @return false if the input is already found invalid, true otherwise
     */
    private static void constructGraph(String[] words, Map<Character, Set<Character>> greaterChars, int[] numSmallerChars)
        throws IllegalArgumentException {
        // for each two words, compare them digit to digit, create an edge if there is a difference
        for (int i = 0; i < words.length - 1; i++) {
            String prev = words[i];
            String next = words[i + 1];
            int lengthToCompare = Math.min(prev.length(), next.length());
            if (prev.length() > next.length() && prev.startsWith(next))
                throw new IllegalArgumentException("invalid input");

            int digit = 0;
            while (digit < lengthToCompare) {
                char prevChar = Character.toLowerCase(prev.charAt(digit));
                char nextChar = Character.toLowerCase(next.charAt(digit));
                digit++;
                addToMapIfNotExist(prevChar, greaterChars);
                addToMapIfNotExist(nextChar, greaterChars);

                // char at prev is smaller than that at next
                if (prevChar != nextChar) {
                    // if this "pointer" has not already existed
                    if (!greaterChars.get(prevChar).contains(nextChar)) {
                        greaterChars.get(prevChar).add(nextChar);
                        numSmallerChars[nextChar - 'a']++;
                    }
                    // no need to compare after two letters are found different
                    break;
                }
            } 

            // add all other characters in two words if not already existed in map
            while (digit < Math.max(prev.length(), next.length())) {
                if (digit < prev.length())
                    addToMapIfNotExist(Character.toLowerCase(prev.charAt(digit)), greaterChars);
                if (digit < next.length())
                    addToMapIfNotExist(Character.toLowerCase(next.charAt(digit)), greaterChars);
                digit++;
            }
        }
    }

    /**
     *
     * @param key: Character key of the entry to be potentially added
     * @param map: the destination map to add to
     */
    private static void addToMapIfNotExist(char key, Map<Character, Set<Character>> map) {
        if (!map.containsKey(key)) {
            Set<Character> set = new HashSet<>();
            map.put(key, set);
        }
    }

    /**
     *
     * Read in a file containing a list of words, and print out the correct alphabetic order
     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(args[0]));
            String[] words = br.lines().toArray(String[]::new);
            List<Character> alphabet = constructAlphabet(words);
            for (char c : alphabet)
                System.out.printf("%c ", c);
            System.out.println();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}