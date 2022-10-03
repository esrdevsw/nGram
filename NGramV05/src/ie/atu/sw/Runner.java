/*
 * Programme Name: NGramV05
 * Class Name: Runner Class
 * Description:
 * The application is run using the “Runner” class.
 * It accomplishes this by starting the "Menu" Class,
 * a class that is used to call all the classes required to run and
 * to configure the text parsing method and get the n-Grams.
 *
 */

package ie.atu.sw; //Package Name.

public class Runner {

    //Main Method - To Run Application.
    public static void main(String[] args) throws Exception {

        Menu m = new Menu(); //Create a New Instance of a Menu Class.
        m.start();

    }
}