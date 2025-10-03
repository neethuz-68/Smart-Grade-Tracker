package com.gradetracker.view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AnalysisView extends JFrame {

    private JLabel overallCgpaLabel;
    private GraphPanel graphPanel;

    public AnalysisView() {
        setTitle("Performance Analysis");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        // Main panel with a border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Panel for the overall CGPA label at the top
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
     * @param data A map where the key is the semester name and the value is the SGPA.
     */
    public void displayChart(Map<String, Double> data) {
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
}

/**
 * A private inner class to handle the custom drawing of the bar chart.
 */
class GraphPanel extends JPanel {
    private Map<String, Double> data;

    public GraphPanel() {
        this.data = null; // Initially no data
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 60, 20));
    }

    public void setData(Map<String, Double> data) {
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}