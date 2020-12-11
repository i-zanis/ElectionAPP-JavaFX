package packagefiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static packagefiles.Candidate.getNumberOfCandidates;
import static packagefiles.Candidate.incrementNumberOfCandidates;

// To note is that there is no direct manipulation of the tally's number. the votes can only increment by 1.
public class Main extends Application {

    // place the paths into Strings for cleaner & error free code
    public static final String ELECTIONPAGE = "FXML/election_page.fxml";
    public static final String UWLLOGO = "packagefiles/MEDIA/uwl_logo.png";
    public static final String PIECHARTICON = "packagefiles/MEDIA/piechart_icon.png";
    public static final String CSS = "packagefiles/CSS/style.css";
    public static Candidate[] candidateList = new Candidate[0];
    public static int[] voteList = new int[0];

    /**
     * Adds a new candidate object in candidateList or increments existing candidate vote count.
     * Adds or updates the statistical data of the users who voted and divided them into groups of Age, Gender, Origin,
     * Course and Year. The data is taken from error free radio buttons with a default choice.
     *
     * @param name   the name of the candidate
     * @param age    radio button choice
     * @param gender radio button choice
     * @param origin radio button choice
     * @param course radio button choice
     * @param year   radio button choice
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
            Candidate[] temp = new Candidate[candidateList.length * 2 + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(candidateList, 0, temp, 0, candidateList.length);
            candidateList = temp;
        }
        candidateList[getNumberOfCandidates()] = candidate; // will find the next empty index
    }

    /**
     * Increments the size of voteList by (length * 2 + 1) to match the size of the candidateList
     */
    public static void IncrementVoteListSize() {
        if (getNumberOfCandidates() >= voteList.length) {
            int[] temp = new int[voteList.length * 2 + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(voteList, 0, temp, 0, voteList.length);
            voteList = temp;
        }
        // will find the next empty index and increment it for next addition
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
