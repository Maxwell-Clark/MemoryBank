package memory_bank;

// Java Program to create a text editor using java
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class NotePad extends JFrame implements ActionListener {
    // Text component
    JTextArea textArea;

    // Frame
    JFrame frame;

    // Constructor
    NotePad()
    {
        // Create a frame
        frame = new JFrame("editor");

        try {
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e) {
        }

        // Text component
        textArea = new JTextArea();

        // Create a menubar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu for menu
        JMenu fileMenu = new JMenu("File");

        // Create menu items
        createMenuItem(fileMenu, "New");
        createMenuItem(fileMenu, "Open");
        createMenuItem(fileMenu, "Save");
        createMenuItem(fileMenu, "Print");

        // Create amenu for menu
        JMenu editMenu = new JMenu("Edit");

        // Create menu items
        createMenuItem(editMenu, "Cut");
        createMenuItem(editMenu, "Copy");
        createMenuItem(editMenu, "Paste");

        JMenuItem closeMenu = new JMenuItem("Close");

        JMenu helpMenu = new JMenu("Help");

        createMenuItem(helpMenu, "About");

        closeMenu.addActionListener(this);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(closeMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
        frame.add(textArea);
        frame.setSize(500, 500);
        frame.show();
    }

    public void createMenuItem(JMenu menu, String txt) {
        JMenuItem menuItem = new JMenuItem(txt);
        menuItem.addActionListener(this);
        menu.add(menuItem);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent event)
    {
        String action = event.getActionCommand();

        if (action.equals("Cut")) {
            textArea.cut();
        }
        else if (action.equals("Copy")) {
            textArea.copy();
        }
        else if (action.equals("Paste")) {
            textArea.paste();
        }
        else if (action.equals("Save")) {
            // Create an object of JFileChooser class
            JFileChooser fileChooser = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int saveDialog = fileChooser.showSaveDialog(null);

            if (saveDialog == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter filewriter = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

                    // Write
                    bufferedWriter.write(textArea.getText());

                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
        }
        else if (action.equals("Print")) {
            try {
                // print the file
                textArea.print();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(frame, evt.getMessage());
            }
        }
        else if (action.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser fileChooser = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog
            int openDialog = fileChooser.showOpenDialog(null);

            // If the user selects a file
            if (openDialog == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fileDirectory = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    // String
                    String string1 = "", string2 = "";

                    // File reader
                    FileReader fileReader = new FileReader(fileDirectory);

                    // Buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    // Initialize sl
                    string2 = bufferedReader.readLine();

                    // Take the input from the file
                    while ((string1 = bufferedReader.readLine()) != null) {
                        string2 = string2 + "\n" + string1;
                    }

                    // Set the text
                    textArea.setText(string2);
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
        }
        else if (action.equals("New")) {
            textArea.setText("");
        }
        else if (action.equals("Close")) {
            frame.setVisible(false);
        }
        else if (action.equals("About")) {
            //Todo
        }
    }

}