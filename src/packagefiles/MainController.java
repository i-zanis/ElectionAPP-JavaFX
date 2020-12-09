package packagefiles;

import javafx.scene.control.*;
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





    public void checkResults(ActionEvent event) throws Exception {
           /* for (int i = 0; i < getNumberOfCandidates(); i++) {
                System.out.println(candidateList[i].getName());
                System.out.println(voteList[i]);
                */


                try {
                    //Parent finalPageView = FXMLLoader.load(getClass().getResource(FINALPAGE));
                    Scene finalPage = new Scene(new Group());
                    Stage window = new Stage();

                    ObservableList<PieChart.Data> pieChartData =
                            FXCollections.observableArrayList();
                    for (int i = 0; i < getNumberOfCandidates(); i++) {
                        // multiplication * 1 to turn into floating to remove the error
                        pieChartData.add(new PieChart.Data(candidateList[i].getName(),voteList[i] * 1.0/(voteList.length - 1)));
                    }

                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Votes");

                    final Label caption = new Label("");
                    caption.setStyle("-fx-font: 35 arial;");

                    for (final PieChart.Data data : chart.getData()) {
                        data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                                e -> {
                                    double total = 0;
                                    for (PieChart.Data d : chart.getData()) {
                                        total += d.getPieValue();
                                    }
                                    caption.setTranslateX(e.getSceneX());
                                    caption.setTranslateY(e.getSceneY());
                                    String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
                                    caption.setText(text);
                                }
                        );
                    }

                    ((Group) finalPage.getRoot()).getChildren().add(chart);
                    ((Group) finalPage.getRoot()).getChildren().add(caption);

                    window.setScene(finalPage);
                    window.setTitle("Results");
                    window.show();
                }
                catch (Exception e) {
                    System.out.println("Error occurred while opening the final results page.");
                    e.printStackTrace();
                }
        }
            }

          /*class PieChartSample extends Application {




                    ObservableList<PieChart.Data> pieChartData =
                            FXCollections.observableArrayList(
                                    new PieChart.Data("Grapefruit", 13),
                                    new PieChart.Data("Oranges", 25),
                                    new PieChart.Data("Plums", 10),
                                    new PieChart.Data("Pears", 22),
                                    new PieChart.Data("Apples", 30));
                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Imported Fruits");


                public void main(String[] args) {
                    launch(args);
                }
            }

      */

