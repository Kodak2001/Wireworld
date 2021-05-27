package przebiegGry;

import java.util.Arrays;

public class cykl {
    public int[][] board;
    public cykl(int[][] b){
        board = new int[b.length][];
        for(int i = 0; i < b.length; i++){
            board[i] = new int[b[i].length];
            System.arraycopy(b[i], 0, board[i], 0, board[i].length);
        }
    }

    public int[][] stateChange(int[][] board){
        int[][] neighbours = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                neighbours[i][j] = neighbourCount(i, j, board);

            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 1) {
                    board[i][j] = 2;
                }
                    else if(board[i][j] == 2){
                        board[i][j] = 3;
                    }
                        else if(board[i][j] == 3 && (neighbours[i][j] == 1 || neighbours[i][j] == 2))
                            board[i][j] = 1;
            }
        }
        return board;
    }

    private int neighbourCount(int x, int y, int[][] board){
        int count = 0;
        int xD, xG, yD, yG;
        if(x == 0){
            xD = 0;
        }else
            xD = x-1;
        if(x == board.length - 1){
            xG = x;
        }else
            xG = x + 1;

        if(y == 0){
            yD = 0;
        }else
            yD = y-1;
        if(y == board.length - 1){
            yG = y;
        }else
            yG = y + 1;
        for(int i = xD; i <= xG; i++){
            for(int j = yD; j <= yG; j++){
                if(board[i][j] == 1)count++;
            }
        }
        if(board[x][y] == 1)count--;
        return count;
    }
}
