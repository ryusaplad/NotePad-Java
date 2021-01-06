package notejava;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Ryu Saplad>
 */
@SuppressWarnings("serial")
public final class NotepadJava extends JFrame implements ActionListener {

    private BufferedWriter writer = null;
    private File myFile;
    private Scanner dataIn;
    private final ImageIcon icon;

    private final JMenuBar barMenu;
    private final JMenu file, edit, format, help;
    private final JMenuItem colorRed, colorBlack, colorBlue, pickColor; // fonts Color
    private final JMenuItem algerianFont, arial, mvBoli, timesNewRoman, formatFont, fontSize, chooseColor; // Fonts
    private final JMenuItem size1, size2, size3, size4, custom; // sizeOfFont
    private final JMenuItem open, save, exit, about;
    private final JPanel panelTop, panel1, panelHorizontal;

    private final JTextArea textArea;

    private final JScrollPane scrollbar;
    private final JScrollPane scrollHori;// Scroll horizontal Need To Fix

    private JFileChooser selectFile;
    private JColorChooser colors;

    @SuppressWarnings("deprecation")
    NotepadJava() throws InterruptedException {
        // calling menu
        barMenu = new JMenuBar();
        barMenu.setBackground(Color.WHITE);

        // adding menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        format = new JMenu("Format");
        help = new JMenu("Help");
        about = new JMenuItem("About");

        //
        edit.setToolTipText("NOT DONE :)");
        // setting Fonts
        file.setFont(new Font("Arial", Font.PLAIN, 12));
        edit.setFont(new Font("Arial", Font.PLAIN, 12));
        format.setFont(new Font("Arial", Font.PLAIN, 12));
        help.setFont(new Font("Arial", Font.PLAIN, 12));
        // adding menus
        barMenu.add(file);
        barMenu.add(edit);
        barMenu.add(format);
        barMenu.add(help);

        // adding items
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");

        formatFont = new JMenu("Font");
        algerianFont = new JMenuItem("ALGERIAN");
        arial = new JMenuItem("Arial");
        mvBoli = new JMenuItem("MV Boli");
        timesNewRoman = new JMenuItem("Times New Roman");

        chooseColor = new JMenu("Font Color");
        colorRed = new JMenuItem("Color: Red");
        colorBlack = new JMenuItem("Color: Black");
        colorBlue = new JMenuItem("Color: Blue");
        pickColor = new JMenuItem("Pick Color");

        fontSize = new JMenu("Font Size");
        size1 = new JMenuItem("size:12");
        size2 = new JMenuItem("size:25");
        size3 = new JMenuItem("size:35");
        size4 = new JMenuItem("Size:45");
        custom = new JMenuItem("Custom..");

        file.add(open);
        file.add(save);
        file.add(exit);

        help.add(about);

        about.addActionListener((ActionEvent e) -> {
            if (e.getSource() == about) {
                new about().show();
            }
        });

        chooseColor.add(colorRed);
        chooseColor.add(colorBlack);
        chooseColor.add(colorBlue);
        chooseColor.add(pickColor);

        fontSize.add(size1);
        fontSize.add(size2);
        fontSize.add(size3);
        fontSize.add(size4);
        fontSize.add(custom);

        formatFont.add(algerianFont);
        formatFont.add(arial);
        formatFont.add(mvBoli);
        formatFont.add(timesNewRoman);

        format.add(fontSize);
        format.add(formatFont);
        format.add(chooseColor);

        // changing background of menu to white (FILE)
        open.setBackground(Color.WHITE);
        save.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        fontSize.setOpaque(true);
        formatFont.setOpaque(true);
        chooseColor.setOpaque(true);
        fontSize.setBackground(Color.WHITE);
        formatFont.setBackground(Color.WHITE);
        chooseColor.setBackground(Color.WHITE);

        // changing background of menu to white (FORMAT)
        format.setBackground(Color.WHITE);
        timesNewRoman.setBackground(Color.WHITE);
        mvBoli.setBackground(Color.WHITE);
        arial.setBackground(Color.WHITE);
        algerianFont.setBackground(Color.WHITE);
        // changing background of menu to white (FORMAT - SIZE)
        size1.setBackground(Color.WHITE);
        size2.setBackground(Color.WHITE);
        size3.setBackground(Color.WHITE);
        size4.setBackground(Color.WHITE);
        // changing background of menu to white (FORMAT - SIZE - Color font)
        colorRed.setBackground(Color.WHITE);
        colorBlack.setBackground(Color.WHITE);
        colorBlue.setBackground(Color.WHITE);

        // adding key
        file.setMnemonic(KeyEvent.VK_F);
        edit.setMnemonic(KeyEvent.VK_E);
        format.setMnemonic(KeyEvent.VK_O);
        help.setMnemonic(KeyEvent.VK_H);
        open.setMnemonic(KeyEvent.VK_O);
        save.setMnemonic(KeyEvent.VK_S);
        exit.setMnemonic(KeyEvent.VK_E);

        file.setFocusable(false);
        edit.setFocusable(false);
        help.setFocusable(false);
        open.setFocusable(false);
        save.setFocusable(false);
        exit.setFocusable(false);

        // adding actionListner
        open.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        // add action listener for fontStyle
        algerianFont.addActionListener(this);
        arial.addActionListener(this);
        mvBoli.addActionListener(this);
        timesNewRoman.addActionListener(this);
        fontSize.addActionListener(this);
        // add action listener for fontSize
        size1.addActionListener(this);
        size2.addActionListener(this);
        size3.addActionListener(this);
        size4.addActionListener(this);
        // adding action listener for font color
        colorRed.addActionListener(this);
        colorBlack.addActionListener(this);
        colorBlue.addActionListener(this);
        pickColor.addActionListener(this);
        custom.addActionListener(this);
        // background of jMenubar
        panelTop = new JPanel();
        panelTop.setBackground(Color.WHITE);
        panelTop.setPreferredSize(new Dimension(12, 19));
        panelTop.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 1));
        panelTop.add(barMenu);

        // adding textArea
        textArea = new JTextArea();
        textArea.setBackground(Color.WHITE);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Scrollbar for text area
        scrollbar = new JScrollPane(textArea);
        scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollbar.getVerticalScrollBar().setBackground(new Color(105, 105, 105));
        scrollbar.setFocusable(false);

        // horizontal Scroll bar
        scrollHori = new JScrollPane();
        scrollHori.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollHori.getVerticalScrollBar().setBackground(new Color(105, 105, 105));
        scrollHori.setFocusable(false);
        scrollHori.getHorizontalScrollBar().setBackground(new Color(25, 5, 3));
        panelHorizontal = new JPanel();
        panelHorizontal.setBackground(Color.gray);
        panelHorizontal.setLayout(new BorderLayout());

        // background of form
        panel1 = new JPanel();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        panel1.setBackground(Color.gray);
        panel1.setLayout(new BorderLayout());
        panel1.add(scrollbar);
        icon = new ImageIcon("icons8_txt_320px.png");
        this.setTitle("Notepad Java");
        this.setIconImage(icon.getImage());
        this.setSize(new Dimension(1000, 500));
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Adding compenents to main frame
        this.add(panel1);
        this.add(panelTop, BorderLayout.NORTH);
        // end of Adding
        this.setVisible(true);
        undoText(); // for redo and undo text
        windowClose(); // for asking to save file or not

    }

    // adding asking to save before to close or open file.
    public void windowClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (!textArea.getText().isEmpty()) {
                    String[] buttons = { "Save", "Dont Save", "Cancel" };

                    int PromptResult = JOptionPane.showOptionDialog(null,
                            "Do you want to save the changes From default.txt?", "Notepad java",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[2]);
                    if (PromptResult == JOptionPane.YES_OPTION) {
                        saveFile();
                    } else if (PromptResult == 1) {
                        System.exit(0);
                    } else if (PromptResult == 2) {
                        System.out.println("Ok");
                    }
                } else {
                    System.exit(0);
                }
            }
        });
    }

    // method to open a file
    private void openFile() {
        selectFile = new JFileChooser();
        selectFile.setCurrentDirectory(new File("C:\\Users\\usetr\\Desktop"));
        int response = selectFile.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            myFile = selectFile.getSelectedFile();
            try {
                dataIn = new Scanner(myFile);
                if (myFile.isFile()) {
                    if (!textArea.getText().isEmpty()) {
                        textArea.setText("");
                    }
                    while (dataIn.hasNext()) {
                        String checkLines = dataIn.nextLine();
                        textArea.append(checkLines + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This is not a File", "OOps", JOptionPane.WARNING_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NotepadJava.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // method to save file
    private void saveFile() {
        selectFile = new JFileChooser();
        selectFile.setCurrentDirectory(new File("C:\\Users\\usetr\\Desktop"));
        int response = selectFile.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            myFile = selectFile.getSelectedFile();
            try {
                writer = new BufferedWriter(new FileWriter(myFile));
                textArea.write(writer);
            } catch (IOException dException) {
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Opening File
        if (e.getSource() == open) {
            if (!textArea.getText().isEmpty()) {
                String[] buttons = { "Save", "Dont Save", "Cancel" };

                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Do you want to save the changes From default.txt?", "Notepad java", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, buttons, buttons[2]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    saveFile();
                } else if (PromptResult == 1) {
                    openFile();
                } else if (PromptResult == 2) {
                }
            } else {
                openFile();
            }

            // Saving File
        } else if (e.getSource() == save) {
            saveFile();

        } else if (e.getSource() == exit) {
            System.out.println("GOOD BYE");
            System.exit(0);
        } else if (e.getSource() == algerianFont) {
            textArea.setFont(new Font("Algerian", Font.PLAIN, 19));
        } else if (e.getSource() == arial) {
            textArea.setFont(new Font("Arial", Font.PLAIN, 19));
        } else if (e.getSource() == mvBoli) {
            textArea.setFont(new Font("MV Boli", Font.PLAIN, 19));
        } else if (e.getSource() == timesNewRoman) {
            textArea.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        } else if (e.getSource() == size1) {
            textArea.setFont(new Font(textArea.getText(), Font.PLAIN, 12));
            // setting size of the font
        } else if (e.getSource() == size2) {
            textArea.setFont(new Font(" ", Font.PLAIN, 25));
        } else if (e.getSource() == size3) {
            textArea.setFont(new Font(" ", Font.PLAIN, 35));
        } else if (e.getSource() == size4) {
            textArea.setFont(new Font(" ", Font.PLAIN, 45));
        } else if (e.getSource() == custom) {
            String s = JOptionPane.showInputDialog(null, "Size", "Font Size", JOptionPane.INFORMATION_MESSAGE);
            int convertInput = Integer.parseInt(s);
            textArea.setFont(new Font(" ", Font.PLAIN, convertInput));
            // end of setting size

            // start setting of font folor of text
        } else if (e.getSource() == colorRed) {
            textArea.setForeground(Color.RED);
        } else if (e.getSource() == colorBlack) {
            textArea.setForeground(Color.BLACK);
        } else if (e.getSource() == colorBlue) {
            textArea.setForeground(Color.BLUE);
        } else if (e.getSource() == pickColor) {
            colors = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "COLOR", Color.BLACK);
            textArea.setForeground(color);
            // end of setting color of text
        }

    }

    // undo and redo Text
    @SuppressWarnings("deprecation")
    public void undoText() {
        UndoManager undoManager = new UndoManager();
        Document doc = textArea.getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());

            }
        });

        InputMap im = textArea.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = textArea.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Undo");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Redo");

        am.put("Undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (undoManager.canUndo()) {
                        undoManager.undo();
                    }
                } catch (CannotUndoException exp) {
                    exp.printStackTrace();
                }
            }
        });
        am.put("Redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (undoManager.canRedo()) {
                        undoManager.redo();
                    }
                } catch (CannotUndoException exp) {
                    exp.printStackTrace();
                }
            }
        });
    }

}
