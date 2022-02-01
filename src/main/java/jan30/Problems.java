package jan30;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Problems {
    public static <E> List<E> filterArray(List<E> input) {
        List<E> filteredList = input
                .stream()
                .filter(Problems::filterInteger)
                .collect(Collectors.toList());
        return filteredList;
    }

    private static <E> boolean filterInteger(E e) {
        if (e.getClass() == Integer.class) {
            return true;
        }
        return false;
    }

    public static String multiplyBy11(String input) {
        // Helpful DS
        Stack<Integer> buildStack = new Stack<>();
        char[] characterizedInput = input.toCharArray();

        // Looping variables
        int startIdx = characterizedInput.length - 1;
        int endIdx = 0;
        int carryOver = 0;
        int n = 0;

        for (int i = startIdx; i > endIdx - 1; i--) {
            char c = characterizedInput[i];
            if (i == startIdx) {
                buildStack.push(Character.getNumericValue(c));
                // peek next
                n = Character.getNumericValue(characterizedInput[i]) + Character.getNumericValue(characterizedInput[i - 1]) + carryOver;
                // calculate carryOver
                carryOver = (n - n % 10) / 10;
                // build Stack
                buildStack.push(n % 10);
            } else if (i == endIdx) {
                // terminating calculation
                buildStack.push(Character.getNumericValue(c) + carryOver);
            } else {
                // Intermediate values will cause carryOver
                // peek next
                n = Character.getNumericValue(characterizedInput[i]) + Character.getNumericValue(characterizedInput[i - 1]) + carryOver;
                // calculate carryOver
                carryOver = (n - n % 10) / 10;
                // build stack
                buildStack.push(n % 10);
            }
        }
        // build String based on stack
        StringBuilder built = new StringBuilder();
        buildStack
                .stream()
                .forEach(integer -> built.append(integer));

        return String.valueOf(built);
    }


    public static <E> void main(String[] args) {
        List<E> testList = new ArrayList<>();
        testList.add((E) ((Integer) 3));
        testList.add((E) "2");
        testList.add((E) "115");
        testList.add((E) ((Integer) 910));
        List<E> filteredList = filterArray(testList);

        System.out.println("Input List: " + testList);
        System.out.println("Filtered List: " + filteredList);

        System.out.println("multiplyBy11() examples");
        System.out.println("11 * 11 =" + multiplyBy11("11"));
        System.out.println("65632  * 11 =" + multiplyBy11("65632 "));
        System.out.println("1752 * 11 =" + multiplyBy11("1752"));

    }
    // 3 LIGHT BULBS
    // Turn on 2 light switches. After some time, the light bulb should be warm, so then turn off one of the two light switches
    // From there, you should be able to figure out which switch belongs to which light bulb
    // because light emitting and electricity produces heat.
    // One light bulb will be on still. One light bulb will be off and warm. And finally, one bulb will be cold.
}
