import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

/**
 * Testing for ConstructAlphabeticOrder.constructAlphabet
 */
public class TestConstructAlphabet {
    public static void main(String args[]) {
        runTest(constructValidTestCases(), constructInvalidTestCases());
    }

    /**
     * run each test case and print test result
     * @param validTestCases
     * @param invalidTestCases
     */
    public static void runTest(Map<String[], List<List<Character>>> validTestCases, List<String[]> invalidTestCases) {
        int i = 0;
        for (Map.Entry<String[], List<List<Character>>> entry : validTestCases.entrySet()) {
            List<Character> res = ConstructAlphabeticOrder.constructAlphabet(entry.getKey());
            if (hasEqualList(res, entry.getValue())) {
                System.out.printf("valid test case %s passed!\n", i);
            } else {
                System.out.printf("valid test case %s failed----------------\n", i);
                System.out.printf("test input: %s\n, expected answer/s: \n", Arrays.toString(entry.getKey()));
                for (List<Character> ans: entry.getValue()) {
                    System.out.printf("\t %s\n", ans.toString());
                }
                System.out.printf("returned result: %s\n", res.toString());
                System.out.printf("---------------------------------\n");
            }
            i++;
        }

        i = 0;
        for (String[] invalidInput : constructInvalidTestCases()) {
            try {
                List<Character> res = ConstructAlphabeticOrder.constructAlphabet(invalidInput);
                System.out.printf("invalid test case %s failed, expect exception to be thrown----------------\n", i);
                System.out.printf("test input: %s\n, returned result: %s\n", Arrays.toString(invalidInput), res.toString());
                System.out.printf("---------------------------------\n");
            } catch (IllegalArgumentException expectedException) {
                System.out.printf("invalid test case %s passed!\n", i);
            }
            i++;
        }
    }

    /**
     *
     * @param result: result returned from constructAlphabet
     * @param answers: list of correct answers
     * @return true if there is a match between result and one of the answers, false if no match found
     */
    public static boolean hasEqualList(List<Character> result, List<List<Character>> answers) {
        for (List<Character> ans: answers) {
            if (result.equals(ans))
                return true;
        }
        return false;
    }

    /**
     *
     * @return a map with key - input sorted list of words, value - correct alphabet order/s as List of Character List
     */
    public static Map<String[], List<List<Character>>> constructValidTestCases() {
        Map<String[], List<List<Character>>> validTestCases = new HashMap<>();

        // case 0: normal test input
        String[] case0_key = new String[]{"bca", "aaa", "acb"};
        List<List<Character>> case0_val = new ArrayList<>();
        case0_val.add(new ArrayList<>(Arrays.asList('b', 'a', 'c')));

        // case 1: normal test input
        String[] case1_key = new String[]{"bca", "aaa", "acb", "dc", "dd"};
        List<List<Character>> case1_val = new ArrayList<>();
        case1_val.add(new ArrayList<>(Arrays.asList('b', 'a', 'c', 'd')));

        // case 2: empty list
        String[] case2_key = new String[] {};
        List<List<Character>> case2_val = new ArrayList<>();
        case2_val.add(new ArrayList<>());

        // case 3: list of empty strings
        String[] case3_key = new String[] {"", "", ""};
        List<List<Character>> case3_val = new ArrayList<>();
        case3_val.add(new ArrayList<>());

        // case 4: one empty list
        String[] case4_key = new String[] {""};
        List<List<Character>> case4_val = new ArrayList<>();
        case4_val.add(new ArrayList<>());

        // case 5: words list of size 1
        String[] case5_key = new String[] {"abbc"};
        List<List<Character>> case5_val = new ArrayList<>();
        case5_val.add(new ArrayList<>(Arrays.asList('a','b','c')));
        case5_val.add(new ArrayList<>(Arrays.asList('a','c','b')));
        case5_val.add(new ArrayList<>(Arrays.asList('b','a','c')));
        case5_val.add(new ArrayList<>(Arrays.asList('b','c','a')));
        case5_val.add(new ArrayList<>(Arrays.asList('c','a','b')));
        case5_val.add(new ArrayList<>(Arrays.asList('c','b','a')));

        // case 6: multiple possible output
        String[] case6_key = new String[] {"efg", "eg"};
        List<List<Character>> case6_val = new ArrayList<>();
        case6_val.add(new ArrayList<>(Arrays.asList('e','f','g')));
        case6_val.add(new ArrayList<>(Arrays.asList('f','e','g')));
        case6_val.add(new ArrayList<>(Arrays.asList('f','g','e')));

        // case 7: input in uppercase
        String[] case7_key = new String[] {"efG", "Eg", "F"};
        List<List<Character>> case7_val = new ArrayList<>();
        case7_val.add(new ArrayList<>(Arrays.asList('e','f','g')));

        validTestCases.put(case0_key, case0_val);
        validTestCases.put(case1_key, case1_val);
        validTestCases.put(case2_key, case2_val);
        validTestCases.put(case3_key, case3_val);
        validTestCases.put(case4_key, case4_val);
        validTestCases.put(case5_key, case5_val);
        validTestCases.put(case6_key, case6_val);
        validTestCases.put(case7_key, case7_val);

        return validTestCases;
    }

    public static List<String[]> constructInvalidTestCases() {
        List<String[]> invalidCases = new ArrayList<>();
        invalidCases.add(new String[]{"bcaaa", "bca"});     // case0: first word longer than second
        invalidCases.add(new String[]{"ba", "bb", "ac"});   // case1: impossible input, a cannot be smaller and greater than b


        return invalidCases;
    }

}