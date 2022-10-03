/*
 * Programme Name: NGramV05
 * Class Name: Parser Class
 * Description:
 * This class has three methods one to read loop the array of files (parseDir) and call the parse method,
 * this second method reads line by line from each file, does the text treatment to remove unwanted characters
 * and then with each word divide the ngrams (getNgram method).
 * After processing all files in the directory, write the results to the text file.
 */

package ie.atu.sw;
import java.io.*;

public class Parser {
    // loops around the file array, processing each one according to the
    // definitions in the variables stored in the GlobalVar class.
    public void parseDir() {
        for (File file : GlobalVar.getFileListInput()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getAbsolutePath());
            parse(file);
        }
        // Write the results that are stored in the table to a file
        new FileIO().writeFile(BuildTable.getTable(), GlobalVar.getFileNameOutput());
        //restart the table
        Object[][] temp = new Object[26][3];
        BuildTable.setTable(temp);
    }

    private void parse(File file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Splitting a Java String by Multiple Delimiters ( hyphen space dot )
                String[] words = line.split("[—\\s+-/'.']");
                for (String word : words) {
                    word = word.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (word.length() >= GlobalVar.getNGramSize()) {
                        //Builds an array Ngrams of each word
                        String[] ngrams = getNgram(word, GlobalVar.getNGramSize());
                        for (String ngram : ngrams) {
                            //Update table with new Ngram
                            BuildTable.addNGram(ngram);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>>>>>> MAP TABLE  <<<<<<<<<<<");
        BuildTable.printTable(BuildTable.getTable());
    }

    public String[] getNgram(String s, int n) {
        String[] ngramTemp = new String[s.length() - n + 1];
        for (int i = 0; i <= s.length() - n; i++) {
            ngramTemp[i] = s.substring(i, i + n);
        }
        return ngramTemp;
    }
}
