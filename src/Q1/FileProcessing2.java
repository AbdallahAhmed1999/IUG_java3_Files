/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Abdallah_Ahmed
 */
public class FileProcessing2 extends JFrame implements ActionListener {

    private JTextArea area;
    private JMenu file, edit;
    private JMenuItem open, save, close, exit, font, color;

    public FileProcessing2() {

        area = new JTextArea();
        area.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(area);
        add(scroll);

        file = new JMenu("File");
        open = new JMenuItem("Open");
        open.setMnemonic('O');;
        open.addActionListener(this);
        save = new JMenuItem("Save");
        save.setMnemonic('S');
        save.addActionListener(this);
        close = new JMenuItem("Close");
        close.setMnemonic('C');
        close.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.setMnemonic('E');
        exit.addActionListener(this);
        file.add(open);
        file.add(save);
        file.add(close);
        file.addSeparator();
        file.add(exit);

        edit = new JMenu("Edit");
        font = new JMenuItem("Font");
        font.setMnemonic('F');
        font.addActionListener(this);
        color = new JMenuItem("Color");
        color.setMnemonic('C');
        color.addActionListener(this);
        edit.add(font);
        edit.add(color);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(edit);

        setJMenuBar(menuBar);

        setTitle("File Processing");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FileProcessing2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Open":
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(FileProcessing2.this);
                File f = chooser.getSelectedFile();
                try {
                    Scanner s = new Scanner(f);
                    while (s.hasNext()) {
                        area.append(s.nextLine() + "\n");
                    }
                } catch (FileNotFoundException | NullPointerException ex) {
                    System.out.println("Exception");
                }
                break;
            case "Save":
                JFileChooser saveChooser = new JFileChooser();
                saveChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                saveChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
                int value = saveChooser.showSaveDialog(FileProcessing2.this);
                if (value == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter fileWriter = new FileWriter(saveChooser.getSelectedFile() + ".txt");
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        printWriter.write(area.getText());
                        printWriter.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
                break;
            case "Close":
                area.setText("");
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Font":
                try {
                    String s = (String) JOptionPane.showInputDialog(
                            null,
                            "Choose Font size",
                            "Font size",
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[]{"12", "14", "18", "24", "30", "36", "48", "60", "72", "84"},
                            "Blue"
                    );
                    area.setFont(new Font(Font.SERIF, Font.PLAIN, Integer.parseInt(s)));
                } catch (Exception ex) {
                    System.out.println("Exception");
                }
                break;
            case "Color":
                try {
                    Color selectedColor = JColorChooser.showDialog(FileProcessing2.this, "a", color.getBackground()); //modal
                    area.setForeground(selectedColor);
                } catch (Exception ex) {
                    System.out.println("Exception");
                }
                break;
        }
    }
}
