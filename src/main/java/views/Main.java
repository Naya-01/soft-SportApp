package views;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
    public static void main(String[] args){

        // Create window with title "application" that stop the program when closed
        JFrame frame = new JFrame("Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creating a panel that will hold the buttons
        JPanel panel = new JPanel();
        
        // Setup frame with the panel
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setVisible(true); // show the window

        // Creation of a button that will be shown dynamically 
        JButton buttonTemp = new JButton("Dynamic click me");
        buttonTemp.addActionListener((actionEvent) -> {
            System.out.println("dynamic click");
        });

        // Main loop
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            String[] cutLine = line.split(" ");
            
            switch (cutLine[0]) {
                // Change the title
                case "title":
                    String title = line.replace("title ", "");
                    frame.setTitle(title);
                    break;

                // Make the button appear
                case "add":
                    panel.add(buttonTemp);
                    break;

                // Remove the button
                case "remove":
                    panel.remove(buttonTemp);
                    break;

                // Enable dark mode
                case "dark":
                    panel.setBackground(Color.BLACK);
                    break;
                
                // Disable dark mode
                case "light":
                    panel.setBackground(Color.WHITE);
                    break;
            
                // Stop the program
                case "stop":
                    in.close();
                    System.exit(0);
                    break;

                // Unknown command
                default:
                    System.out.println("Unknown command " + cutLine[0]);
                    break;
            }

            /*
             * These lines update the UI
             * Repaint holds for all graphical changes (like color changes)
             * Revalidate holds for UI change (like button add/remove)
             */
            frame.repaint();
            frame.revalidate();
        }
    }
}