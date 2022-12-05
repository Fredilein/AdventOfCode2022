import utils.Solvable;

import java.util.Arrays;
import java.util.List;

public class Day04 implements Solvable<Integer> {
    public Integer part1(List<String> input) {
        int contained = 0;
        for (String line : input) {
            String[] elves = line.split(",");
            int[] elf0 = Arrays.stream(elves[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] elf1 = Arrays.stream(elves[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if ((elf0[0] >= elf1[0] && elf0[1] <= elf1[1]) || (elf0[0] <= elf1[0] && elf0[1] >= elf1[1])) contained++;
        }
        return contained;
    }

    public Integer part2(List<String> input) {
        int overlap = 0;
        for (String line : input) {
            String[] elves = line.split(",");
            int[] elf0 = Arrays.stream(elves[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] elf1 = Arrays.stream(elves[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (!(elf0[1] < elf1[0] || elf0[0] > elf1[1])) overlap++;
        }
        return overlap;
    }
}
