package software.davidson.ian.graph.safestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SafestPathInGrid {

    public static void main(String [] args){
        SafestPathInGrid safestPathInGrid = new SafestPathInGrid();
        List<List<Integer>> grid = new ArrayList<>();
//        grid.add(List.of(1,0,0));
//        grid.add(List.of(0,0,0));
//        grid.add(List.of(0,0,1));
//        System.out.println(safestPathInGrid.maximumSafenessFactor(grid));

        grid = new ArrayList<>();
        grid.add(List.of(0,0,1));
        grid.add(List.of(0,0,0));
        grid.add(List.of(0,0,0));
        System.out.println(safestPathInGrid.maximumSafenessFactor(grid));
    }


    public int maximumSafenessFactor(List<List<Integer>> grid) {
        // first bfs from each thief to create a grid of min safety score
        Queue<int[]> queue = new LinkedList<>();
        int n = grid.size();

        int[][] aGrid = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(aGrid[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.add(new int[] { i, j });
                    aGrid[i][j] = 0;
                }
            }
        }


        // queue.addAll(theifCoordinates);
        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] current = queue.remove();
                int currentScore = aGrid[current[0]][current[1]];

                // find valid neighbors
                List<int[]> neighbors = findValidNeighbors(n, current[0], current[1]);

                for (int[] neighbor : neighbors) {
                    int neighborScore = aGrid[neighbor[0]][neighbor[1]];

                    if (neighborScore > 1 + currentScore) {
                        aGrid[neighbor[0]][neighbor[1]] = 1 + currentScore;
                        queue.add(new int[] { neighbor[0], neighbor[1] });
                    }
                }

            }
        }

        // Apply dijkstra's on aGrid to determine best path from start to finish;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        heap.add(new int[] { aGrid[0][0], 0, 0 });

        // element structure looks like: {safetyScore, row, col}

        while (!heap.isEmpty()) {
            int[] current = heap.remove();
            int safetyScore = current[0];
            int row = current[1];
            int col = current[2];

            if (row == n - 1 && col == n - 1) {
                return safetyScore;
            }

            visited[row][col] = true;

            List<int[]> neighbors = findValidNeighbors(n, row, col);
            for (int[] neighbor : neighbors) {
                if (!visited[neighbor[0]][neighbor[1]]) {
                    int newSafetyScore = Math.min(safetyScore, aGrid[neighbor[0]][neighbor[1]]);
                    heap.add(new int[] { newSafetyScore, neighbor[0], neighbor[1] });
                    visited[neighbor[0]][neighbor[1]] = true;
                }
            }
        }

        return -1;

    }

    private List<int[]> findValidNeighbors(int gridSize, int r, int c) {
        List<int[]> neighbors = new ArrayList<>();
        for (int[] shift : new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }) {
            int newR = shift[0] + r;
            int newC = shift[1] + c;

            // check inbounds
            if (newR >= 0 && newR < gridSize && newC >= 0 && newC < gridSize) {
                neighbors.add(new int[] { newR, newC });
            }
        }

        return neighbors;
    }
}
