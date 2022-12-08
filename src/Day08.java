import utils.Solvable;

import java.util.List;

public class Day08 implements Solvable<Integer> {
    @Override
    public Integer part1(List<String> input) {
        int[][] grid = parseInput(input);
        int[][] visible = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int max = -1;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > max) {
                    visible[i][j] = 1;
                    max = grid[i][j];
                }
            }
            max = -1;
            for (int j = grid[i].length - 1; j >= 0; j--) {
                if (grid[i][j] > max) {
                    visible[i][j] = 1;
                    max = grid[i][j];
                }
            }
        }

        for (int j = 0; j < grid[0].length; j++) {
            int max = -1;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] > max) {
                    visible[i][j] = 1;
                    max = grid[i][j];
                }
            }
            max = -1;
            for (int i = grid.length - 1; i >= 0; i--) {
                if (grid[i][j] > max) {
                    visible[i][j] = 1;
                    max = grid[i][j];
                }
            }
        }
        return countVisible(visible);
    }

    @Override
    public Integer part2(List<String> input) {
        int[][] grid = parseInput(input);
        int maxScore = 0;
        int counter = 0;
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[i].length - 1; j++) {
                int tree = grid[i][j];
                int score = 1;
                // right
                counter = 0;
                for (int r = j+1; r < grid[i].length; r++) {
                    counter++;
                    if (grid[i][r] >= tree) break;
                }
                score *= counter;
                // left
                counter = 0;
                for (int r = j-1; r >= 0; r--) {
                    counter++;
                    if (grid[i][r] >= tree) break;
                }
                score *= counter;
                // down
                counter = 0;
                for (int s = i+1; s < grid.length; s++) {
                    counter++;
                    if (grid[s][j] >= tree) break;
                }
                score *= counter;
                // up
                counter = 0;
                for (int s = i-1; s >= 0; s--) {
                    counter++;
                    if (grid[s][j] >= tree) break;
                }
                score *= counter;
                if (score > maxScore) maxScore = score;
            }
        }
        return maxScore;
    }

    public Integer countVisible(int[][] visible) {
        int counter = 0;
        for (int i = 0; i < visible.length; i++) {
            for (int j = 0; j < visible[i].length; j++) {
                if (visible[i][j] == 1) counter++;
//                System.out.print(visible[i][j]);
            }
//            System.out.print("\n");
        }
        return counter;
    }

    public int[][] parseInput(List<String> input) {
        int rows = input.size();
        int cols = input.get(0).length();
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String row = input.get(i);
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Character.getNumericValue(row.charAt(j));
            }
        }
        return grid;
    }
}
