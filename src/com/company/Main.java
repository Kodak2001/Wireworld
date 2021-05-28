package com.company;

import java.util.Arrays;

import GUI.WireworldWindow;
import struktury.*;
import przebiegGry.*;
import odczytIZapis.*;

public class Main {

    public static void main(String[] args) {

        int x =20;
        int[][] tab = new int[x][x];
        odczyt od = new odczyt("C:\\Users\\Kuba\\IdeaProjects\\Wireworld\\src\\com\\company\\elo.txt", x, x);
        tab = od.dataFromFile();
        cykl gra = new cykl(tab);
        for (int j = 0; j < 10; j++) {
            System.out.println(" ");
            for (int i = 0; i < x; i++) {
                System.out.println(Arrays.toString(tab[i]));
            }
            tab = gra.stateChange(tab);
        }
        new WireworldWindow("ello", x);
    }
}
