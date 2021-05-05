package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Window extends JFrame {
    private JPanel mainPanel;
    private JToolBar menuToolBar;
    private JButton openButton;
    private JLabel iterationPanel;
    private JTextField iterationTextField;
    private JButton STARTButton;
    private JButton stopButton;
    private JLabel programLabel;

    public Window(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(new Dimension(900, 600));
        this.setVisible(true);

        ImageIcon icon = new ImageIcon("wire2.png");

        programLabel.setBackground(Color.black);
        programLabel.setOpaque(true);
        programLabel.setIcon(icon);

        iterationTextField.setSize(new Dimension(40, 20));




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
