/*
 * Programme Name: NGramV05
 * Class Name: Menu Class
 * Description:
 * This class acts as a user interface with a simplified menu.
 * The user must configure the analysis of texts by introducing the directory (option 1 - getTextDirectory),
 * specify the size of the N-Gram (option 2 - getSizeNGram), define the output file (option 3 - getOutputFile),
 * and choose the method to build o N-Gram (option 4 - buildNGram). The option 5 'Quits' the application.
 * The user can choose between two methods HashCode Function or Linear Search.
 * The values entered by the user are displayed on the screen below the menu header.
 * The parameters introduced by the user are stored in the GlobalVar class
 */

package ie.atu.sw;

import java.util.Scanner;

public class Menu {
    private boolean keepRunning = true;
    private boolean subMenuRunning = true;
    private final Scanner s;

    public Menu() {
        s = new Scanner(System.in); //Creates a new instance of a scanner, s.
    }

    public void start() {
        do {
            showOptions();
            try {
                int choice = Integer.parseInt(s.next()); //Waits on Input from User
                if (choice == 1) {// Specify Text File Directory
                    getTextDirectory();
                } else if (choice == 2) {// Specify n-Gram Size
                    getSizeNGram();
                } else if (choice == 3) {// Specify Output File
                    getOutputFile();
                } else if (choice == 4) {// Build n-Grams
                    buildNGram();
                } else if (choice == 5) {// Quit Application.
                    System.out.println("[INFO] Shutting down...please wait...");
                    keepRunning = false;
                } else {
                    System.out.println("[ERROR] Invalid input.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("[ERROR] Invalid input.");
                System.out.println(nfe.getMessage());
            }
        } while (keepRunning);
    }

    private void getTextDirectory() {
        System.out.println(">> Specify Text File Directory: ");
        GlobalVar.setDirectoryPath(s.next());
        GlobalVar.setFileListInput(new FileIO().listFilesDirectory(GlobalVar.getDirectoryPath()));
    }

    private void getSizeNGram() {
        System.out.println(">> Specify n-Gram Size: ");
        try {
            GlobalVar.setNGramSize(Integer.parseInt(s.next()));
            System.out.println("The files will be processed in " + GlobalVar.getNGramSize() + "-grams");
        } catch (NumberFormatException nfe) {
            GlobalVar.setNGramSize(0);
            System.out.println("[ERROR] NumberFormat Exception: invalid input n-Gram Size");
            System.out.println(nfe.getMessage());
        }
    }

    private void getOutputFile() {
        System.out.println(">> Specify Output File Name");
        GlobalVar.setFileNameOutput((s.next()) + ".txt");
        System.out.println("The output file: " + GlobalVar.getFileNameOutput());
    }

    private void buildNGram() {
        if (GlobalVar.getDirectoryPath() == null) {
            System.out.println("[ERROR] >> Please Specify Text File Directory... ");
        } else if (GlobalVar.getNGramSize() < 1) {
            System.out.println("[ERROR] >> Please Specify n-Gram Size... ");
        } else if (GlobalVar.getFileNameOutput() == null) {
            System.out.println("[ERROR] >> Please Specify Output File Name... ");
        } else {
            buildNGramOptions();
        }
    }

    private void buildNGramOptions() {
        int innerChoice = 0;
        do {
            showHead();
            System.out.println("(1) Use HashCode Function");
            System.out.println("(2) Use Linear Search");
            System.out.println("(3) Return To Main Menu");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("[INFO] Method 2 may be a little slower than method 1...");
            System.out.println("Please select method ");
            try {
                innerChoice = Integer.parseInt(s.next());
                if (innerChoice == 1) {
                    GlobalVar.setMethodParser(innerChoice);
                    System.out.println("The files will be processed with HashCode Function");
                    subMenuRunning = false;
                } else if (innerChoice == 2) {
                    GlobalVar.setMethodParser(innerChoice);
                    System.out.println("The files will be processed with Linear Search Function");
                    subMenuRunning = false;
                } else if (innerChoice == 3) {
                    GlobalVar.setMethodParser(0);
                    System.out.println("[INFO] Return To Main Menu");
                    subMenuRunning = false;
                } else {
                    System.out.println("[ERROR] Invalid input.");
                }
            } catch (NumberFormatException nfe) {
                GlobalVar.setMethodParser(0);
                System.out.println("[ERROR] NumberFormat Exception: invalid input ");
                System.out.println(nfe.getMessage());
            }
        } while (subMenuRunning);
        if (innerChoice != 3) new Parser().parseDir();
    }

    private void showOptions() {
        showHead();
        System.out.println("(1) Specify Text File Directory");
        System.out.println("(2) Specify n-Gram Size");
        System.out.println("(3) Specify Output File Name");
        System.out.println("(4) Select Method and Build n-Grams");
        System.out.println("(5) Quit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Select an option [1-5]>");
    }

    private void showHead() {
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("|      ATU - Dept. Computer Science & Applied Physics      |");
        System.out.println("|                                                          |");
        System.out.println("|                  N-Gram Frequency Builder                |");
        System.out.println("|                                                          |");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (GlobalVar.getDirectoryPath() != null)
            System.out.println("[INFO] >> File Directory: " + GlobalVar.getDirectoryPath());
        if (GlobalVar.getNGramSize() > 0)
            System.out.println("[INFO] >> n-Gram Size: " + GlobalVar.getNGramSize() + "-gram");
        if (GlobalVar.getFileNameOutput() != null)
            System.out.println("[INFO] >> Output File: " + GlobalVar.getFileNameOutput());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
