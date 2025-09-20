package models;

public class Subject {
    private int subjectId;
    private String subjectName;
    private int credits;
    private int marks;
    private String grade;

    // Constructor
    public Subject(int subjectId, String subjectName, int credits, int marks, String grade) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
        this.marks = marks;
        this.grade = grade;
    }

    // Getters & Setters
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return subjectName + " (" + grade + ", " + credits + " credits, " + marks + " marks)";
    }
}
