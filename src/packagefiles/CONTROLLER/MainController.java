package packagefiles.CONTROLLER;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    ToggleGroup ageGroup = new ToggleGroup();
    public RadioButton age1824;
    public RadioButton age2529;
    public RadioButton age3039;
    public RadioButton age4049;
    public RadioButton age5064;
    public RadioButton age65;


    ToggleGroup sexGroup = new ToggleGroup();
    public RadioButton sexMale;
    public RadioButton sexFemale;
    public RadioButton sexLGBT;
    public RadioButton sexRefuse;


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
        age1824.setToggleGroup(ageGroup);
        age2529.setToggleGroup(ageGroup);
        age3039.setToggleGroup(ageGroup);
        age4049.setToggleGroup(ageGroup);
        age5064.setToggleGroup(ageGroup);
        age65.setToggleGroup(ageGroup);

        sexMale.setToggleGroup(sexGroup);
        sexFemale.setToggleGroup(sexGroup);
        sexLGBT.setToggleGroup(sexGroup);
        sexRefuse.setToggleGroup(sexGroup);

        originUK.setToggleGroup(originGroup);
        originEEA.setToggleGroup(originGroup);
        originInternational.setToggleGroup(originGroup);

        courseCS.setToggleGroup(courseGroup);
        courseCG.setToggleGroup(courseGroup);
        courseWD.setToggleGroup(courseGroup);
        courseIT.setToggleGroup(courseGroup);
        courseITMB.setToggleGroup(courseGroup);


    }
}
