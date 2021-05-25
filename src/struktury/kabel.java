package struktury;
import java.util.Arrays;

public class kabel {
    protected int startX;
    protected int startY;
    public int[][] board;

    public kabel(int x, int y, int[][] b){
        startX = x;
        startY = y;
        board = new int[b.length][];
        for(int i = 0; i < b.length; i++){
            board[i] = new int[b[i].length];
            System.arraycopy(b[i], 0, board[i], 0, board[i].length);
        }

    }

    public int[][] kabelGeneration(){
        for(int i = 0; i < 4; i++){
            board[startX][startY+i] = 3;
        }
        return board;
    }
}