package com.company;

import Cell.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za przechowywanie planszy Wireworld
 */
public class CellGrid implements Serializable {

    /**
     * liczba, ktĂłra wskazuje na numer generacji, ktĂłra jest aktualnie
     * wyĹ›wietlana na ekranie.
     */
    public static int count = -1;

    /**
     * Lista zawierajÄ…ca generacje plansz.
     */
    public static ArrayList boards = new ArrayList<CellGrid>();
    /**
     * wysokoĹ›Ä‡ planszy (w komĂłrkach)
     */
    public int height;
    /**
     * szerokoĹ›Ä‡ planszy (w komĂłrkach)
     */
    public int width;
    /**
     * Dwuwymiarowa tablica zawierajÄ…ca wszystkie komĂłrki z danej generacji.
     */
    public final Cell grid[][];

    /**
     *
     * @param height wysokoĹ›Ä‡ planszy (w komĂłrkach)
     * @param width szerokoĹ›Ä‡ planszy (w komĂłrkach)
     */
    public CellGrid(int height, int width) {
        this.width = width;
        this.height = height;
        CellGrid.count++;
        boards.add(this);
        grid = new Cell[height + 2][width + 2];
        for (int i = 0; i < height + 2; i++) {
            for (int j = 0; j < width + 2; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }


    /**
     * Zwraca komĂłrkÄ™ o zadanych wspĂłĹ‚rzÄ™dnych
     * @param y wspĂłĹ‚rzÄ™dna Y
     * @param x wspĂłĹ‚rzÄ™dna X
     * @return KomĂłrka o podanych wspĂłĹ‚rzÄ™dnych
     */
    public Cell getCell(int y, int x) {
        return grid[y][x];
    }

    /**
     * Zeruje listÄ™ generacji do zera i ustawia stan wszystkich komĂłrek na 0 - puste.
     */
    public void clear() {

        CellGrid.boards.subList(1, CellGrid.boards.size()).clear();
        CellGrid cellgrid = (CellGrid) CellGrid.boards.get(0);
        for (int i = 0; i < height + 2; i++) {
            for (int j = 0; j < width + 2; j++) {
                cellgrid.grid[i][j].setValue(0);
            }
        }

    }

    /**
     * Ustawia stan komĂłrki dla danych wspĂłĹ‚rzÄ™dnych.
     * @param y wspĂłĹ‚rzÄ™dna Y
     * @param x wspĂłĹ‚rzÄ™dna X
     * @param v Stan komĂłrki. 0 - pusta, 1 - przewodnik, 2 - ogon elektronu, 3- gĹ‚owa elektronu.
     */
    public void setCell(int y, int x, int v) {
        Cell c;
        switch (v) {
            case 0: {
                c = new Empty(y, x);
                break;
            }
            case 1: {
                c = new Conductor(y, x);
                break;
            }
            case 2: {
                c = new Tail(y, x);
                break;
            }
            case 3: {
                c = new Head(y, x);
                break;
            }
            default: {
                c = new Cell(y, x);
                break;
            }
        }
        if (x > width) {

            throw new ArrayIndexOutOfBoundsException("x > width");
        }
        if (y > height) {
            throw new ArrayIndexOutOfBoundsException("y > height");
        }
        if (x < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (y < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        grid[y][x] = c;
    }

    /**
     * Zwraca wysokoĹ›Ä‡ planszy (w komĂłrkach).
     * @return wysokoĹ›Ä‡ planszy (w komĂłrkach).
     */
    public int getHeights() {
        return height;
    }
    /**
     * Zwraca szerokoĹ›Ä‡ planszy (w komĂłrkach)
     * @return szerokoĹ›Ä‡ planszy (w komĂłrkach)
     */
    public int getWidths() {
        return width;
    }



    /**
     * Uaktualnia planszÄ™
     * @param basicgrid plansza, na podstawie ktĂłrej bÄ™dzie tworzona nowa.
     */
    public void Update(CellGrid basicgrid) {
        for (int i = 1; i < height + 1; i++) {
            for (int j = 1; j < width + 1; j++) {
                grid[i][j] = basicgrid.getCell(i, j);
            }
        }
    }

    /**
     * Liczy sÄ…siadĂłw komĂłrki "GĹ‚owa elektronu"
     * @param y wspĂłĹ‚rzÄ™dna Y
     * @param x wspĂłĹ‚rzÄ™dna X
     * @return liczba sÄ…siadĂłw gĹ‚owy elektronu
     */
    public int countHeadNeighbours(int y, int x) {
        int counter = 0;
        if (getCell(y, x).getValue() == 3) {
            counter--;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getCell(y - 1 + i, x - 1 + j).getValue() == 3) {
                    counter++;
                }
            }
        }
        return counter;

    }

}
