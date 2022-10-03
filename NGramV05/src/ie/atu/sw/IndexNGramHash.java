/*
 * Programme Name: NGramV05
 * Class Name: IndexNGramHash Class
 * Description:
 * In this class we use the hash function to find the index of each ngram in the table.
 *
 * >> Method openAddressingIndex Code Adaption from:
 * GeeksforGeeks. (2021).
 * Java Program to Implement HashTables with Linear Probing. [online]
 * Available at:
 * https://www.geeksforgeeks.org/java-program-to-implement-hashtables-with-linear-probing/
 * [Accessed 13 Jul. 2022].
 */
package ie.atu.sw;

public class IndexNGramHash {
    int maxTableSize = (int) Math.pow(26, GlobalVar.getNGramSize());

    public int getIndex(String ngram) {
        int index = getHashIndex(ngram);
        if (index > (BuildTable.getTable().length - 1)) {
            BuildTable.needExpandTable(index);
            System.out.println("[INFO] resizing the table, please wait... New size: " + BuildTable.getTable().length);
        }
        return collisionCheck(index, ngram);
    }
    // we get the positive index for each ngram.
    private int getHashIndex (String ngram){
        return (ngram.hashCode() & 0x7fffffff) % maxTableSize;
    }

    // Checks for collisions and calls the openAddressingIndex method if necessary.
    private int collisionCheck(int index, String ngram) {
        if (BuildTable.getTable()[index][1] != null) {
            int hash1 = (BuildTable.getTable()[index][0]).hashCode();
            int hash2 = ngram.hashCode();
            if (hash1 != hash2) {
                index = openAddressingIndex(index, ngram);
            }
        }
        return index;
    }

    // with Linear Probing method, look for next empty location or if current ngram is in table
    private int openAddressingIndex(int index, String ngram) {
        // Linear Probing
        boolean probingIndex = true;
        int hashProbing = ngram.hashCode();
        int i = 1;
        int indexTemp = (hashProbing + i) % maxTableSize;
        do {
            if (BuildTable.getTable()[indexTemp][0] == null) {
                index = indexTemp;
                probingIndex = false;
            }
            if (BuildTable.getTable()[indexTemp][0] != null &&
                    BuildTable.getTable()[indexTemp][0].equals(ngram)) {
                index = indexTemp;
                probingIndex = false;
            }
            indexTemp = (hashProbing + i) % maxTableSize;
            i++;
        } while (probingIndex);
        return index;
    }
}
