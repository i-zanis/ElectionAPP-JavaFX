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

    public int getAge1820() {
        return age1820;
    }

    public int getAge2124() {
        return age2124;
    }

    public int getAge2529() {
        return age2529;
    }

    public int getAge3039() {
        return age3039;
    }

    public int getAge40() {
        return age40;
    }

    public int getGenderMale() {
        return genderMale;
    }

    public int getGenderFemale() {
        return genderFemale;
    }

    public int getGenderOther() {
        return genderOther;
    }

    public int getOriginUK() {
        return originUK;
    }

    public int getOriginEEA() {
        return originEEA;
    }

    public int getOriginInternational() {
        return originInternational;
    }

    public int getCourseCS() {
        return courseCS;
    }

    public int getCourseCG() {
        return courseCG;
    }

    public int getCourseWD() {
        return courseWD;
    }

    public int getCourseIT() {
        return courseIT;
    }

    public int getCourseITMB() {
        return courseITMB;
    }

    public int getYear0() {
        return year0;
    }

    public int getYear1() {
        return year1;
    }

    public int getYear2() {
        return year2;
    }

    public int getYear3() {
        return year3;
    }

    public int getYear4() {
        return year4;
    }

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
    // testing to see if this works
    protected void updateStats(String ageGroup, String genderType, String origin, String courseName, String year) {
        switch (ageGroup) {
            case "age1820" : this.age1820++;
            case "age2124" : this.age2124++;
            case "age2529" : this.age2529++;
            case "age3039" : this.age3039++;
            case "age40" :   this.age40++;
        }
        switch (genderType) {
            case "genderMale" : this.genderMale++;
            case "genderFemale" : this.genderFemale++;
            case "genderOther" : this.genderOther++;
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
