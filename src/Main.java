import utils.Solvable;
import utils.FileUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = FileUtils.readInput("./src/data/day05.txt");
        Solvable solver = new Day05();
        System.out.println(solver.part1(input));
        System.out.println(solver.part2(input));
    }
}