package struktury;
import java.util.Arrays;

public class AND {
    protected int startX;
    protected int startY;
    protected String whichWay;
    public int[][] board;

    public AND(int x, int y, String w, int[][] b) {
        startX = x;
        startY = y;
        board = new int[b.length][];
        for (int i = 0; i < b.length; i++) {
            board[i] = new int[b[i].length];
            System.arraycopy(b[i], 0, board[i], 0, board[i].length);
        }
        whichWay = w;

    }

    public int[][] andGeneration() {
        if (whichWay.equals("leftToRight")) {
            for (int i = 0; i < 8; i++) {
                board[startX - 3][startY + i] = 3;
            }
            board[startX][startY] = 3;
            for (int i = 0; i < 3; i++) {
                board[startX + 1 + i][startY + 1] = 3;
                board[startX + 1 + i][startY + 3] = 3;
            }
            board[startX + 4][startY + 2] = 3;
            board[startX][startY + 4] = 3;
            board[startX][startY + 5] = 3;
            board[startX - 1][startY + 5] = 3;
            board[startX + 1][startY + 5] = 3;
            board[startX][startY + 6] = 3;
            board[startX - 1][startY + 7] = 3;
            board[startX + 1][startY + 7] = 3;
            board[startX - 2][startY + 8] = 3;
            board[startX - 2][startY + 9] = 3;
            board[startX - 2][startY + 10] = 3;
            board[startX + 2][startY + 8] = 3;
            board[startX + 2][startY + 9] = 3;
            board[startX + 2][startY + 10] = 3;
            board[startX + 1][startY + 9] = 3;
            board[startX + 3][startY + 9] = 3;
            board[startX - 1][startY + 11] = 3;
            board[startX][startY + 11] = 3;
            board[startX + 1][startY + 11] = 3;
            board[startX + 3][startY + 11] = 3;
            board[startX + 3][startY + 12] = 3;
            board[startX + 2][startY + 13] = 3;
            board[startX + 1][startY + 14] = 3;

        } else {
            for (int i = 0; i < 8; i++) {
                board[startX - 3][startY - i] = 3;
            }
            board[startX][startY] = 3;
            for (int i = 0; i < 3; i++) {
                board[startX + 1 + i][startY - 1] = 3;
                board[startX + 1 + i][startY - 3] = 3;
            }
            board[startX + 4][startY - 2] = 3;
            board[startX][startY - 4] = 3;
            board[startX][startY - 5] = 3;
            board[startX - 1][startY - 5] = 3;
            board[startX + 1][startY - 5] = 3;
            board[startX][startY - 6] = 3;
            board[startX - 1][startY - 7] = 3;
            board[startX + 1][startY - 7] = 3;
            board[startX - 2][startY - 8] = 3;
            board[startX - 2][startY - 9] = 3;
            board[startX - 2][startY - 10] = 3;
            board[startX + 2][startY - 8] = 3;
            board[startX + 2][startY - 9] = 3;
            board[startX + 2][startY - 10] = 3;
            board[startX + 1][startY - 9] = 3;
            board[startX + 3][startY - 9] = 3;
            board[startX - 1][startY - 11] = 3;
            board[startX][startY - 11] = 3;
            board[startX + 1][startY - 11] = 3;
            board[startX + 3][startY - 11] = 3;
            board[startX + 3][startY - 12] = 3;
            board[startX + 2][startY - 13] = 3;
            board[startX + 1][startY - 14] = 3;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        return board;
    }
}