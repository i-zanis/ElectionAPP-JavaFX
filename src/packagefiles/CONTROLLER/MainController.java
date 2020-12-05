package packagefiles.CONTROLLER;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;
import static packagefiles.Main.*;

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

        RadioButton radiobutton = (RadioButton)ageGroup.getSelectedToggle();
        if (radiobutton != null) {
            String s = radiobutton.getText();
            System.out.println(s);
        }


        String age = ageGroup.getSelectedToggle().toString();
        String gender = genderGroup.getSelectedToggle().toString();
        String origin = originGroup.getSelectedToggle().toString();
        String course = courseGroup.getSelectedToggle().toString();
        String year = yearGroup.getSelectedToggle().toString();
        addData( name,  age,  gender,  origin,  course,  year);


    }

    public static void addData(String name, String age, String gender, String origin, String course, String year) {
        add2Array(nameList,name);
        add2Array(ageList,age);
        add2Array(genderList,gender);
        add2Array(originList,origin);
        add2Array(courseList,course);
        add2Array(yearList,year);
        numberOfCandidates++; // increment the total number of candidates
        for (int i = 0; i < nameList.length; i++) {
            System.out.println(nameList[i]);
        }
    }
}