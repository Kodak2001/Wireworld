package struktury;
import java.util.Arrays;

public class OR {
    protected int startX;
    protected int startY;
    protected String whichWay;
    public int[][] board;

    public OR(int x, int y, String w, int[][] b){
        startX = x;
        startY = y;
        board = new int[b.length][];
        for(int i = 0; i < b.length; i++){
            board[i] = new int[b[i].length];
            System.arraycopy(b[i], 0, board[i], 0, board[i].length);
        }
        whichWay = w;

    }

    public int[][] orGeneration(){
        if(whichWay.equals("leftToRight")){
            for(int i = 0; i < 2; i++){
                board[startX][startY+i] = 3;
                board[startX+2][startY+i] = 3;
            }
            for(int i = 0; i < 2; i++){
                board[startX-1][startY+i+2] = 3;
                board[startX+3][startY+i+2] = 3;
            }
            board[startX][startY+4] = 3;
            board[startX+2][startY+4] = 3;
            for(int i = 0; i < 4; i++){
                board[startX+1][startY+i+3] = 3;
            }
        }
        else{
            for(int i = 0; i < 2; i++){
                board[startX][startY-i] = 3;
                board[startX+2][startY-i] = 3;
            }
            for(int i = 0; i < 2; i++){
                board[startX-1][startY-i-2] = 3;
                board[startX+3][startY-i-2] = 3;
            }
            board[startX][startY-4] = 3;
            board[startX+2][startY-4] = 3;
            for(int i = 0; i < 4; i++){
                board[startX+1][startY-i-3] = 3;
            }
        }
        return board;
    }
}
