/*
 * Programme Name: NGramV05
 * Class Name: FileIO Class
 * Description:
 * This class has two methods one for reading the directory and storing the files in an array,
 * and a second method for writing the results to a file.
 *
 */

package ie.atu.sw;

import java.io.*;

public class FileIO {

    // Read the files in the directory and store them in an array
    public File[] listFilesDirectory(String dirPath) {
        File directoryPath = new File(dirPath);
        // List of all files and directories
        File[] fileListParser = directoryPath.listFiles();
        if (fileListParser == null) {
            System.out.println("[ERROR] >> The directory is empty, please enter a valid directory...");
            GlobalVar.setDirectoryPath(null);
        } else {
            System.out.println("List of files and directories in the specified directory:");
            for (File file : fileListParser) {
                System.out.println("File name: " + file.getName());
            }
        }
        return fileListParser;
    }

    // Write the data stored in the table to a text file
    public void writeFile (Object[][] table, String file)  {
        try (PrintWriter pw = new PrintWriter(file)){
            pw.write(GlobalVar.getNGramSize() +"-Gram" + ",");
            pw.write("COUNT" + ",");
            pw.write("RelativeFrequencies(%)" + "\n");
            //Write only the values ignoring the null spaces in the table
            for (Object[] objects : table) {
                if (objects[0] != null) {
                    pw.write(objects[0] + "," + objects[1] + "," + String.format("%.6f", objects[2]) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e.getMessage());
        }
    }
}
