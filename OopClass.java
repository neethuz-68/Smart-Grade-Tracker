public class Subject {
    private int subjectId;
    private String subjectName;
    private int credits;

    
    public Subject(int subjectId, String subjectName, int credits) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}
public class Student {
    private int studentId;
    private String name;
    private Semester semester;

    
    public Student(int studentId, String name, Semester semester) {
        this.studentId = studentId;
        this.name = name;
        this.semester = semester;
    }

    
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Semester getSemester() { return semester; }
    public void setSemester(Semester semester) { this.semester = semester; }
}
import java.util.List;

public class Semester {
    private int semesterId;
    private int semesterNumber;
    private List<Subject> subjects;

    
    public Semester(int semesterId, int semesterNumber, List<Subject> subjects) {
        this.semesterId = semesterId;
        this.semesterNumber = semesterNumber;
        this.subjects = subjects;
    }

    
    public int getSemesterId() { return semesterId; }
    public void setSemesterId(int semesterId) { this.semesterId = semesterId; }

    public int getSemesterNumber() { return semesterNumber; }
    public void setSemesterNumber(int semesterNumber) { this.semesterNumber = semesterNumber; }

    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }
}
