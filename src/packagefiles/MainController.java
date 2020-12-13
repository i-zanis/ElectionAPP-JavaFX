package packagefiles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static packagefiles.Candidate.getNumberOfCandidates;
import static packagefiles.Main.*;

public class MainController implements Initializable {
    public RadioButton age1820;
    public RadioButton age2124;
    public RadioButton age2529;
    public RadioButton age3039;
    public RadioButton age40;
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public RadioButton genderOther;
    public RadioButton originUK;
    public RadioButton originEEA;
    public RadioButton originInternational;
    public RadioButton courseCS;
    public RadioButton courseCG;
    public RadioButton courseWD;
    public RadioButton courseIT;
    public RadioButton courseITMB;
    public RadioButton year0;
    public RadioButton year1;
    public RadioButton year2;
    public RadioButton year3;
    public RadioButton year4;
    public TextField inputField;
    public Button voteButton;
    public Button resultsButton;
    public CheckBox winnerStats;
    public Button ageGroupButton;
    public Button genderGroupButton;
    public Button originGroupButton;
    public Button courseGroupButton;
    public Button yearGroupButton;
    ToggleGroup ageGroup = new ToggleGroup();
    ToggleGroup genderGroup = new ToggleGroup();
    ToggleGroup originGroup = new ToggleGroup();
    ToggleGroup courseGroup = new ToggleGroup();
    ToggleGroup yearGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        age1820.setToggleGroup(ageGroup);
        age2124.setToggleGroup(ageGroup);
        age2529.setToggleGroup(ageGroup);
        age3039.setToggleGroup(ageGroup);
        age40.setToggleGroup(ageGroup);

        genderMale.setToggleGroup(genderGroup);
        genderFemale.setToggleGroup(genderGroup);
        genderOther.setToggleGroup(genderGroup);

        originUK.setToggleGroup(originGroup);
        originEEA.setToggleGroup(originGroup);
        originInternational.setToggleGroup(originGroup);

        courseCS.setToggleGroup(courseGroup);
        courseCG.setToggleGroup(courseGroup);
        courseWD.setToggleGroup(courseGroup);
        courseIT.setToggleGroup(courseGroup);
        courseITMB.setToggleGroup(courseGroup);

