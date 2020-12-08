package packagefiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    public static String[] nameList = new String[0];
    public static int[] votesList = new int[0];
    public static Map<String,Integer> ageMap = new HashMap<>();
    public static Map<String,Integer> genderMap = new HashMap<>();
    public static Map<String,Integer> originMap = new HashMap<>();
    public static Map<String,Integer> courseMap = new HashMap<>();
    public static Map<String,Integer> yearMap = new HashMap<>();

    public static int numberOfCandidates = 0;


    public static void addData(String name, String age, String gender, String origin, String course, String year) {
        add2Array(nameList,name);
        // space for voteList
    }

    /**
     * Adds one item to the String[]
     * @param src Source/original array[]
     * @param newValue the item to be added
     */
    public static void add2ArrayString(String[] src, String newValue) {
        if (numberOfCandidates >= src.length) {
            String[] temp = new String[src.length * 2 + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(src, 0,temp, 0, src.length);
            src = temp;
        }
        src[numberOfCandidates++] = newValue;
    }
    /**
     * Adds one item to the int[]. This one is different the number of candidates
     * does not increase as it was increased in the add2ArrayString
     * @param src Source/original array[]
     * @param newValue the item to be added
     */
    public static void add2ArrayInt(int[] src, int newValue) {
        if (numberOfCandidates >= src.length) {
            int[] temp = new int[src.length * 2 + 1]; // +1 to avoid multiplication with size 0
            System.arraycopy(src, 0,temp, 0, src.length);
            src = temp;
        }
        src[numberOfCandidates] = newValue;
    }

    /**
     *
     * @param name name of the candidate taken from the inputField
     * @return boolean
     */
    public  static boolean  findCandidate(String name) {
        for (String s : nameList) {
            if (s.equals(name)) return true;
        }
        return false;
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
