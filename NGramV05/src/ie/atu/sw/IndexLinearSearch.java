/*
 * Programme Name: NGramV05
 * Class Name: IndexLinearSearch Class
 * Description:
 * Linear-search function to find the index of a ngram
 */
package ie.atu.sw;

public class IndexLinearSearch {
    public int findIndex(Object[][] table, String ngram) {
        // if array is Null
        if (table == null) {
            return -1;
        }
        // find length of table
        int len = table.length;
        int i = 0;
        // traverse in the array
        while (i < len) {
            // if the i-th element is ngram then return the index
            if (ngram.equals(table[i][0])) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }
}
