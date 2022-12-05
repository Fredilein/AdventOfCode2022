import utils.Solvable;

import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 implements Solvable<String> {
    public String part1(List<String> input) {
        String pattern = "move ([0-9]+) from ([0-9]+) to ([0-9]+)";
        Pattern r = Pattern.compile(pattern);
        Stack<Character>[] stacks = initStacks();

        for (String line : input) {
            Matcher m = r.matcher(line);
            int num, from, to;
            if (m.find()) {
                num = Integer.parseInt(m.group(1));
                from = Integer.parseInt(m.group(2));
                to = Integer.parseInt(m.group(3));
            } else {
                continue;
            }
            for (int i = 0; i < num; i++) {
                stacks[to-1].push(stacks[from-1].pop());
            }
        }
        char[] sol = new char[9];
        for (int i = 0; i < 9; i++) {
            sol[i] = stacks[i].pop();
        }
        return new String(sol);
    }

    public String part2(List<String> input) {
        String pattern = "move ([0-9]+) from ([0-9]+) to ([0-9]+)";
        Pattern r = Pattern.compile(pattern);
        Stack<Character>[] stacks = initStacks();

        for (String line : input) {
            Matcher m = r.matcher(line);
            int num, from, to;
            if (m.find()) {
                num = Integer.parseInt(m.group(1));
                from = Integer.parseInt(m.group(2));
                to = Integer.parseInt(m.group(3));
            } else {
                continue;
            }
            Stack<Character> crane = new Stack();
            for (int i = 0; i < num; i++) {
                crane.push(stacks[from-1].pop());
            }
            for (int i = 0; i < num; i++) {
                stacks[to-1].push(crane.pop());
            }
        }
        char[] sol = new char[9];
        for (int i = 0; i < 9; i++) {
            sol[i] = stacks[i].pop();
        }
        return new String(sol);
    }

    public Stack<Character>[] initStacks() {
        Stack<Character>[] stacks = new Stack[9];
        for (int s = 0; s < 9; s++) stacks[s] = new Stack();
        char[][] stacksArr = new char[][] {
                {'B', 'W', 'N'},
                {'L', 'Z', 'S', 'P', 'T', 'D', 'M', 'B'},
                {'Q', 'H', 'Z', 'W', 'R'},
                {'W', 'D', 'V', 'J', 'Z', 'R'},
                {'S', 'H', 'M', 'B'},
                {'L', 'G', 'N', 'J', 'H', 'V', 'P', 'B'},
                {'J', 'Q', 'Z', 'F', 'H', 'D', 'L', 'S'},
                {'W', 'S', 'F', 'J', 'G', 'Q', 'B'},
                {'Z', 'W', 'M', 'S', 'C', 'D', 'J'}};
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < stacksArr[i].length; j++) {
                stacks[i].push(stacksArr[i][j]);
            }
        }
        return stacks;
    }
}
