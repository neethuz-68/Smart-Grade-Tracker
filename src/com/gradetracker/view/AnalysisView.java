package com.gradetracker.view;

import com.gradetracker.model.Student;
import com.gradetracker.controller.AnalysisController;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Displays a graphical SGPA analysis chart for the logged-in student.
 */
public class AnalysisView extends JFrame {

    private final Student loggedInStudent;
    private final AnalysisController analysisController;

    public AnalysisView(Student student, AnalysisController analysisController) {
        this.loggedInStudent = student;
        this.analysisController = analysisController;

        setTitle("SGPA Analysis - " + student.getName());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        Map<Integer, Double> sgpaData = analysisController.getSgpaBySemester(loggedInStudent);
        add(new GraphPanel(sgpaData));
    }

    // --- Inner Panel for Graph Drawing ---
    static class GraphPanel extends JPanel {
        private final Map<Integer, Double> sgpaData;

        public GraphPanel(Map<Integer, Double> sgpaData) {
            this.sgpaData = sgpaData;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (sgpaData == null || sgpaData.isEmpty()) {
                g.drawString("No SGPA data available", getWidth() / 2 - 50, getHeight() / 2);
                return;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 60;
            int labelPadding = 40;
            int pointRadius = 6;

            double maxSGPA = 10.0;
            double minSGPA = 0.0;
            int semesters = sgpaData.size();

            // Draw axes
            g2.setColor(Color.BLACK);
            g2.drawLine(padding, height - padding, width - padding, height - padding); // X-axis
            g2.drawLine(padding, padding, padding, height - padding); // Y-axis

            // Axis labels
            g2.drawString("Semester", width / 2 - 20, height - 20);
            g2.drawString("SGPA", 10, padding - 10);

            // Plot points
            int i = 0;
            int prevX = 0, prevY = 0;

            for (Map.Entry<Integer, Double> entry : sgpaData.entrySet()) {
                int semester = entry.getKey();
                double sgpa = entry.getValue();

                // Convert to coordinates
                int x = padding + (i * (width - 2 * padding) / (semesters - 1));
                int y = (int) ((height - padding) - ((sgpa - minSGPA) / (maxSGPA - minSGPA)) * (height - 2 * padding));

                // Draw point
                g2.setColor(new Color(100, 150, 255));
                g2.fillOval(x - pointRadius, y - pointRadius, pointRadius * 2, pointRadius * 2);

                // Label
                g2.setColor(Color.BLACK);
                g2.drawString(String.format("%.2f", sgpa), x - 10, y - 10);
                g2.drawString("S" + semester, x - 10, height - padding + 20);

                // Connect with previous point
                if (i > 0) {
                    g2.setColor(Color.BLUE);
                    g2.setStroke(new BasicStroke(2f));
                    g2.drawLine(prevX, prevY, x, y);
                }

                prevX = x;
                prevY = y;
                i++;
            }

            // Draw Y-axis ticks
            g2.setColor(Color.GRAY);
            for (int j = 0; j <= 10; j++) {
                int y = (int) ((height - padding) - (j / 10.0) * (height - 2 * padding));
                g2.drawLine(padding - 5, y, padding + 5, y);
                g2.drawString(String.valueOf(j), padding - 25, y + 5);
            }
        }
    }
}
