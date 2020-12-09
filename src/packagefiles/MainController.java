package packagefiles;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import packagefiles.Main.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import static packagefiles.Main.*;
import static packagefiles.Candidate.*;

public class MainController implements Initializable {
    ToggleGroup ageGroup = new ToggleGroup();
    public RadioButton age1820;
    public RadioButton age2124;
    public RadioButton age2529;
    public RadioButton age3039;
    public RadioButton age40;


    ToggleGroup genderGroup = new ToggleGroup();
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public RadioButton genderOther;


    ToggleGroup originGroup = new ToggleGroup();
    public RadioButton originUK;
    public RadioButton originEEA;
    public RadioButton originInternational;

    ToggleGroup courseGroup = new ToggleGroup();
    public RadioButton courseCS;
    public RadioButton courseCG;
    public RadioButton courseWD;
    public RadioButton courseIT;
    public RadioButton courseITMB;


    ToggleGroup yearGroup = new ToggleGroup();
    public RadioButton year0;
    public RadioButton year1;
    public RadioButton year2;
    public RadioButton year3;
    public RadioButton year4;


    public TextField inputField;
    public Button voteButton;
    public Button resultsButton;

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


    public void vote(ActionEvent event) throws Exception {
        String name = inputField.getText();
        String age = ((RadioButton) ageGroup.getSelectedToggle()).getId();
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getId();
        String origin = ((RadioButton) originGroup.getSelectedToggle()).getId();
        String course = ((RadioButton) courseGroup.getSelectedToggle()).getId();
        String year = ((RadioButton) yearGroup.getSelectedToggle()).getId();
        // need to fix if all properties are selected
        System.out.println(name + age + gender + origin + course + year);
        addData(name, age, gender, origin, course, year);
    }


    /**
     * Action event for checkResults button. Calculates the votes saved in the candidateList/voteList and displays 
     * a pie chart with the candidates.
     * @param event
     * @throws Exception
     */
    public void checkResults(ActionEvent event) throws Exception {
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
                        pieChartDataList.add(new PieChart.Data(candidateList[i].getName(),voteList[i]/(voteList.length - 1.0)));
                    }
                    // create a Pie chart to display the votes
                    final PieChart chart = new PieChart(pieChartDataList);
                    chart.setTitle("Votes");

                    // creates a label to display the percentage
                    final Label PERCENTAGE = new Label("");
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
                                    String text = String.format("%.1f%%", data.getPieValue()/total * 100) ;
                                    PERCENTAGE.setText(text);
                                }
                        );
                    }

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


                    System.out.println("2124 " + candidateList[0].getAge1820());
                    System.out.println("2124 " + candidateList[0].getAge2124());

                    System.out.println("25 29 " + candidateList[0].getAge2529() );

                    System.out.println("30 39 " + candidateList[0].getAge3039());

                    System.out.println("40 " + candidateList[0].getAge40());

                }
                catch (Exception e) {
                    // pinpoints the the location of the error occurrence
                    System.out.println("Error occurred while opening the final results page.");
                    e.printStackTrace();
                }
        }

    public void checkAgeGroup(ActionEvent event) throws Exception {
        try {
            // creates a scene and adds it to a group
            Scene agrGroupPage = new Scene(new Group());
            // creates a new window to display the stats
            Stage window = new Stage();

            // assign an initial value to increment in the loop
            int a1820 = 0;
            int a2124 = 0;
            int a2529 = 0;
            int a3039 = 0;
            int a40   = 0;


            for (int i = 0; i < getNumberOfCandidates(); i++) {
                // adds the candidates name and calculates the percentage (candidates votes)/total votes
                // multiplication * 1 to turn into floating to remove the error
                a1820 += candidateList[i].getAge1820();
                System.out.println(" check age 18 20 " + a1820);
                a2124 += candidateList[i].getAge2124();
                System.out.println(" check age 21 23 " + a2124);
                a2529 += candidateList[i].getAge2529();
                System.out.println(" check age 25 29 " + a2529);
                a3039 += candidateList[i].getAge3039();
                System.out.println(" check age 30 39 " + a3039);
                a40   += candidateList[i].getAge40();
                System.out.println(" check age 40  " + a40);
            }
            // makes an Observable list, similar to ArrayList but for FX nodes
            ObservableList<PieChart.Data> pieChartDataList =
                    FXCollections.observableArrayList(
                            // 1.0 to convert to floating point
                            new PieChart.Data("18 - 20", a1820/(voteList.length - 1.0)),
                            new PieChart.Data("21 - 24", a2124/(voteList.length - 1.0)),
                            new PieChart.Data("25 - 29", a2529 /(voteList.length - 1.0)),
                            new PieChart.Data("30 - 39", a3039/(voteList.length - 1.0)),
                            new PieChart.Data("40+",     a40 /(voteList.length - 1.0)));

            // create a Pie chart to display the age group that voted
            final PieChart chart = new PieChart(pieChartDataList);
            chart.setTitle("Age Groups");

            // creates a label to display the percentage
            final Label PERCENTAGE = new Label("");
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
                            String text = String.format("%.1f%%", data.getPieValue()/total * 100) ;
                            PERCENTAGE.setText(text);
                        }
                );
            }

            // adds the chart to the Group
            ((Group) agrGroupPage.getRoot()).getChildren().add(chart);
            ((Group) agrGroupPage.getRoot()).getChildren().add(PERCENTAGE);
            // sets the Scene to the window
            window.setScene(agrGroupPage);
            // applies the preferred icon
            window.getIcons().add(new Image(PIECHARTICON));
            // preferred title atop of the window
            window.setTitle("Age Groups");
            // shows the window
            window.show();
            // makes the window not resizable
            window.setResizable(false);
        }
        catch (Exception e) {
            // pinpoints the the location of the error occurrence
            System.out.println("Error occurred while opening the Age Groups page.");
            e.printStackTrace();
        }
    }
            }



