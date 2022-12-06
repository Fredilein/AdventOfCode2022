import utils.Solvable;

import java.util.*;

public class Day06 implements Solvable<Integer> {
    public Integer part1(List<String> input) {
        char[] stream = input.get(0).toCharArray();
        return getMarker(stream, 4);
    }

    public Integer part2(List<String> input) {
        char[] stream = input.get(0).toCharArray();
        return getMarker(stream, 14);
    }

    private Integer getMarker(char[] stream, int length) {
        char[] mem = new char[length];
        for (int i = 0; i < length; i++) {
            mem[i] = stream[i];
        }
        for (int i = length; i < stream.length; i++) {
            Set<Character> s = new HashSet<>();
            for (char c : mem) s.add(c);
            if (s.size() == length) return i;
            mem[i % length] = stream[i];
        }
        return -1;

    }
}
