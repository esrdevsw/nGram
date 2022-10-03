# nGram
menu-driven program for text analysis and getting the n-gram


In this menu-driven program, we use a simple command-line user interface where the user must input the directory address (option 1), specify the number of characters for the n-gram (option 2) and the output name file (option 3). In option 4 the user chooses the method to build the n-gram table. Option 4 will only be active after the previous ones are validated by an IF condition (Menu class, buildNGram method). To prevent Exceptions, we implement a try-catch block for each user input.

To build the table, the user can choose between two methods:

•	Linear-search (IndexLinearSearch.java). Linear-search function to find the index of an n-gram. The value is stored in the first null cell if the n-gram does not exist in the table.

•	Hash Table (IndexNGramHash.java). In this class, we use the hash function to find the index of each n-gram in the table.

To solve the problem of collision in the hash table, we have implemented the openAddressingIndex method, where we use the Linear Probing method. The method openAddressingIndex has been adapted from the reference [1].

The table is built in the BuildTable Class. We have a method to add the n-grams to the table, print the table on the screen and expand the table when it is necessary. In the third column, we added the relative frequency calculated by the RelativeFrequencies method. 

With the texts provided on this assignment, it was possible to verify the results of the relative frequencies in 1-Gram agree with the values presented in the reference table on Wikipedia [2].
References

[1] 	GeeksforGeeks, "Java Program to Implement HashTables with Linear Probing," 13 Jul 2022. [Online]. Available: https://www.geeksforgeeks.org/java-program-to-implement-hashtables-with-linear-probing/.
[2] 	Wikipedia, "Letter frequency," Wikipedia, [Online]. Available: https://en.wikipedia.org/wiki/Letter_frequency. [Accessed 20 Jul 2022].


