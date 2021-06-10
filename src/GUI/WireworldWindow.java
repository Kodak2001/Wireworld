package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.Timer;


import odczytIZapis.*;
import przebiegGry.cykl;

public class WireworldWindow extends JFrame {
    private JPanel menuPanel;
    private JButton openButton;
    private JLabel iterationLabel;
    private JTextField iterationTextField;
    private JLabel boardSizeLabel;
    private JTextField boardSize;
    private JButton startButton;
    private JButton stopButton;
    private JPanel cellPanel;
    private JPanel cellGridPanel;
    public static File file;

    public static WireworldWindow wireworldWindow;

    public final JButton CellButton[][];
    int ile;


    public WireworldWindow(String title, int x, int[][] tab){
        super(title);

        wireworldWindow = this;



        int cellHeight = x;
        int cellWidth = x;

        menuPanel = new JPanel();
        cellPanel = new JPanel();
        cellGridPanel = new JPanel();
        openButton = new JButton("Open");
        iterationLabel = new JLabel("Wpisz liczbę iteracji");
        iterationTextField = new JTextField();
        boardSizeLabel = new JLabel("Wpisz rozmiar planszy");
        boardSize = new JTextField();
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

        menuPanel.add(boardSizeLabel);
        menuPanel.add(boardSize);
        boardSizeLabel.setForeground(Color.yellow);
        boardSize.setPreferredSize(new Dimension(150, 20));
        boardSize.setText("20");
        boardSize.setHorizontalAlignment(SwingConstants.RIGHT);



        Action action = new Action();
        menuPanel.add(startButton);
        startButton.addActionListener(action);



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
                    fileChooser.setCurrentDirectory(new File("."));

                    if(response == JFileChooser.APPROVE_OPTION) {
                        file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                }
            }
        });
    }



    public void updateCellGridPanel(int tab[][]) {

        int v = 0;
        for (int i = 1; i < 20 + 2; i++) {
            for (int j = 1; j < 20 + 2; j++) {
                v = tab[i][j];

                switch (v) {
                    case (3):
                        CellButton[i][j].setBackground(Color.YELLOW);
                        break;
                    case (2):
                        CellButton[i][j].setBackground(Color.BLUE);
                        break;
                    case (1):
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


    private class Action implements ActionListener{

        private Worker worker;

        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == startButton) {

                ile = Integer.parseInt(iterationTextField.getText());

                worker = new Worker();
                worker.execute();
            }
        }
    }




    class Worker extends SwingWorker<Void, Void> {
        int x = 22;
        private int[][] tab = new int[x][x];
        @Override
        protected Void doInBackground() {



            odczyt od = new odczyt(WireworldWindow.file.getAbsolutePath(), x, x);
            tab = od.dataFromFile();
            cykl gra = new cykl(tab);
            Timer timer = new Timer();
            Timer timer2 = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    wireworldWindow.updateCellGridPanel(tab);
                    for (int j = 0; j < ile; j++) {
                        System.out.println(" ");
                        for (int i = 1; i < 22; i++) {
                            System.out.println(Arrays.toString(tab[i]));
                        }
                        tab = gra.stateChange(tab);

                        timer2.schedule(new TimerTask() {
                            @Override
                            public void run() {  }
                        }, 1000,1000);
                    }

                }
            }, 1000,1000);


            return null;
        }
    }
}