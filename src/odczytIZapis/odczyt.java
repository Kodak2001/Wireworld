package odczytIZapis;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import struktury.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


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
    private PrintWriter dataToTxt;

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
            System.out.println("Blad - nie znaleziono pliku z danymi");
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
        dane.close();
        return board;
    }

    public void dataToFile(int[][] currentBoard ) {
        try {
            plikTxt = new File(path);
            dane = new Scanner(plikTxt);
        }
        catch(FileNotFoundException e){
            System.out.println("elo");
        }

        try {
            File zapis = new File("zapis.txt");
            zapis.createNewFile();
        } catch (IOException e) {
            System.out.println("Blad zapisu");
            e.printStackTrace();
        }
        try {
            dataToTxt = new PrintWriter("zapis.txt");
        } catch(FileNotFoundException e){
            System.out.println("Blad w zapisie");
        }

        while (dane.hasNext()) {
            structType = dane.next();
            structX = dane.nextInt();
            structY = dane.nextInt();

            if (structType.equals("AND")) {
                whichWay = dane.next();
                dataToTxt.print(structType);
                dataToTxt.print(" ");
                dataToTxt.print(structX);
                dataToTxt.print(" ");
                dataToTxt.print(structY);
                dataToTxt.print(" ");
                dataToTxt.println(whichWay);

            } else if (structType.equals("OR")) {
                whichWay = dane.next();
                dataToTxt.print(structType);
                dataToTxt.print(" ");
                dataToTxt.print(structX);
                dataToTxt.print(" ");
                dataToTxt.print(structY);
                dataToTxt.print(" ");
                dataToTxt.println(whichWay);
            } else if (structType.equals("XOR")) {
                whichWay = dane.next();
                dataToTxt.print(structType);
                dataToTxt.print(" ");
                dataToTxt.print(structX);
                dataToTxt.print(" ");
                dataToTxt.print(structY);
                dataToTxt.print(" ");
                dataToTxt.println(whichWay);

            } else if (structType.equals("DIODE")) {
                whichWay = dane.next();
                dataToTxt.print(structType);
                dataToTxt.print(" ");
                dataToTxt.print(structX);
                dataToTxt.print(" ");
                dataToTxt.print(structY);
                dataToTxt.print(" ");
                dataToTxt.println(whichWay);

            } else if (structType.equals("CABLE")) {
                dataToTxt.print(structType);
                dataToTxt.print(" ");
                dataToTxt.print(structX);
                dataToTxt.print(" ");
                dataToTxt.println(structY);
            }
        }
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++){
                if(currentBoard[i][j] == 1){
                    dataToTxt.print("HEAD ");
                    dataToTxt.print(i);
                    dataToTxt.print(" ");
                    dataToTxt.println(j);
                } else if(currentBoard[i][j] == 2){
                    dataToTxt.print("TAIL ");
                    dataToTxt.print(i);
                    dataToTxt.print(" ");
                    dataToTxt.println(j);
                }
            }
        }
        dataToTxt.close();
    }
}
