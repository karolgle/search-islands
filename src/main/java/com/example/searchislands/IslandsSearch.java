package com.example.searchislands;

import org.javatuples.Pair;

import java.util.HashSet;
import java.util.Set;

public class IslandsSearch {

    public static int countIsland(byte[][] M) {
        int rowsSize = M.length;
        if (rowsSize == 0) {
            return 0;
        }

        int colsSize = M[0].length;
        if (colsSize == 0) {
            return 0;
        }
        Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();

        for (int i = 0; i < rowsSize; i++) {
            for (int j = 0; j < colsSize; j++) {
                if (M[i][j] == 1) {
                    Set<Pair<Integer, Integer>> topRight = check(islands, M, j + 1, i - 1, rowsSize, colsSize);
                    Set<Pair<Integer, Integer>> top = check(islands, M, j, i - 1, rowsSize, colsSize);
                    Set<Pair<Integer, Integer>> topLeft = check(islands, M, j - 1, i - 1, rowsSize, colsSize);
                    Set<Pair<Integer, Integer>> left = check(islands, M, j - 1, i, rowsSize, colsSize);

                    Set<Pair<Integer, Integer>> mergeIslands = new HashSet<>();
                    if (topRight != null) mergeIslands.addAll(topRight);
                    if (top != null) mergeIslands.addAll(top);
                    if (topLeft != null) mergeIslands.addAll(topLeft);
                    if (left != null) mergeIslands.addAll(left);

                    mergeIslands.add(new Pair<>(i, j));

                    islands.remove(topRight);
                    islands.remove(top);
                    islands.remove(topLeft);
                    islands.remove(left);
                    islands.add(mergeIslands);
                }
            }
            if (i % 10 == 0) {
                System.out.printf("%,8d elements out of %,8d processed(row %d out of %d).\n", i*colsSize, rowsSize*colsSize,i, rowsSize);
            }
        }
        return islands.size();
    }

    private static Set<Pair<Integer, Integer>> check(Set<Set<Pair<Integer, Integer>>> islands, byte[][] M, int x, int y, int rowsSize, int colsSize) {
        if (x >= 0 && y >= 0 && y < rowsSize && x < colsSize && M[y][x] == 1) {
            for (Set<Pair<Integer, Integer>> island : islands) {
                if (island.contains(new Pair<>(y, x))) {
                    return island;
                }
            }
        }
        return null;
    }
}
