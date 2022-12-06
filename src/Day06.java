import utils.Solvable;

import java.util.*;

public class Day06 implements Solvable<Integer> {
    public Integer part1(List<String> input) {
        int markerLength = 4;
        char[] mem = new char[markerLength];
        char[] stream = input.get(0).toCharArray();
        for (int i = 0; i < markerLength; i++) {
            mem[i] = stream[i];
        }
        for (int i = markerLength; i < stream.length; i++) {
            Set<Character> s = new HashSet<Character>();
            for (char c : mem) s.add(c);
            if (s.size() == markerLength) return i;
            mem[i%markerLength] =stream[i];
        }
        return -1;
    }

    public Integer part2(List<String> input) {
        int markerLength = 14;
        char[] mem = new char[markerLength];
        char[] stream = input.get(0).toCharArray();
        for (int i = 0; i < markerLength; i++) {
            mem[i] = stream[i];
        }
        for (int i = markerLength; i < stream.length; i++) {
            Set<Character> s = new HashSet<Character>();
            for (char c : mem) s.add(c);
            if (s.size() == markerLength) return i;
            mem[i%markerLength] =stream[i];
        }
        return -1;
    }
}
