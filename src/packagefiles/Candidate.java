package packagefiles;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import packagefiles.Main.*;

public class Candidate {
    String name = "";

    int age1820 = 0;
    int age2124 = 0;
    int age2529 = 0;
    int age3039 = 0;
    int age40 = 0;

    int genderMale = 0;
    int genderFemale = 0;
    int genderOther = 0;

    int originUK = 0;
    int originEEA = 0;
    int originInternational = 0;

    int courseCS = 0;
    int courseCG = 0;
    int courseWD = 0;
    int courseIT = 0;
    int courseITMB = 0;

    int year0 = 0;
    int year1 = 0;
    int year2 = 0;
    int year3 = 0;
    int year4 = 0;

    Candidate(String name, String ageGroup, String genderType, String origin, String courseName, String year) {
        this.name = name;
        switch (ageGroup) {
            case "age1820" : age1820++;
            case "age2124" : age2124++;
            case "age2529" : age2529++;
            case "age3039" : age3039++;
            case "age40" : age40++;
        }
        switch (genderType) {
            case "genderMale" : genderMale++;
            case "genderFemale" : genderFemale++;
            case "genderOther" : genderOther++;
        }
        switch (origin) {
            case "originUK" : originUK++;
            case "originEEA" : originEEA++;
            case "originInternational" : originInternational++;
        }
        switch (courseName) {
            case "courseCS" : courseCS++;
            case "courseCG" : courseCG++;
            case "courseWD" : courseWD++;
            case "courseIT" : courseIT++;
            case "courseITMB" : courseITMB++;
        }
        switch (year) {
            case "year0" : year0++;
            case "year1" : year1++;
            case "year2" : year2++;
            case "year3" : year3++;
            case "year4" : year4++;
        }

    }
}
