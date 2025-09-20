package ui;

import javax.swing.*;

public class AnalysisPage extends JFrame {
    public AnalysisPage() {
        setTitle("Analysis - Smart Grade Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Semester GPA Trend", new SemesterGpaPanel());
        tabbedPane.add("Subject Averages", new SubjectAveragePanel());
        tabbedPane.add("CGPA Progress", new CgpaPanel());
        tabbedPane.add("Grade Distribution", new GradeDistributionPanel());

        add(tabbedPane);
        setVisible(true);
    }
}
