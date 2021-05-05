package struktury;
import java.util.Arrays;

public class XOR {
    protected int startX;
    protected int startY;
    protected String whichWay;
    public int[][] board;

    public XOR(int x, int y, String w, int[][] b){
        startX = x;
        startY = y;
        board = new int[b.length][];
        for(int i = 0; i < b.length; i++){
            board[i] = new int[b[i].length];
            System.arraycopy(b[i], 0, board[i], 0, board[i].length);
        }
        whichWay = w;

    }

    public int[][] xorGeneration(){
        if(whichWay.equals("leftToRight")){
            for(int i = 0; i < 2; i++){
                board[startX][startY+i] = 3;
                board[startX+2][startY+i] = 3;
            }
            board[startX-1][startY+2] = 3;
            board[startX+3][startY+2] = 3;
            for(int i = 0; i < 2; i++){
                board[startX-2][startY+i+3] = 3;
                board[startX+4][startY+i+3] = 3;
            }
            board[startX-1][startY+5] = 3;
            board[startX+3][startY+5] = 3;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    board[startX+i][startY+4+j] = 3;
                }
            }
            board[startX+1][startY+5] = 0;
            board[startX+1][startY+8] = 3;
        }
        else{
            for(int i = 0; i < 2; i++){
                board[startX][startY-i] = 3;
                board[startX+2][startY-i] = 3;
            }
            board[startX-1][startY-2] = 3;
            board[startX+3][startY-2] = 3;
            for(int i = 0; i < 2; i++){
                board[startX-2][startY-i-3] = 3;
                board[startX+4][startY-i-3] = 3;
            }
            board[startX-1][startY-5] = 3;
            board[startX+3][startY-5] = 3;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    board[startX+i][startY-4-j] = 3;
                }
            }
            board[startX+1][startY-5] = 0;
            board[startX+1][startY-8] = 3;
        }
        for(int i = 0; i < 10; i++){
            System.out.println(Arrays.toString(board[i]));
        }
        return board;
    }
}
