package com.company;

import java.util.Arrays;

import GUI.WireworldWindow;
import struktury.*;
import przebiegGry.*;
import odczytIZapis.*;

public class Main {

    public static void main(String[] args) {

        int[][] tab = new int[20][20];
        odczyt od = new odczyt("C:\\Users\\Kuba\\IdeaProjects\\Wireworld\\src\\com\\company\\elo.txt", 20, 20);
        tab = od.dataFromFile();
        cykl gra = new cykl(tab);
        for (int j = 0; j < 10; j++) {
            System.out.println(" ");
            for (int i = 0; i < 20; i++) {
                System.out.println(Arrays.toString(tab[i]));
            }
            tab = gra.stateChange(tab);
        }
        new WireworldWindow("ello");
    }
}