        year0.setToggleGroup(yearGroup);
        year1.setToggleGroup(yearGroup);
        year2.setToggleGroup(yearGroup);
        year3.setToggleGroup(yearGroup);
        year4.setToggleGroup(yearGroup);
    }

    /**
     * Adds a new Candidate or adds a vote for that candidate if that name exists
     * The method takes the names of the Radio buttons that have been selected and saves them into a String
     * to update the values in the Candidate object data fields.
     */
    public void vote(ActionEvent event) throws Exception {
        // gets the input from the textField
        String name = inputField.getText();
        // capitalizes the first letter
        if (!name.equals(EMPTYSTRING)) name = name.substring(0, 1).toUpperCase() + name.substring(1);
        // checks if inputField is empty or has the error message. If empty posts the error message, requests focus
        // and selects all text to be erased on next type
        if (name.equals(EMPTYSTRING) || name.equals("Please Enter a name")) {
            inputField.setText("Please Enter a name");
            inputField.requestFocus();
            inputField.selectAll();
        }
        else {
            String age = ((RadioButton) ageGroup.getSelectedToggle()).getId();
            String gender = ((RadioButton) genderGroup.getSelectedToggle()).getId();
            String origin = ((RadioButton) originGroup.getSelectedToggle()).getId();
            String course = ((RadioButton) courseGroup.getSelectedToggle()).getId();
            String year = ((RadioButton) yearGroup.getSelectedToggle()).getId();
            // need to fix if all properties are selected
            System.out.printf("%s has gained one vote.\n", name);
            addData(name, age, gender, origin, course, year);
            // turns on resultsButton visibility when a second candidate is registered
            if (getNumberOfCandidates() > 1) resultsButton.setVisible(true);
        }
        // hides all buttons when voteButton is pressed, they are revealed when resultsButton is pressed
        hideButtons();
    }

    /**
     * Action event for checkResults button. Calculates the votes saved in the candidateList/voteList and displays
     * a pie chart with the candidates.
     *
     */
    public void checkResults(ActionEvent event) throws Exception {
        // sorts the candidates based on votes from voteList
        sort();
        try {
            // creates a scene and adds it to a group
            Scene resultsPage = new Scene(new Group());
            // creates a new window to display the stats
            Stage window = new Stage();
            // makes an Observable list, similar to ArrayList but for FX nodes
            ObservableList<PieChart.Data> pieChartDataList =
                    FXCollections.observableArrayList();
            for (int i = 0; i < getNumberOfCandidates(); i++) {
                // adds the candidates name and calculates the percentage (candidates votes)/total votes
                // 1.0 to convert to floating point
                pieChartDataList.add(new PieChart.Data(candidateList[i].getName(), voteList[i] / (voteList.length - 1.0)));
            }
            // create a Pie chart to display the votes
            final PieChart chart = new PieChart(pieChartDataList);
            chart.setTitle("Votes");

            // creates a label to display the percentage
            final Label PERCENTAGE = new Label(EMPTYSTRING);
            PERCENTAGE.setStyle("-fx-font: 35 arial;");

            // create a handler to display the percentage on click
            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            double total = 0;
                            for (PieChart.Data d : chart.getData()) {
                                total += d.getPieValue();
                            }
                            // set coordinates for label
                            PERCENTAGE.setTranslateX(e.getSceneX());
                            PERCENTAGE.setTranslateY(e.getSceneY());
                            // formats text and calculates based on (candidates votes)/total votes
                            String text = String.format("%.1f%%", data.getPieValue() / total * 100);
                            PERCENTAGE.setText(text);
                        }
                );
            }
            // shows the hidden buttons
            showButtons();
            // adds the chart to the Group
            ((Group) resultsPage.getRoot()).getChildren().add(chart);
            ((Group) resultsPage.getRoot()).getChildren().add(PERCENTAGE);
            // sets the Scene to the window
            window.setScene(resultsPage);
            // applies the preferred icon
            window.getIcons().add(new Image(PIECHARTICON));
            // preferred title atop of the window
            window.setTitle("Results");
            // shows the window
            window.show();
            // makes the window not resizable
            window.setResizable(false);
        } catch (Exception e) {
            // pinpoints the the location of the error occurrence
            System.out.println("Error occurred while opening the final results page.");
            e.printStackTrace();
        }
    }
    /**
     * Action event for ageGroupButton. Displays age groups in a new window with a pie chart
     */
    public void checkAgeGroup(ActionEvent event) throws Exception {
        try {
            // creates a scene and adds it to a group
            Scene ageGroupPage = new Scene(new Group());
            // creates a new window to display the stats
            Stage window = new Stage();

            // assign an initial value to increment in the loop
            int a1820 = 0;
            int a2124 = 0;
            int a2529 = 0;
            int a3039 = 0;
            int a40   = 0;

            int iterations = getNumberOfCandidates();
            if (winnerStats.isSelected()) iterations = 1;
            for (int i = 0; i < iterations; i++) {
                // adds the candidates name and calculates the percentage (candidates votes)/total votes
                // multiplication * 1 to turn into floating to remove the error
                a1820 += candidateList[i].getAge1820();
                a2124 += candidateList[i].getAge2124();
                a2529 += candidateList[i].getAge2529();
                a3039 += candidateList[i].getAge3039();
                a40   += candidateList[i].getAge40();

            }
            // makes an Observable list, similar to ArrayList but for FX nodes
            ObservableList<PieChart.Data> pieChartDataList =
                    FXCollections.observableArrayList();
            // if statement to prevent display of 0% data when 0 candidates
            // 1.0 to convert to floating point
            if (a1820 > 0) pieChartDataList.add(new PieChart.Data("18 - 20", a1820 / (voteList.length - 1.0)));
            if (a2124 > 0) pieChartDataList.add(new PieChart.Data("21 - 24", a2124 / (voteList.length - 1.0)));
            if (a2529 > 0) pieChartDataList.add(new PieChart.Data("25 - 29", a2529 / (voteList.length - 1.0)));
            if (a3039 > 0) pieChartDataList.add(new PieChart.Data("30 - 39", a3039 / (voteList.length - 1.0)));
            if (a40 >   0) pieChartDataList.add(new PieChart.Data("40+", a40 / (voteList.length - 1.0)));

            // create a Pie chart to display the age group that voted
            final PieChart chart = new PieChart(pieChartDataList);
            chart.setTitle("Age Groups");

            // creates a label to display the percentage
            final Label PERCENTAGE = new Label(EMPTYSTRING);
            PERCENTAGE.setStyle("-fx-font: 35 arial;");

            // create a handler to display the percentage on click
            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            double total = 0;
                            for (PieChart.Data d : chart.getData()) {
                                total += d.getPieValue();
                            }
                            // set coordinates for label
                            PERCENTAGE.setTranslateX(e.getSceneX());
                            PERCENTAGE.setTranslateY(e.getSceneY());
                            // formats text and calculates based on (candidates votes)/total votes
                            String text = String.format("%.1f%%", data.getPieValue() / total * 100);
                            PERCENTAGE.setText(text);
                        }
                );
            }

            // adds the chart to the Group
            ((Group) ageGroupPage.getRoot()).getChildren().add(chart);
            ((Group) ageGroupPage.getRoot()).getChildren().add(PERCENTAGE);
            // sets the Scene to the window
            window.setScene(ageGroupPage);
            // applies the preferred icon
            window.getIcons().add(new Image(PIECHARTICON));
            // preferred title atop of the window
            window.setTitle("Student statistics");
            // shows the window
            window.show();
            // makes the window not resizable
            window.setResizable(false);
        } catch (Exception e) {
            // pinpoints the the location of the error occurrence
            System.out.println("Error occurred while opening the Age Groups page.");
            e.printStackTrace();
        }
    }
    /**
     * Action event for genderGroupButton. Displays gender groups in a new window with a pie chart
     */
    public void checkGenderGroup(ActionEvent event) throws Exception {
        try {
            // creates a scene and adds it to a group
            Scene genderGroupPage = new Scene(new Group());
            // creates a new window to display the stats
            Stage window = new Stage();

            // assign an initial value to increment in the loop
            int genderMale = 0;
            int genderFemale = 0;
            int genderOther = 0;


            int iterations = getNumberOfCandidates();
            if (winnerStats.isSelected()) iterations = 1;
            for (int i = 0; i < iterations; i++) {
                // adds the candidates name and calculates the percentage (candidates votes)/total votes
                // multiplication * 1 to turn into floating to remove the error
                genderMale += candidateList[i].getGenderMale();
                genderFemale += candidateList[i].getGenderFemale();
                genderOther += candidateList[i].getGenderOther();
            }
            // makes an Observable list, similar to ArrayList but for FX nodes
            ObservableList<PieChart.Data> pieChartDataList =
                    FXCollections.observableArrayList();
            // if statement to prevent display of 0% data when 0 candidates
            // 1.0 to convert to floating point
            if (genderMale > 0) pieChartDataList.add(new PieChart.Data("Male", genderMale / (voteList.length - 1.0)));
            if (genderFemale > 0)
                pieChartDataList.add(new PieChart.Data("Female", genderFemale / (voteList.length - 1.0)));
            if (genderOther > 0)
                pieChartDataList.add(new PieChart.Data("Other", genderOther / (voteList.length - 1.0)));


            // create a Pie chart to display the gender group that voted
            final PieChart chart = new PieChart(pieChartDataList);
            chart.setTitle("Gender Groups");

            // creates a label to display the percentage
            final Label PERCENTAGE = new Label(EMPTYSTRING);
            PERCENTAGE.setStyle("-fx-font: 35 arial;");

            // create a handler to display the percentage on click
            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            double total = 0;
                            for (PieChart.Data d : chart.getData()) {
                                total += d.getPieValue();
                            }
                            // set coordinates for label
                            PERCENTAGE.setTranslateX(e.getSceneX());
                            PERCENTAGE.setTranslateY(e.getSceneY());
                            // formats text and calculates based on (candidates votes)/total votes
                            String text = String.format("%.1f%%", data.getPieValue() / total * 100);
                            PERCENTAGE.setText(text);
                        }
                );
            }

            // adds the chart to the Group
            ((Group) genderGroupPage.getRoot()).getChildren().add(chart);
            ((Group) genderGroupPage.getRoot()).getChildren().add(PERCENTAGE);
            // sets the Scene to the window
            window.setScene(genderGroupPage);
            // applies the preferred icon
            window.getIcons().add(new Image(PIECHARTICON));
            // preferred title atop of the window
            window.setTitle("Student statistics");
            // shows the window
            window.show();
            // makes the window not resizable
            window.setResizable(false);
        } catch (Exception e) {
            // pinpoints the the location of the error occurrence
            System.out.println("Error occurred while opening the Gender Groups page.");
            e.printStackTrace();
        }
    }
    /**
     * Action event for originGroupButton. Displays origin groups in a new window with a pie chart
     */
    public void checkOriginGroup(ActionEvent event) throws Exception {
        try {
            // creates a scene and adds it to a group
            Scene originGroupPage = new Scene(new Group());
            // creates a new window to display the stats
            Stage window = new Stage();

            // assign an initial value to increment in the loop
            int originUK = 0;
            int originEEA = 0;
            int originInternational = 0;

            int iterations = getNumberOfCandidates();
            if (winnerStats.isSelected()) iterations = 1;
            for (int i = 0; i < iterations; i++) {
                // adds the candidates name and calculates the percentage (candidates votes)/total votes
                // multiplication * 1 to turn into floating to remove the error
                originUK += candidateList[i].getOriginUK();
                originEEA += candidateList[i].getOriginEEA();
                originInternational += candidateList[i].getOriginInternational();
            }
            // makes an Observable list, similar to ArrayList but for FX nodes
            ObservableList<PieChart.Data> pieChartDataList =
                    FXCollections.observableArrayList();
            // if statement to prevent display of 0% data when 0 candidates
            // 1.0 to convert to floating point
            if (originUK > 0)
                pieChartDataList.add(new PieChart.Data("UK students", originUK / (voteList.length - 1.0)));
            if (originEEA > 0)
                pieChartDataList.add(new PieChart.Data("EEA students", originEEA / (voteList.length - 1.0)));
            if (originInternational > 0)
                pieChartDataList.add(new PieChart.Data("International students", originInternational / (voteList.length - 1.0)));


            // create a Pie chart to display the origin of group that voted
            final PieChart chart = new PieChart(pieChartDataList);
            chart.setTitle("Origin Groups");

            // creates a label to display the percentage
            final Label PERCENTAGE = new Label(EMPTYSTRING);
            PERCENTAGE.setStyle("-fx-font: 35 arial;");

            // create a handler to display the percentage on click
            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            double total = 0;
                            for (PieChart.Data d : chart.getData()) {
                                total += d.getPieValue();
                            }
                            // set coordinates for label
                            PERCENTAGE.setTranslateX(e.getSceneX());
                            PERCENTAGE.setTranslateY(e.getSceneY());
                            // formats text and calculates based on (candidates votes)/total votes
                            String text = String.format("%.1f%%", data.getPieValue() / total * 100);
                            PERCENTAGE.setText(text);
                        }
                );
            }

            // adds the chart to the Group
            ((Group) originGroupPage.getRoot()).getChildren().add(chart);
            ((Group) originGroupPage.getRoot()).getChildren().add(PERCENTAGE);
            // sets the Scene to the window
            window.setScene(originGroupPage);
            // applies the preferred icon
            window.getIcons().add(new Image(PIECHARTICON));
            // preferred title atop of the window
            window.setTitle("Student statistics");
            // shows the window
            window.show();
            // makes the window not resizable
            window.setResizable(false);
        } catch (Exception e) {
            // pinpoints the the location of the error occurrence
            System.out.println("Error occurred while opening the Origin Groups page.");
            e.printStackTrace();
        }
    }
    /**
     * Action event for courseGroupButton. Displays course groups in a new window with a pie chart
     */
    public void checkCourseGroup(ActionEvent event) throws Exception {
        try {
            // creates a scene and adds it to a group
            Scene courseGroupPage = new Scene(new Group());
            // creates a new window to display the stats
            Stage window = new Stage();

            // assign an initial value to increment in the loop
            int courseCS = 0;
            int courseCG = 0;
            int courseWD = 0;
            int courseIT = 0;
            int courseITMB = 0;

            int iterations = getNumberOfCandidates();
            if (winnerStats.isSelected()) iterations = 1;
            for (int i = 0; i < iterations; i++) {
                // adds the candidates name and calculates the percentage (candidates votes)/total votes
                // multiplication * 1 to turn into floating to remove the error
                courseCS += candidateList[i].getCourseCS();
                courseCG += candidateList[i].getCourseCG();
                courseWD += candidateList[i].getCourseWD();
                courseIT += candidateList[i].getCourseIT();
                courseITMB += candidateList[i].getCourseITMB();

            }
            // makes an Observable list, similar to ArrayList but for FX nodes
            ObservableList<PieChart.Data> pieChartDataList =
                    FXCollections.observableArrayList();
            // if statement to prevent display of 0% data when 0 candidates
            // 1.0 to convert to floating point
            if (courseCS > 0)
                pieChartDataList.add(new PieChart.Data("Computer Science", courseCS / (voteList.length - 1.0)));
            if (courseCG > 0)
                pieChartDataList.add(new PieChart.Data("Computer Games", courseCG / (voteList.length - 1.0)));
            if (courseWD > 0)
                pieChartDataList.add(new PieChart.Data("Web Development", courseWD / (voteList.length - 1.0)));
            if (courseIT > 0) pieChartDataList.add(new PieChart.Data("IT", courseIT / (voteList.length - 1.0)));
            if (courseITMB > 0) pieChartDataList.add(new PieChart.Data("ITMB", courseITMB / (voteList.length - 1.0)));


            // create a Pie chart to display the gender group that voted
            final PieChart chart = new PieChart(pieChartDataList);
            chart.setTitle("Course groups");

            // creates a label to display the percentage
            final Label PERCENTAGE = new Label(EMPTYSTRING);
            PERCENTAGE.setStyle("-fx-font: 35 arial;");

            // create a handler to display the percentage on click
            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            double total = 0;
                            for (PieChart.Data d : chart.getData()) {
                                total += d.getPieValue();
                            }
                            // set coordinates for label
                            PERCENTAGE.setTranslateX(e.getSceneX());
                            PERCENTAGE.setTranslateY(e.getSceneY());
                            // formats text and calculates based on (candidates votes)/total votes
                            String text = String.format("%.1f%%", data.getPieValue() / total * 100);
                            PERCENTAGE.setText(text);
                        }
                );
            }

            // adds the chart to the Group
            ((Group) courseGroupPage.getRoot()).getChildren().add(chart);
            ((Group) courseGroupPage.getRoot()).getChildren().add(PERCENTAGE);
            // sets the Scene to the window
            window.setScene(courseGroupPage);
            // applies the preferred icon
            window.getIcons().add(new Image(PIECHARTICON));
            // preferred title atop of the window
            window.setTitle("Student statistics");
            // shows the window
            window.show();
            // makes the window not resizable
            window.setResizable(false);
        } catch (Exception e) {
            // pinpoints the the location of the error occurrence
            System.out.println("Error occurred while opening the Course Groups page.");
            e.printStackTrace();
        }
    }
    /**
     * Action event for yearGroupButton. Displays year groups in a new window with a pie chart
     */
    public void checkYearGroup(ActionEvent event) throws Exception {
        try {
            // creates a scene and adds it to a group
            Scene yearGroupPage = new Scene(new Group());
            // creates a new window to display the stats
            Stage window = new Stage();

            // assign an initial value to increment in the loop
            int year0 = 0;
            int year1 = 0;
            int year2 = 0;
            int year3 = 0;
            int year4 = 0;

            int iterations = getNumberOfCandidates();
            if (winnerStats.isSelected()) iterations = 1;
            for (int i = 0; i < iterations; i++) {
                // adds the candidates name and calculates the percentage (candidates votes)/total votes
                // multiplication * 1 to turn into floating to remove the error
                year0 += candidateList[i].getYear0();
                year1 += candidateList[i].getYear1();
                year2 += candidateList[i].getYear2();
                year3 += candidateList[i].getYear3();
                year4 += candidateList[i].getYear4();
            }
            // makes an Observable list, similar to ArrayList but for FX nodes
            ObservableList<PieChart.Data> pieChartDataList =
                    FXCollections.observableArrayList();
            // if statement to prevent display of 0% data when 0 candidates
            // 1.0 to convert to floating point
            if (year0 > 0) pieChartDataList.add(new PieChart.Data("Foundation Year", year0 / (voteList.length - 1.0)));
            if (year1 > 0) pieChartDataList.add(new PieChart.Data("Year 1", year1 / (voteList.length - 1.0)));
            if (year2 > 0) pieChartDataList.add(new PieChart.Data("Year 2", year2 / (voteList.length - 1.0)));
            if (year3 > 0) pieChartDataList.add(new PieChart.Data("Year 3", year3 / (voteList.length - 1.0)));
            if (year4 > 0) pieChartDataList.add(new PieChart.Data("Year 4+", year4 / (voteList.length - 1.0)));


            // create a Pie chart to display the gender group that voted
            final PieChart chart = new PieChart(pieChartDataList);
            chart.setTitle("Year groups");

            // creates a label to display the percentage
            final Label PERCENTAGE = new Label(EMPTYSTRING);
            PERCENTAGE.setStyle("-fx-font: 35 arial;");

            // create a handler to display the percentage on click
            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            double total = 0;
                            for (PieChart.Data d : chart.getData()) {
                                total += d.getPieValue();
                            }
                            // set coordinates for label
                            PERCENTAGE.setTranslateX(e.getSceneX());
                            PERCENTAGE.setTranslateY(e.getSceneY());
                            // formats text and calculates based on (candidates votes)/total votes
                            String text = String.format("%.1f%%", data.getPieValue() / total * 100);
                            PERCENTAGE.setText(text);
                        }
                );
            }

            // adds the chart to the Group
            ((Group) yearGroupPage.getRoot()).getChildren().add(chart);
            ((Group) yearGroupPage.getRoot()).getChildren().add(PERCENTAGE);
            // sets the Scene to the window
            window.setScene(yearGroupPage);
            // applies the preferred icon
            window.getIcons().add(new Image(PIECHARTICON));
            // preferred title atop of the window
            window.setTitle("Student statistics");
            // shows the window
            window.show();
            // makes the window not resizable
            window.setResizable(false);
        } catch (Exception e) {
            // pinpoints the the location of the error occurrence
            System.out.println("Error occurred while opening the Year Groups page.");
            e.printStackTrace();
        }
    }

    /**
     * Turns on the visibility of winnerStats, agrGroupButton, genderGroupButton, originGroupButton courseGroupButton,
     * yearGroupButton after two candidates have been registered
     */
    public void showButtons() {
        winnerStats.setVisible(true);
        ageGroupButton.setVisible(true);
        genderGroupButton.setVisible(true);
        originGroupButton.setVisible(true);
        courseGroupButton.setVisible(true);
        yearGroupButton.setVisible(true);
    }

    /**
     * Turns off the visibility of winnerStats, agrGroupButton, genderGroupButton, originGroupButton courseGroupButton,
     * yearGroupButton when the vote button because the user has to press checkResults first
     */
    public void hideButtons() {
        winnerStats.setVisible(false);
        ageGroupButton.setVisible(false);
        genderGroupButton.setVisible(false);
        originGroupButton.setVisible(false);
        courseGroupButton.setVisible(false);
        yearGroupButton.setVisible(false);
    }
}



