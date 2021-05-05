package com.company;

import java.util.Arrays;
import struktury.*;

public class Main {

    public static void main(String[] args) {

        int[][] tab = new int [20][20];
        AND bramkaor = new AND(3, 17,"elo", tab);
        tab = bramkaor.andGeneration();
        System.out.println(" ");

        for(int i = 0; i < 20; i++){
                System.out.println(Arrays.toString(tab[i]));
            }
        }
    }
