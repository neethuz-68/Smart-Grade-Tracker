// In file: com/gradecalculator/models/Subject.java
package com.gradetracker.model;

public class Subject {
    private final int subId;
    private final String subjectName;
    private final int credit;

    public Subject(int subId, String subjectName, int credit) {
        this.subId = subId;
        this.subjectName = subjectName;
        this.credit = credit;
    }

    // --- Getters ---
    public int getSubId() {
        return subId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return "Subject{" +
               "subjectName='" + subjectName + '\'' +
               ", credit=" + credit +
               '}';
    }
}