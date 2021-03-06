package struktury;
import java.util.Arrays;

public class dioda {
    protected int startX;
    protected int startY;
    protected String whichWay;
    public int[][] board;

    public dioda(int x, int y, String w, int[][] b){
        startX = x;
        startY = y;
        board = new int[b.length][];
        for(int i = 0; i < b.length; i++){
            board[i] = new int[b[i].length];
            System.arraycopy(b[i], 0, board[i], 0, board[i].length);
        }
        whichWay = w;

    }

    public int[][] diodeGeneration(){
        if(whichWay.equals("leftToRight")){
            for(int i = 0; i < 2; i++){
                board[startX][startY+i] = 3;
            }
            board[startX][startY+2] = 3;
            for(int i = 0; i < 2; i++){
                board[startX-1][startY+i+2] = 3;
                board[startX+1][startY+i+2] = 3;
            }
            board[startX][startY+4] = 3;
            board[startX][startY+5] = 3;
        }
        else{
            for(int i = 0; i < 2; i++){
                board[startX][startY-i] = 3;
            }
            board[startX][startY-2] = 3;
            for(int i = 0; i < 2; i++){
                board[startX-1][startY-i-2] = 3;
                board[startX+1][startY-i-2] = 3;
            }
            board[startX][startY-4] = 3;
            board[startX][startY-5] = 3;
        }
        for(int i = 0; i < 10; i++){
            System.out.println(Arrays.toString(board[i]));
        }
        return board;
    }
}