


package ie.atu.sw;

public class TestRunner {

    public static void main(String[] args) {
        GlobalVar.setDirectoryPath("./textFiles");
        GlobalVar.setFileListInput(new FileIO().listFilesDirectory(GlobalVar.getDirectoryPath()));
        String M = "";
        for (int n = 1; n <= 5; n++) {
            GlobalVar.setNGramSize(n);
            for (int i = 1; i <= 2; i++) {
                if (i == 1) M = "Hash";
                if (i == 2) M = "Linear";
                GlobalVar.setMethodParser(i);
                GlobalVar.setFileNameOutput("TEST_" + n + "-Gram" + "_Method_" + M + ".txt");
                new Parser().parseDir();
            }
        }
    }
}
