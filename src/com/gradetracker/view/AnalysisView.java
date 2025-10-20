package com.gradetracker.view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.LinkedHashMap; // Import for the GraphPanel data

public class AnalysisView extends JFrame {

    private JLabel overallCgpaLabel;
    private GraphPanel graphPanel;

    /**
     * Constructor for the AnalysisView. Sets up the window and UI components.
     */
    public AnalysisView() {
        setTitle("Performance Analysis");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setLocationRelativeTo(null);

        // Main panel with a border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Panel for the overall CGPA label at the top
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        overallCgpaLabel = new JLabel("Overall CGPA: --");
        overallCgpaLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        topPanel.add(overallCgpaLabel);

        // The graph panel will be in the center
        graphPanel = new GraphPanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(graphPanel, BorderLayout.CENTER);

        this.add(mainPanel);
    }

    /**
     * Called by the controller to pass the chart data to the view.
     * @param data A map where the key is the semester number and the value is the SGPA.
     */
    public void displayChart(Map<Integer, Double> data) {
        graphPanel.setData(data);
        repaint(); // Redraw the component with the new data
    }

    /**
     * Called by the controller to display the overall CGPA.
     * @param cgpa The calculated overall CGPA.
     */
    public void displayOverallCGPA(double cgpa) {
        overallCgpaLabel.setText(String.format("Overall CGPA: %.2f", cgpa));
    }

    /**
     * A private inner class to handle the custom drawing of the line graph.
     */
    private static class GraphPanel extends JPanel {
        private Map<Integer, Double> sgpaData;

        public GraphPanel() {
            this.sgpaData = new LinkedHashMap<>(); // Initialize to an empty map
            setBackground(Color.WHITE);
        }

        public void setData(Map<Integer, Double> sgpaData) {
            this.sgpaData = sgpaData;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (sgpaData == null || sgpaData.isEmpty()) {
                g.drawString("No SGPA data available to display.", getWidth() / 2 - 100, getHeight() / 2);
                return;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 60;
            int pointRadius = 6;

            double maxSGPA = 10.0;
            int semesters = sgpaData.size();

            // Draw axes
            g2.setColor(Color.BLACK);
            g2.drawLine(padding, height - padding, width - padding, height - padding); // X-axis
            g2.drawLine(padding, padding, padding, height - padding); // Y-axis

            // Plot points
            int i = 0;
            int prevX = -1, prevY = -1;

            for (Map.Entry<Integer, Double> entry : sgpaData.entrySet()) {
                int semester = entry.getKey();
                double sgpa = entry.getValue();

                int x = padding + (i * (width - 2 * padding) / (semesters > 1 ? semesters - 1 : 1));
                int y = (int) ((height - padding) - (sgpa / maxSGPA) * (height - 2 * padding));

                // Draw point
                g2.setColor(new Color(100, 150, 255));
                g2.fillOval(x - pointRadius, y - pointRadius, pointRadius * 2, pointRadius * 2);

                // Label point and semester
                g2.setColor(Color.BLACK);
                g2.drawString(String.format("%.2f", sgpa), x - 15, y - 15);
                g2.drawString("Sem " + semester, x - 20, height - padding + 20);

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

            // Draw Y-axis ticks and labels
            g2.setColor(Color.GRAY);
            for (int j = 0; j <= 10; j++) {
                int yTick = (int) ((height - padding) - (j / 10.0) * (height - 2 * padding));
                g2.drawLine(padding - 5, yTick, padding, yTick);
                g2.drawString(String.valueOf(j), padding - 25, yTick + 5);
            }
        }
    }
}