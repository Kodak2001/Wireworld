package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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
    private JPanel cellPanel;
    private JPanel cellGridPanel;
    public static File file;

    public static WireworldWindow wireworldWindow;

    public final JButton CellButton[][];
    int ile;
    int map;


    public WireworldWindow(String title) {
        super(title);

        wireworldWindow = this;


        menuPanel = new JPanel();
        cellPanel = new JPanel();
        cellGridPanel = new JPanel();
        openButton = new JButton("Open");
        iterationLabel = new JLabel("Wpisz liczbę iteracji");
        iterationTextField = new JTextField();
        boardSizeLabel = new JLabel("Rozmiar planszy z pliku");
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
        CellButton = new JButton[45][45];
        cellGridPanel.setLayout(new GridLayout(45, 45));
        for (int i = 0; i < 45; i++) {
            for (int j = 0; j < 45; j++) {
                CellButton[i][j] = new JButton("");
                cellGridPanel.add(CellButton[i][j]);
                CellButton[i][j].setPreferredSize(new Dimension(12, 12));
                CellButton[i][j].setBackground(Color.BLACK);
            }
        }

        for (int i = 0; i < 45; i++) {

            CellButton[0][i].setVisible(false);
            CellButton[44][i].setVisible(false);
        }
        for (int i = 0; i < 45; i++) {
            CellButton[i][0].setVisible(false);
            CellButton[i][44].setVisible(false);
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(new Dimension(900, 600));
        this.setVisible(true);
        this.setLayout(null);


        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == openButton) {
                    JFileChooser fileChooser = new JFileChooser();
                    int response = fileChooser.showOpenDialog(null);
                    fileChooser.setCurrentDirectory(new File("."));

                    if (response == JFileChooser.APPROVE_OPTION) {
                        file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        map = Integer.parseInt(boardSize.getText());

                    }
                }
            }
        });
    }


    public void updateCellGridPanel(int tab[][]) {

        int v = 0;
        for (int i = 1; i < map + 2; i++) {
            for (int j = 1; j < map + 2; j++) {
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


    private class Action implements ActionListener {

        private Worker worker;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {

                ile = Integer.parseInt(iterationTextField.getText());

                worker = new Worker();
                worker.execute();
            }
        }
    }


    class Worker extends SwingWorker<Void, Void> {
        int x = map + 2;
        private int[][] tab = new int[x][x];

        @Override
        protected Void doInBackground() {


            odczyt od = new odczyt(WireworldWindow.file.getAbsolutePath(), x, x);
            tab = od.dataFromFile();
            wireworldWindow.updateCellGridPanel(tab);
            cykl gra = new cykl(tab);

            for (int j = 0; j < ile; j++) {
                for (int i = 1; i < map + 2; i++) {
                }

                tab = gra.stateChange(tab);
                wireworldWindow.updateCellGridPanel(tab);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                od.dataToFile(tab);
            }

        return null;
        }
    }
}