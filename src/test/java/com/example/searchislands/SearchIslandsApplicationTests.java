package com.example.searchislands;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class SearchIslandsApplicationTests {

    @Test
    void shouldReturnOne1() {
        byte[][] M = new byte[][]{{1}};
        assertThat(IslandsSearch.countIsland(M)).isEqualTo(1);
    }

    @Test
    void shouldReturnZero() {
        byte[][] M = new byte[][]{{0}};
        assertThat(IslandsSearch.countIsland(M)).isEqualTo(0);
    }

    @Test
    void shouldReturnOne2() {
        byte[][] M = new byte[][]{
                {1,0,1,0,1,0,1,0,1,0},
                {0,1,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1,0,1,0},
                {0,1,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1,0,1,0},
                {0,1,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1,0,1,0},
                {0,1,0,1,0,1,0,1,0,1}};
        assertThat(IslandsSearch.countIsland(M)).isEqualTo(1);
    }


    @Test
    void shouldReturnThree() {
        byte[][] M = new byte[][]{{1,0,1,0,0,1},
                                  {1,0,1,0,0,0},
                                  {0,1,0,1,0,0},
                                  {0,0,0,0,0,1}};
        assertThat(IslandsSearch.countIsland(M)).isEqualTo(3);
    }

    @Test
    void shouldReturnFive() {
        byte[][] M = new byte[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,1,0,1},
                {0,0,0,0,0,0,1,0,1,0,1},
                {0,0,0,0,0,0,1,0,1,0,1},
                {0,0,0,0,0,0,1,0,1,0,1}};
        assertThat(IslandsSearch.countIsland(M)).isEqualTo(5);
    }

    @Test
    void shouldReturn4() {
        byte[][] M = new byte[][]{
                {0,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0},
                {1,1,1,0,0,0,1,0,0},
                {1,1,0,0,0,1,1,1,0},
                {0,0,0,0,0,1,1,0,0},
                {0,0,1,0,0,0,0,0,0},
                {1,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,1,0,0}};
        assertThat(IslandsSearch.countIsland(M)).isEqualTo(4);
    }

    @Test
    void shouldReturn7() {
        byte[][] M = new byte[][]{{1,1,0,0,0},
                                  {0,1,0,0,1},
                                  {1,0,0,1,1},
                                  {0,0,0,0,0},
                                  {1,0,1,0,1},
                                  {0,0,0,0,0},
                                  {0,0,0,1,0},
                                  {1,0,1,0,1},
                                  {1,0,0,1,0}};
        assertThat(IslandsSearch.countIsland(M)).isEqualTo(7);
    }

    @Test
    void shouldWorkOn10k() {
        byte[][] M = generateMatrix(100,100);
        assertThat(IslandsSearch.countIsland(M)).isGreaterThan(0);
    }

    @Test
    void shouldWorkOn100k() {
        byte[][] M = generateMatrix(1000,100);
        assertThat(IslandsSearch.countIsland(M)).isGreaterThan(0);
    }


    private byte[][] generateMatrix(int rowsSize,int colsSize) {

        byte[][] matrix = new byte[rowsSize][colsSize];
        Random r = new Random();
        for (int i = 0; i < rowsSize; i++) {
            for (int j = 0; j < colsSize; j++) {
                matrix[i][j] = (byte) r.nextInt(2);
            }
        }
        return matrix;
    }


}
