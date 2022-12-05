import utils.Solvable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 implements Solvable<Integer> {
    @Override
    public Integer part1(List<String> input) {
        int max = 0;
        int curr = 0;
        for (String line : input) {
            if (line.isEmpty()) {
                if (curr > max) max = curr;
                curr = 0;
            } else {
                curr += Integer.parseInt(line);
            }
        }
        return max;
    }

    @Override
    public Integer part2(List<String> input) {
        int curr = 0;
        List<Integer> sums = new ArrayList<Integer>();
        for (String line : input) {
            if (line.isEmpty()) {
                sums.add(curr);
                curr = 0;
            } else {
                curr += Integer.parseInt(line);
            }
        }
        Collections.sort(sums);
        Collections.reverse(sums);
        return sums.get(0) + sums.get(1) + sums.get(2);
    }
}
