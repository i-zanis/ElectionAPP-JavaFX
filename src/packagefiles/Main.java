package packagefiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static packagefiles.Candidate.*;
import java.util.HashMap;
import java.util.Map;

// main things to note is that there is no direct manipulation of the tally's number. the votes can only increment by 1.
public class Main extends Application {

    public static Candidate[] candidateList = new Candidate[0];
    public static int[] votesList = new int[0];
    
    
    public static void addData(Candidate candidate, String age, String gender, String origin, String course, String year) {
        int indexOfName = findCandidate(candidate);
        if (indexOfName >= 0) {
            votesList[indexOfName]++; // to fulfil the assignments requirements
            candidate.updateStats(age,gender,origin,course,year);
        }
        else {
            add2ArrayCandidate(candidate);
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
        candidateList[incrementNumberOfCandidates()] = candidate;
    }
    /**
     * Adds one item to the int[]. This one is different the number of candidates
     * does not increase as it was increased in the add2ArrayCandidate
     * @param src Source/original array[]
     * @param newValue the item to be added
     */
    public static void add2ArrayInt() {
        if (numberOfCandidates >= votesList.length) {
            int[] temp = new int[votesList.length * 2 + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(votesList, 0,temp, 0, votesList.length);
            votesList = temp;
        }
        votesList[numberOfCandidates] = 1; // increases the vote tally by one for selected index
    }

    /**
     *
     * @param candidate name of the candidate taken from the inputField
     * @return int
     */
    public  static int findCandidate(Candidate candidate) {
       for (int i = 0; i < candidateList.length; i++) {
           if (candidateList[i].getName().equals(candidate.getName())) return i;
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
        Parent root = FXMLLoader.load(getClass().getResource("FXML/election_page.fxml"));
        primaryStage.setTitle("Student Election");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
