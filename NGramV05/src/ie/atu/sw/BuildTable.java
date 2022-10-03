/*
 * Programme Name: NGramV05
 * Class Name: BuildTable Class
 * Description:
 * This class handles the table where ngrams are stored.
 * We have a method to add the ngrams to the table,
 * print the table on the screen and expand the table if necessary.
 */

package ie.atu.sw;

import static java.lang.String.valueOf;

public class BuildTable {
    private static final int INITIAL_CAPACITY = 26;
    private static final int COL_CAPACITY = 3;

    // getTable()[index][0] = ngram;
    // getTable()[index][1] = counter;
    // getTable()[index][2] = RelativeFrequencies;
    private static Object[][] table = new Object[INITIAL_CAPACITY][COL_CAPACITY];

    public static Object[][] getTable() {
        return table;
    }

    public static void setTable(Object[][] table) {
        BuildTable.table = table;
    }

    // The relative frequency is the division of the
    // number of times an n-Gram repeats itself by the total amount of ngram
    public static void RelativeFrequencies(Object[][] table) {
        double ngramTotal = 0;
        for (Object[] objects : table) {
            if (objects[0] != null) {
                ngramTotal += (long) objects[1];
            }
        }
        for (int row = 0; row < table.length; row++) {
            if (table[row][0] != null) {
                double i = Double.parseDouble(valueOf(table[row][1]));
                table[row][2] = (i / ngramTotal) * 100;
            }
        }
    }

    // Print the table on the screen
    public static void printTable(Object[][] table) {
        long ngramUnique = 0;
        long ngramTotal = 0;

        RelativeFrequencies(table);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int row = 0; row < table.length; row++) {
            if (table[row][0] != null) {
                System.out.println("| " + row + " >> " + table[row][0] + " = " + table[row][1] + " , " + String.format("%.4f", table[row][2]) + "%");
                ngramTotal += (long) table[row][1];
                ngramUnique++;
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Unique N-Gram = " + ngramUnique);
        System.out.println("Total N-Gram = " + ngramTotal);
    }

    //Adds the ngram to the table, if necessary expand the table,
    // call the expansion method, according to the method in use (Hash or Linear).
    public static void addNGram(String ngram) {
        int index = 0;
        long counter = 1;

        if (GlobalVar.getMethodParser() == 1) {
            index = new IndexNGramHash().getIndex(ngram);
        } else if (GlobalVar.getMethodParser() == 2) {
            index = new IndexLinearSearch().findIndex(getTable(), ngram);
            if (index == -1) {
                index = findNull();
            }
        }

        if (getTable()[index][1] != null) {
            int h1 = (BuildTable.getTable()[index][0]).hashCode();
            int h2 = ngram.hashCode();
            if (h1 == h2) {
                counter += (Long) getTable()[index][1];
            }
        }
        getTable()[index][0] = ngram;
        getTable()[index][1] = counter;
    }

    // Looks for the first null element of the table,
    // if there is no null element, call the method to expand the table.
    private static int findNull() {
        boolean needExpanded = true;
        int index = 0;
        do {
            for (int row = 0; row < getTable().length; row++) {
                if (getTable()[row][0] == null) {
                    index = row;
                    needExpanded = false;
                    break;
                }
            }
            if (needExpanded) {
                needExpandTable(index);
            }
        } while (needExpanded);
        return index;
    }

    // Creates a new temporary object table.
    public static void needExpandTable(int index) {
        if (GlobalVar.getMethodParser() == 1) { // Expand table for hash method
            copyTableData(new Object[index + 10][COL_CAPACITY]);
        } else if (GlobalVar.getMethodParser() == 2) { // Expand table for linear method
            copyTableData(new Object[getTable().length * 2][COL_CAPACITY]);
        }
    }

    // Copy data from the old table to the new one.
    private static void copyTableData(Object[][] temp) { //Copy all from old table to new table
        for (int row = 0; row < getTable().length; row++) {
            System.arraycopy(getTable()[row], 0, temp[row], 0, getTable()[row].length);
        }
        setTable(temp);
    }
}
