package notejava;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 *
 * @author Ryu Saplad>
 */
public class about extends JFrame implements ActionListener, KeyListener {

    JPanel panel;
    JLabel movingObject;
    ImageIcon icon;
    JPanel borderPanel;
    JLabel information;
    JLabel information2;
    JLabel information3;
    JLabel counterLabel;
    JButton back;
    int counter = 0;

    about() {
        back = new JButton("X");
        back.setBounds(425, 5, 20, 20);
        back.setBackground(Color.BLACK);
        back.setFocusable(false);
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        back.setForeground(Color.RED);
        back.addActionListener(this);

        counterLabel = new JLabel();
        counterLabel.setBounds(190, 450, 140, 50);
        counterLabel.setText("Counter: " + counter);
        counterLabel.setFont(new Font("sans-serif", Font.BOLD, 12));
        counterLabel.setForeground(Color.BLACK);

        information = new JLabel();
        information.setBounds(160, 5, 140, 50);
        information.setText("\"ABOUT\"\n");
        information.setFont(new Font("sans-serif", Font.BOLD, 30));
        information.setForeground(Color.BLACK);

        information2 = new JLabel();
        information2.setBounds(115, 60, 250, 50);
        information2.setText("Develop By: Ryu Saplad");
        information2.setFont(new Font("sans-serif", Font.BOLD, 20));
        information2.setForeground(Color.BLACK);

        icon = new ImageIcon("Rocket.gif");

        movingObject = new JLabel();
        movingObject.setIcon(icon);
        movingObject.setBounds(170, 288, 100, 200);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(450, 510));
        panel.setBorder(BorderFactory.createLineBorder(new Color(240, 255, 255)));
        panel.setBackground(new Color(248, 248, 255));
        panel.setLayout(null);
        panel.add(back);
        panel.add(counterLabel);
        panel.add(information);
        panel.add(information2);
        panel.add(movingObject);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(new Dimension(460, 520));
        this.setLayout(new FlowLayout());

        //this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("About");
        this.add(panel);
        this.setVisible(true);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case 65:
                counter += 1;
                counterLabel.setText("Counter: " + counter);
                movingObject.setLocation(movingObject.getX() - 10, movingObject.getY());

                break;
            case 87:
                counter += 1;
                counterLabel.setText("Counter: " + counter);
                movingObject.setLocation(movingObject.getX(), movingObject.getY() - 10);

                break;
            case 83:
                counter += 1;
                counterLabel.setText("Counter: " + counter);
                movingObject.setLocation(movingObject.getX(), movingObject.getY() + 10);

                break;
            case 68:
                counter += 1;
                counterLabel.setText("Counter: " + counter);
                movingObject.setLocation(movingObject.getX() + 10, movingObject.getY());
                break;

        }
        if (counter == 100) {
            String[] choices = {"OK"};
            movingObject.setVisible(false);
            counterLabel.setText("Times up");
            int getInput = JOptionPane.showOptionDialog(null, "TIMES UP!", "Sorry :(!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, choices, choices[0]);

            if (getInput == 0) {
                this.hide();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {   
    }

    @Override
    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.hide();
        }
    }

}
