import utils.Solvable;

import java.util.*;

public class Day02 implements Solvable<Integer> {
    @Override
    public Integer part1(List<String> input) {
        List<int[]> rounds = parseInput(input);
        int[][] points = new int[][] {
                { 4, 8, 3},
                { 1, 5, 9},
                { 7, 2, 6}};
        return calcScore(points, rounds);
    }

    @Override
    public Integer part2(List<String> input) {
        List<int[]> rounds = parseInput(input);
        int[][] points = new int[][] {
                { 3, 4, 8},
                { 1, 5, 9},
                { 2, 6, 7}};
        return calcScore(points, rounds);
    }

    private Integer calcScore(int[][] points, List<int[]> rounds) {
        int score = 0;
        for (int[] round : rounds) score += points[round[0]][round[1]];
        return score;
    }

    private List<int[]> parseInput(List<String> input) {
        Map<String, Integer> mapA = new HashMap<>();
        Map<String, Integer> mapB = new HashMap<>();
        mapA.put("A", 0);
        mapA.put("B", 1);
        mapA.put("C", 2);
        mapB.put("X", 0);
        mapB.put("Y", 1);
        mapB.put("Z", 2);
        List<int[]> res = new ArrayList<>();
        for (String line : input) {
            String[] hands = line.split(" ");
            res.add(new int[] {mapA.get(hands[0]), mapB.get(hands[1])});
        }
        return res;
    }
}