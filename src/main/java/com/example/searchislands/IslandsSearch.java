package com.example.searchislands;

//ja bym nie dołaczył biblioteki tylko po to by dodać klase Pair, którą można dość prosto napisać samemu
import org.javatuples.Pair;

import java.util.HashSet;
import java.util.Set;

//klasa powinna byc finalna i z ukrytym domyślnym konstruktorem
public class IslandsSearch {

    //nazwa zmiennej nieopisowa. Troche brakuje konswekwencji, przekazywana jest tablica byte a nastepnie operujemy na Integerach
    public static int countIsland(byte[][] M) {
        int rowsSize = M.length;
        if (rowsSize == 0) {
            return 0;
        }

        int colsSize = M[0].length;
        if (colsSize == 0) {
            return 0;
        }

        //struktura seta setów z parami obiektów typu Integer nie będzie zbyt optymalna przy dużych wolumenach
        //sama czytelność tej konstrukcji takze nie jest tak oczywista - lepszymy byłoby utworzenie własnej struktury i podejście do tego domenowo.
        Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();

        for (int i = 0; i < rowsSize; i++) {
            for (int j = 0; j < colsSize; j++) {
                if (M[i][j] == 1) {

                  //brak zachowania SRP - metoda może być śmiało podzielona na 3 osobne:
                  // - sprawdzenie rozmiarów
                  // pętla
                  // ciało pętli

                  //ja tu widzę powtarzalny kod, który można wydzielić
                    Set<Pair<Integer, Integer>> topRight = check(islands, M, j + 1, i - 1, rowsSize, colsSize);
                    Set<Pair<Integer, Integer>> top = check(islands, M, j, i - 1, rowsSize, colsSize);
                    Set<Pair<Integer, Integer>> topLeft = check(islands, M, j - 1, i - 1, rowsSize, colsSize);
                    Set<Pair<Integer, Integer>> left = check(islands, M, j - 1, i, rowsSize, colsSize);

                    Set<Pair<Integer, Integer>> mergeIslands = new HashSet<>();

                    //brak konsekwencji w formatowaniu kodu - poniżej jest if z jedną instrukcą w jednej linie  a powyżej - tam gdzie sprawdzany jest sie to jest w wielu liniach i z klamrami
                    // wydaje mi się także, że wcięcia takze nie są wszędzie takie same
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
              //lepszym byłobyy użycie loggera
                System.out.printf("%,8d elements out of %,8d processed(row %d out of %d).\n", i*colsSize, rowsSize*colsSize,i, rowsSize);
            }
        }
        return islands.size();
    }

    //null-driven-development - lepszy byłby optional
    // nazwa metody mogłaby informować co sprawdza
    // liczba parametrow >3 zaburza zasadny Clean-Code
    private static Set<Pair<Integer, Integer>> check(Set<Set<Pair<Integer, Integer>>> islands, byte[][] M, int x, int y, int rowsSize, int colsSize) {
        //wg mnie lepiej wyciągnąć warunek i go jawnie nazwać np. pierwsza jego część sprawdza czy punkt jest na mapie
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
