package packagefiles;

/**
 * Candidate object with several fields to save data of what specific groups participated in the voting
 */
     class Candidate {
    // the total count of the candidates
    private static int numberOfCandidates = 0;
    // initialize name with empty value to prevent errors
    String name = "";
    // age groups
    int age1820 = 0;
    int age2124 = 0;
    int age2529 = 0;
    int age3039 = 0;
    int age40 = 0;
    // gender groups
    int genderMale = 0;
    int genderFemale = 0;
    int genderOther = 0;
    // origin groups
    int originUK = 0;
    int originEEA = 0;
    int originInternational = 0;
    // course groups
    int courseCS = 0;
    int courseCG = 0;
    int courseWD = 0;
    int courseIT = 0;
    int courseITMB = 0;
    // year groups
    int year0 = 0;
    int year1 = 0;
    int year2 = 0;
    int year3 = 0;
    int year4 = 0;

    // Candidate constructor
    Candidate(String name, String ageGroup, String genderType, String origin, String courseName, String year) {
        this.name = name;

        switch (ageGroup) {
            case "age1820" -> age1820++;
            case "age2124" -> age2124++;
            case "age2529" -> age2529++;
            case "age3039" -> age3039++;
            case "age40"   -> age40++;
        }
        switch (genderType) {
            case "genderMale"   -> genderMale++;
            case "genderFemale" -> genderFemale++;
            case "genderOther"  -> genderOther++;
        }
        switch (origin) {
            case "originUK"            -> originUK++;
            case "originEEA"           -> originEEA++;
            case "originInternational" -> originInternational++;
        }
        switch (courseName) {
            case "courseCS"     -> courseCS++;
            case "courseCG"     -> courseCG++;
            case "courseWD"     -> courseWD++;
            case "courseIT"     -> courseIT++;
            case "courseITMB"   -> courseITMB++;
        }
        switch (year) {
            case "year0" -> year0++;
            case "year1" -> year1++;
            case "year2" -> year2++;
            case "year3" -> year3++;
            case "year4" -> year4++;
        }
    }

    // increments the number of candidates by 1
    protected static int incrementNumberOfCandidates() {
        return numberOfCandidates++;
    }

    // returns the number of candidates
    protected static int getNumberOfCandidates() {
        return numberOfCandidates;
    }

    // candidate fields accessor methods
    protected String getName() {
        return name;
    }

    protected int getAge1820() {
        return age1820;
    }

    protected int getAge2124() {
        return age2124;
    }

    protected int getAge2529() {
        return age2529;
    }

    protected int getAge3039() {
        return age3039;
    }

    protected int getAge40() {
        return age40;
    }

    protected int getGenderMale() {
        return genderMale;
    }

    protected int getGenderFemale() {
        return genderFemale;
    }

    protected int getGenderOther() {
        return genderOther;
    }

    protected int getOriginUK() {
        return originUK;
    }

    protected int getOriginEEA() {
        return originEEA;
    }

    protected int getOriginInternational() {
        return originInternational;
    }

    protected int getCourseCS() {
        return courseCS;
    }

    protected int getCourseCG() {
        return courseCG;
    }

    protected int getCourseWD() {
        return courseWD;
    }

    protected int getCourseIT() {
        return courseIT;
    }

    protected int getCourseITMB() {
        return courseITMB;
    }

    protected int getYear0() {
        return year0;
    }

    protected int getYear1() {
        return year1;
    }

    protected int getYear2() {
        return year2;
    }

    protected int getYear3() {
        return year3;
    }

    protected int getYear4() {
        return year4;
    }
    // end of candidate fields accessor methods

    /**
     * Takes values saved through a temporary variable in Main Controller Action Event methods fired through a button
     * and updates the stats based on that value by the use of enhanced switch statement
     * @param ageGroup   radio button choice
     * @param genderType radio button choice
     * @param origin     radio button choice
     * @param courseName radio button choice
     * @param year       radio button choice
     */
    void updateStats(String ageGroup, String genderType, String origin, String courseName, String year) {
        switch (ageGroup) {
            case "age1820" -> age1820++;
            case "age2124" -> age2124++;
            case "age2529" -> age2529++;
            case "age3039" -> age3039++;
            case "age40"   -> age40++;
        }
        switch (genderType) {
            case "genderMale"   -> genderMale++;
            case "genderFemale" -> genderFemale++;
            case "genderOther"  -> genderOther++;
        }
        switch (origin) {
            case "originUK"            -> originUK++;
            case "originEEA"           -> originEEA++;
            case "originInternational" -> originInternational++;
        }
        switch (courseName) {
            case "courseCS"    -> courseCS++;
            case "courseCG"    -> courseCG++;
            case "courseWD"    -> courseWD++;
            case "courseIT"    -> courseIT++;
            case "courseITMB"  -> courseITMB++;
        }
        switch (year) {
            case "year0" -> year0++;
            case "year1" -> year1++;
            case "year2" -> year2++;
            case "year3" -> year3++;
            case "year4" -> year4++;
        }
    }
}
