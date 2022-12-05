import utils.Solvable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day03 implements Solvable<Integer> {
    public Integer part1(List<String> input) {
        Map<Character, Integer> priorities = new HashMap<>();
        for (char ch='a'; ch <= 'z'; ++ch) priorities.put(ch, (int) ch - 96);
        for (char ch='A'; ch <= 'Z'; ++ch) priorities.put(ch, (int) ch - 38);
        int sum = 0;

        for (String line : input) {
            char[] allItems = line.toCharArray();
            Map<Character, Integer> items = new HashMap<>();
            for (int i = 0; i < allItems.length / 2; i++) items.put(allItems[i], 1);
            for (int j = allItems.length / 2; j < allItems.length; j++) {
                if (items.containsKey(allItems[j])) {
                    sum += priorities.get(allItems[j]);
                    break;
                }
            }
        }
        return sum;
    }

    @Override
    public Integer part2(List<String> input) {
        Map<Character, Integer> priorities = new HashMap<>();
        for (char ch='a'; ch <= 'z'; ++ch) priorities.put(ch, (int) ch - 96);
        for (char ch='A'; ch <= 'Z'; ++ch) priorities.put(ch, (int) ch - 38);
        int sum = 0;

        for (int l = 0; l < input.size(); l+=3) {
            Map<Character, Integer> items = new HashMap<>();
            String line = input.get(l);
            char[] allItems = line.toCharArray();
            for (int i = 0; i < allItems.length; i++) items.put(allItems[i], 1);
            line = input.get(l+1);
            allItems = line.toCharArray();
            for (int j = 0; j < allItems.length; j++) {
                if (items.containsKey(allItems[j])) {
                    items.put(allItems[j], 2);
                }
            }
            line = input.get(l+2);
            allItems = line.toCharArray();
            for (int k = 0; k < allItems.length; k++) {
                if (items.containsKey(allItems[k]) && items.get(allItems[k]) == 2) {
                    sum += priorities.get(allItems[k]);
                    break;
                }
            }
        }
        return sum;
    }
}
