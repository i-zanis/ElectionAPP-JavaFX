package packagefiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static packagefiles.Candidate.getNumberOfCandidates;
import static packagefiles.Candidate.incrementNumberOfCandidates;

/**
 * Basic Election Application with Graphical User Interface. It allows the user to input candidate names in a text field
 * and register them by pressing the Vote button. If the name exists then it increments the vote count by 1.
 * The system collects user data that can be displayed to find more information about the users who voted
 * (eg. year/course). By ticking the checkbox you can exclusively analyze and isolate the groups that voted the
 * candidate with most votes. Clicking on the pie chart also displays the percentage of the data.
 *
 * Made in Java 14.0.2.
 * If the Program does not work please download "javafx-sdk-11.0" and add all the jar-files in
 * Javafx-sdk-11.0.2\lib\ to the global library.
 *
 * VM options --module-path %java path% --add-modules javafx.controls,javafx.fxml
 * If there is an error with the root file FXML please place the above VM options as well.
 * Screenshot provided in MEDIA Folder.
 */
// To note is that there is no direct manipulation of the tally's number. the votes can only increment by 1.
public class Main extends Application {

    // place the paths into Strings for cleaner & error free code
    public static final String ELECTIONPAGE = "FXML/election_page.fxml";
    public static final String UWLLOGO      = "packagefiles/MEDIA/uwl_logo.png";
    public static final String PIECHARTICON = "packagefiles/MEDIA/piechart_icon.png";
    public static final String CSS          = "packagefiles/CSS/style.css";
    public static Candidate[] candidateList = new Candidate[0];
    public static int[] voteList            = new int[0];
    public static final String EMPTYSTRING  = "";

    /**
     * Adds a new candidate object in candidateList or increments existing candidate vote count.
     * Adds or updates the statistical data of the users who voted and divided them into groups of Age, Gender, Origin,
     * Course and Year. The data is taken from error free radio buttons with a default choice.
     *
     * @param name   the name of the candidate
     * @param age    taken by radio button choice
     * @param gender taken by radio button choice
     * @param origin taken by radio button choice
     * @param course taken by radio button choice
     * @param year   taken by radio button choice
     */
    public static void addData(String name, String age, String gender, String origin, String course, String year) {
        // saves the index of the candidate if found
        int indexOfName = findCandidate(name);
        //  if the candidate exists (returns -1 when he doesn't) increments the vote tally and updates the stats
        if (indexOfName >= 0) {
            voteList[indexOfName]++; // to fulfil the assignments requirements I made two Arrays
            candidateList[indexOfName].updateStats(age, gender, origin, course, year);
            // if the candidate doesn't exist then it adds the new candidate, increments the vote and updates stats
        } else {
            addCandidate2Array(new Candidate(name, age, gender, origin, course, year));
            IncrementVoteListSize();
        }

    }

    /**
     * Adds a new candidate to candidateList and increments the size if needed by (length * 2 + 1)
     *
     * @param candidate the new element to be added
     */
    public static void addCandidate2Array(Candidate candidate) {
        if (getNumberOfCandidates() >= candidateList.length) {
            Candidate[] temp = new Candidate[candidateList.length + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(candidateList, 0, temp, 0, candidateList.length);
            candidateList = temp;
        }
        candidateList[getNumberOfCandidates()] = candidate; // finds the next empty index
    }

    /**
     * Increments the size of voteList by (length * 2 + 1) to match the size of the candidateList
     */
    public static void IncrementVoteListSize() {
        if (getNumberOfCandidates() >= voteList.length) {
            int[] temp = new int[voteList.length + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(voteList, 0, temp, 0, voteList.length);
            voteList = temp;
        }
        // finds the next empty index and increment numberOfCandidates for next addition
        voteList[incrementNumberOfCandidates()] = 1;
    }


    /**
     * Finds if the candidate exists in the Array returns -1 if not matched
     *
     * @param candidate name of the candidate taken from the inputField
     * @return int
     */
    public static int findCandidate(String candidate) {
        for (int i = 0; i < getNumberOfCandidates(); i++) {
            if (candidateList[i].getName().equals(candidate)) return i;
        }
        return -1;
    }

    /**
     * Sorts candidateList & voteList[] based on voteList[] highest integer in descending order
     */
    public static void sort() {
        int N = getNumberOfCandidates();
        for (int i = 0; i < N; i++) {
            int max = i;
            for (int j = i + 1; j < N; j++)  {
                if (voteList[j] > voteList[max]) max = j;
            }
            exchange(voteList, i, max);
            exchange(candidateList,i,max);
            }
        // formats the string and attempts to approximately place a CANDIDATES label in the middle if the name is < 9 chars
        System.out.printf("\n%16s\n","[CANDIDATES]");
        // creates a line separator with hyphens
        System.out.print(String.format("%10s",EMPTYSTRING).replace(EMPTYSTRING,"\u2013"));
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.printf("%-9s %3s vote(s)\n",candidateList[i].getName(), voteList[i]);
        }
    }

    /**
     * Exchange variables method for the Int[] arrays
     * @param array Int[] array
     * @param i index
     * @param j index
     */
    static void exchange(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * Overloaded Exchange method for Candidate[] arrays
     * @param array Candidate[] array
     * @param i index
     * @param j index
     */
     static void exchange(Candidate[] array, int i, int j) {
        Candidate t = array[i];
        array[i] = array[j];
        array[j] = t;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // sets the initial FXML file to be loaded
        Parent root = FXMLLoader.load(getClass().getResource(ELECTIONPAGE));
        // Create a scene and set the initial(root) FXML
        Scene scene = new Scene(root);
        // Sets the title of the window
        primaryStage.setTitle("UWL Student Election");
        // Clear any previous CSS to avoid inconsistencies
        scene.getStylesheets().clear();
        // Add CSS from separate Style Sheet
        scene.getStylesheets().add(CSS);
        // Sets the icon of the window in the Taskbar and top window bar
        primaryStage.getIcons().add(new Image(UWLLOGO));
        // Sets the Scene to the Stage
        primaryStage.setScene(scene);
        // Shows the Stage
        primaryStage.show();
        // makes the window not resizable
        primaryStage.setResizable(false);
    }
}
