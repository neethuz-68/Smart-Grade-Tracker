import javax.swing.*;
import java.awt.*;

public class DashboardPage extends JFrame {
    public DashboardPage() {
        setTitle("Dashboard - Grade Calculator");
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel background = new JPanel(new GridBagLayout());
        background.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel title = new JLabel("Welcome to Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 36));
        title.setForeground(new Color(44, 62, 80)); // Dark gray text
        gbc.gridy = 0;
        centerPanel.add(title, gbc);

        JButton gradeEntryBtn = createNavButton(" Grade Entry", new Color(72, 201, 176));
        JButton analysisBtn = createNavButton(" Analysis", new Color(52, 152, 219));
        JButton plannerBtn = createNavButton(" Planner", new Color(231, 76, 60));

        gbc.gridy = 1;
        centerPanel.add(gradeEntryBtn, gbc);

        gbc.gridy = 2;
        centerPanel.add(analysisBtn, gbc);

        gbc.gridy = 3;
        centerPanel.add(plannerBtn, gbc);

        background.add(centerPanel);
        add(background);
        setVisible(true);

        gradeEntryBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Grade Entry..."));
        analysisBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Analysis..."));
        plannerBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Planner..."));
    }

    private JButton createNavButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 22));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(300, 70));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashboardPage::new);
    }
}
