package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import odczytIZapis.*;
import przebiegGry.cykl;

public class WireworldWindow extends JFrame {
    private JPanel menuPanel;
    private JButton openButton;
    private JLabel iterationLabel;
    private JTextField iterationTextField;
    private JButton startButton;
    private JButton stopButton;
    private JPanel cellPanel;
    private JPanel cellGridPanel;

    public static WireworldWindow wireworldWindow;

    public final JButton CellButton[][];
    private final int cellPanelSizeY = 3600;
    private final int cellPanelSizeX = 3600;


    public WireworldWindow(String title, int x, int[][] tab){
        super(title);

        WireworldWindow.wireworldWindow = this;

        int cellSize = 10;
        int cellHeight = x;
        int cellWidth = x;

        menuPanel = new JPanel();
        cellPanel = new JPanel();
        cellGridPanel = new JPanel();
        openButton = new JButton("Open");
        iterationLabel = new JLabel("Wpisz liczbę iteracji");
        iterationTextField = new JTextField();
        startButton = new JButton("Start");

        menuPanel.setPreferredSize(new Dimension(150, 600));
        cellPanel.setPreferredSize(new Dimension(750, 600));


        this.add(menuPanel, BorderLayout.WEST);
        this.add(cellPanel, BorderLayout.EAST);

        menuPanel.setBackground(Color.BLUE);
        cellPanel.setBackground(Color.BLACK);
        cellPanel.add(cellGridPanel, BorderLayout.CENTER);

        menuPanel.add(openButton);

        iterationLabel.setForeground(Color.yellow);

        menuPanel.add(iterationLabel);
        menuPanel.add(iterationTextField);
        iterationTextField.setPreferredSize(new Dimension(150, 20));
        iterationTextField.setText("10");
        iterationTextField.setHorizontalAlignment(SwingConstants.RIGHT);

        menuPanel.add(startButton);



        cellGridPanel.setBackground(Color.orange);
        CellButton = new JButton[cellHeight + 2][cellWidth + 2];
        cellGridPanel.setLayout(new GridLayout(cellWidth + 2, cellHeight + 2));
        for(int i = 0; i < cellHeight + 2; i++){
            for(int j = 0; j < cellWidth + 2; j++){
                CellButton[i][j] = new JButton("");
                cellGridPanel.add(CellButton[i][j]);
                CellButton[i][j].setPreferredSize(new Dimension(12,12));
                CellButton[i][j].setBackground(Color.BLACK);
                    }
                }
/* Początkowy układ planszy*/
        int v = 0;
        for (int i = 1; i < cellHeight; i++) {
            for (int j = 1; j < cellWidth; j++) {
                v = tab[i][j];

                switch (v) {
                    case (1):
                        CellButton[i][j].setBackground(Color.YELLOW);
                        break;
                    case (2):
                        CellButton[i][j].setBackground(Color.BLUE);
                        break;
                    case (3):
                        CellButton[i][j].setBackground(Color.RED);
                        break;
                    case (0):
                        CellButton[i][j].setBackground(Color.BLACK);
                        break;
                    default:
                        break;
                }
            }
        }


        for (int i = 0; i < cellHeight + 2; i++) {

            CellButton[0][i].setVisible(false);
            CellButton[cellHeight + 1][i].setVisible(false);
        }
        for (int i = 0; i < cellWidth + 2; i++) {
            CellButton[i][0].setVisible(false);
            CellButton[i][cellWidth + 1].setVisible(false);
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(new Dimension(900, 600));
        this.setVisible(true);
        this.setLayout(null);



        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == openButton) {
                    JFileChooser fileChooser = new JFileChooser();
                    int response = fileChooser.showOpenDialog(null);
                    // fileChooser.setCurrentDirectory(new File("."));

                    if(response == JFileChooser.APPROVE_OPTION) {
                        try {
                            FileReader file = new FileReader(fileChooser.getSelectedFile().getAbsolutePath());
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == startButton) {
                    worker = new Worker();
                    worker.execute();
                }
            }
        });
    }

    public void updateCellGridPanel() {
        int[][] tab = new int[20][20];
        odczyt od = new odczyt("C:\\Users\\Kuba\\IdeaProjects\\Wireworld\\src\\com\\company\\elo.txt", 20, 20);
        tab = od.dataFromFile();
        cykl gra = new cykl(tab);
        int v = 0;
        for (int i = 1; i < 20 + 1; i++) {
            for (int j = 1; j < 20 + 1; j++) {
                v = tab[i][j];

                switch (v) {
                    case (1):
                        CellButton[i][j].setBackground(Color.YELLOW);
                        break;
                    case (2):
                        CellButton[i][j].setBackground(Color.BLUE);
                        break;
                    case (3):
                        CellButton[i][j].setBackground(Color.RED);
                        break;
                    case (0):
                        CellButton[i][j].setBackground(Color.BLACK);
                        break;
                    default:
                        break;
                }
            }
        }
    }


    private Worker worker;

    class Worker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
 {
                int x =20;
                int[][] tab = new int[x][x];
                odczyt od = new odczyt("C:\\Users\\Kuba\\IdeaProjects\\Wireworld\\src\\com\\company\\elo.txt", x, x);
                tab = od.dataFromFile();
                cykl gra = new cykl(tab);
                new WireworldWindow("ello", x, tab);
                for (int j = 0; j < 10; j++) {
                    tab = gra.stateChange(tab);
                    wireworldWindow.updateCellGridPanel();
                    System.out.println("jd");
                }

            }

                Thread.sleep(100);

            return null;
        }
    }
}
