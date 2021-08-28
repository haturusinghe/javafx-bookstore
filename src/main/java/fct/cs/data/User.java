package fct.cs.data;

public class User {
    private String studentID;
    private String grade;


    public User(String studentID, String grade) {
        this.studentID = studentID;
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
