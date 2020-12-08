package packagefiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import packagefiles.Candidate.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    public static Candidate nameList = new String[0];
    public static int[] votesList = new int[0];
    public static int numberOfCandidates = 0;

    /*
    public static Map<String,Integer> ageMap = new HashMap<>();
    public static Map<String,Integer> genderMap = new HashMap<>();
    public static Map<String,Integer> originMap = new HashMap<>();
    public static Map<String,Integer> courseMap = new HashMap<>();
    public static Map<String,Integer> yearMap = new HashMap<>();



    public static void incrementValue(Map<String,Integer> map, String name) {
        Integer value = map.get(name);
        if (value == null) {
            map.put(name, 1);
        }
        else {
            map.put(name, value + 1);
        }
    }


     */
    public static void addData(String name, String age, String gender, String origin, String course, String year) {
        int indexOfName = findCandidate(name);
        if (indexOfName >= 0) {
            votesList[indexOfName]++;
            itemList[indexOfName].updateStats(age, gender, origin, course, year);
        }
        else {
            add2ArrayString(name);
            add2ArrayInt();
        }

    }

    /**
     * Adds one item to the String[]
     * @param newValue the item to be added
     */
    public static void add2ArrayString(String newValue) {
        if (numberOfCandidates >= nameList.length) {
            String[] temp = new String[nameList.length * 2 + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(nameList, 0,temp, 0, nameList.length);
            nameList = temp;
        }
        nameList[numberOfCandidates++] = newValue;
    }
    /**
     * Adds one item to the int[]. This one is different the number of candidates
     * does not increase as it was increased in the add2ArrayString
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
     * @param name name of the candidate taken from the inputField
     * @return int
     */
    public  static int findCandidate(String name) {
       for (int i = 0; i < nameList.length; i++) {
           if (nameList[i].equals(name)) return i;
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
