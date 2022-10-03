/*
 * Programme Name: NGramV05
 * Class Name: GlobalVar Class
 * Description:
 * This class is destined to store the parameters introduced by the user:
 * NGramSize - size of N-Gram
 * directoryPath - directory to be analyzed
 * fileListInput - List of files in directory
 * fileNameOutput - Name and directory of the output file after parsing
 * methodParser - Method to be used in the analysis of files (Hash or Linear).
 * Get and set methods were defined for each variable.
 */

package ie.atu.sw;

import java.io.File;

public class GlobalVar {
    private static int nGramSize;
    private static String directoryPath;
    private static File[] fileListInput;
    private static String fileNameOutput;
    private static int methodParser;

    public static int getNGramSize() {
        return nGramSize;
    }

    public static void setNGramSize(int nGramSize) {
        GlobalVar.nGramSize = nGramSize;
    }

    public static String getDirectoryPath() {
        return directoryPath;
    }

    public static void setDirectoryPath(String directoryPath) {
        GlobalVar.directoryPath = directoryPath;
    }

    public static File[] getFileListInput() {
        return fileListInput;
    }

    public static void setFileListInput(File[] fileListInput) {
        GlobalVar.fileListInput = fileListInput;
    }

    public static String getFileNameOutput() {
        return fileNameOutput;
    }

    public static void setFileNameOutput(String fileNameOutput) {
        GlobalVar.fileNameOutput = fileNameOutput;
    }

    public static int getMethodParser() {
        return methodParser;
    }

    public static void setMethodParser(int methodParser) {
        GlobalVar.methodParser = methodParser;
    }
}
