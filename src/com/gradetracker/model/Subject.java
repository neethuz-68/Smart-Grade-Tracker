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
    @Override
    public String toString() {
        return this.subjectName;
    }
    public int getSubId() {
        return subId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getCredit() {
        return credit;
    }

}