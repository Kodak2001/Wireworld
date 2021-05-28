package GUI;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WireworldWindow extends JFrame {
    private JPanel menuPanel;
    private JButton openButton;
    private JLabel iterationLabel;
    private JTextField iterationTextField;
    private JButton startButton;
    private JButton stopButton;
    private JPanel cellPanel;
    private JPanel cellGridPanel;

    public final JButton CellButton[][];
    private final int cellPanelSizeY = 3600;
    private final int cellPanelSizeX = 3600;


    public WireworldWindow(String title){
        super(title);

        int cellSize = 10;
        int cellHeight = 50;
        int cellWidth = 50;

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
                    // fileChooser.setCurrentDirectory(new File("."));

                    if(response == JFileChooser.APPROVE_OPTION) {
                        try {
                            FileReader file = new FileReader(fileChooser.getSelectedFile().getAbsolutePath());
                            int symbole = file.read();
                            while(symbole != -1){
                                System.out.print((char)symbole);
                                symbole = file.read();
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });


    }
}