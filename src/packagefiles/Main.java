package packagefiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static packagefiles.Candidate.*;
import static packagefiles.Candidate.getNumberOfCandidates;

import java.util.HashMap;
import java.util.Map;

// main things to note is that there is no direct manipulation of the tally's number. the votes can only increment by 1.
public class Main extends Application {

    public static final String ELECTIONPAGE = "FXML/election_page.fxml";
    public static final String UWLLOGO = "packagefiles/MEDIA/uwl_logo.png";
    public static final String PIECHARTICON = "packagefiles/MEDIA/piechart_icon.png";
    public static Candidate[] candidateList = new Candidate[0];
    public static int[] voteList = new int[0];
    
    
    public static void addData(String name, String age, String gender, String origin, String course, String year) {
        int indexOfName = findCandidate(name);
        if (indexOfName >= 0) {
            voteList[indexOfName]++; // to fulfil the assignments requirements
            candidateList[indexOfName].updateStats(age,gender,origin,course,year);
        }
        else {
            add2ArrayCandidate(new Candidate(name, age, gender, origin, course, year));
            add2ArrayInt();
        }

    }

    /**
     * Adds one item to the String[]
     * @param candidate the item to be added
     */
    public static void add2ArrayCandidate(Candidate candidate) {
        if (getNumberOfCandidates() >= candidateList.length) {
            Candidate[] temp = new Candidate[candidateList.length * 2 + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(candidateList, 0,temp, 0, candidateList.length);
            candidateList = temp;
        }
        candidateList[getNumberOfCandidates()] = candidate; // will find the next empty index
    }
    /**
     * Adds one item to the int[]. This one is different the number of candidates
     * does not increase as it was increased in the add2ArrayCandidate
     * @param src Source/original array[]
     * @param newValue the item to be added
     */
    public static void add2ArrayInt() {
        if (getNumberOfCandidates() >= voteList.length) {
            int[] temp = new int[voteList.length * 2 + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(voteList, 0,temp, 0, voteList.length);
            voteList = temp;
        }
        voteList[incrementNumberOfCandidates()] = 1; // will find the next empty index and increment it for next addition

    }

    /**
     *
     * @param candidate name of the candidate taken from the inputField
     * @return int
     */
    public  static int findCandidate(String candidate) {
       // take care for minus 1
        for (int i = 0; i < getNumberOfCandidates(); i++) {
           if (candidateList[i].getName().equals(candidate)) return i;
       }
       return -1;
    }

    /**
     * Copies the contents of one array[] to the target array[]
     * @param src source array
     * @param dst destination/copy of source array
     * @return boolean
     */
    // i will implement this later
    public static boolean copyArray(int[] src, int[] dst) {
        if (src.length > dst.length) return false;
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
        return true;
    }
    //method to increase the size of the array double
    //method to print results
    //method
    // make graph for winner
    // make graph for people who voted him
    // it has to find if it contains the same name first then add stuff;
    @Override
    public void start(Stage primaryStage) throws Exception{
        // sets the initial FXML file to be loaded
        Parent root = FXMLLoader.load(getClass().getResource(ELECTIONPAGE));
        // Create a scene and set the initial(root) FXML
        Scene logInScene = new Scene(root);
        // Sets the title of the window
        primaryStage.setTitle("UWL Student Election");
        // Sets the icon of the window in the Taskbar and top window bar
        primaryStage.getIcons().add(new Image(UWLLOGO));
        // Sets the Scene to the Stage
        primaryStage.setScene(logInScene);
        // Shows the Stage
        primaryStage.show();
        // makes the window not resizable
        primaryStage.setResizable(false);
    }






    public static void main(String[] args) {
        launch(args);
    }
}
