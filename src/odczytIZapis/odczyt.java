package odczytIZapis;
import java.io.File;
import java.util.Scanner;
import struktury.*;
import java.io.FileNotFoundException;

public class odczyt {
    private int x;
    private int y;
    private String structType;
    private int structX;
    private int structY;
    private String whichWay;
    public int[][] board;
    private String path;
    private File plikTxt;
    private Scanner dane;

    public odczyt(String path, int x, int y){
        this.path = path;
        board = new int [x][y];

    }
    public int[][] dataFromFile() {
        try {
            plikTxt = new File(path);
            dane = new Scanner(plikTxt);
        }
        catch(FileNotFoundException e){
            System.out.println("elo");
        }
        while (dane.hasNext()) {
            structType = dane.next();
            structX = dane.nextInt();
            structY = dane.nextInt();

            if (structType.equals("AND")) {
                whichWay = dane.next();
                AND and = new AND(structX, structY, whichWay, board);
                board = and.andGeneration();
            } else if (structType.equals("OR")) {
                whichWay = dane.next();
                OR or = new OR(structX, structY, whichWay, board);
                board = or.orGeneration();
            } else if (structType.equals("XOR")) {
                whichWay = dane.next();
                XOR xor = new XOR(structX, structY, whichWay, board);
                board = xor.xorGeneration();

            } else if (structType.equals("DIODE")) {
                whichWay = dane.next();
                dioda diode = new dioda(structX, structY, whichWay, board);
                board = diode.diodeGeneration();

            } else if (structType.equals("CABLE")) {
                kabel cable = new kabel(structX, structY, board);
                board = cable.kabelGeneration();
            } else if (structType.equals("HEAD")) {
                board[structX][structY] = 1;
            } else if (structType.equals("TAIL")) {
                board[structX][structY] = 2;
            } else if (structType.equals("CONDUCTOR")) {
                board[structX][structY] = 3;
            }
        }
        return board;
    }
}
