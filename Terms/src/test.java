import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class test extends JFrame {
    private JPanel sidebar;
    private JPanel homePanel;
    private JPanel homePage;
    private JPanel shortcutPanel;
    private JPanel viewShortcut;
    private JPanel analyticsShortcut;
    private JPanel recordsPanel;
    private JPanel viewPanel;
    private JPanel statsPanel;
    private Timer expandTimer;
    private Timer collapseTimer;
    private int sidebarWidth = 100;
    private final int expandedWidth = 200;
    private final int collapsedWidth = 100;
    private final int animationStep = 5;

    private JPanel homeItem, recordsItem, viewItem, statsItem;
    private JPanel selectedPanel = null; // Tracks the selected item

    public test() {
        formatFrame();
    }

    public void formatFrame() {
        setTitle("TeRMS");
        setSize(1280, 700);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Sidebar Panel
        sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(Color.darkGray);
        sidebar.setBounds(0, 0, sidebarWidth, getHeight());
        add(sidebar);

        // Home Panel
        homePanel = new JPanel();
        homePanel.setBackground(Color.WHITE);
        homePanel.setBounds(100, 0, 1180, 700);
        homePanel.setLayout(null);
        add(homePanel);

        homePage = new JPanel();
        homePage.setBackground(Color.white);
        homePage.setLayout(null);
        homePage.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        homePage.setBounds(0, 0, 1180, 100);
        homePage.setVisible(true);
        homePanel.add(homePage);

        JLabel txtHome = new JLabel("Home");
        txtHome.setFont(new Font("Arial", Font.BOLD, 30));
        txtHome.setBounds(550, 20, 200, 100);
        homePage.add(txtHome);

        
        JLabel txtShortcut = new JLabel("Add new record");
        txtShortcut.setFont(new Font("Arial", Font.BOLD, 20));
        txtShortcut.setBounds(40, 100, 200, 100);
        homePanel.add(txtShortcut);

        JLabel txtViewShortcut = new JLabel("View all records");
        txtViewShortcut.setFont(new Font("Arial", Font.BOLD, 20));
        txtViewShortcut.setBounds(40, 250, 200, 100);
        homePanel.add(txtViewShortcut);

        JLabel txtAnalyticsShortcut = new JLabel("View analytics");
        txtAnalyticsShortcut.setFont(new Font("Arial", Font.BOLD, 20));
        txtAnalyticsShortcut.setBounds(40, 400, 200, 100);
        homePanel.add(txtAnalyticsShortcut);
        
        // Add shortcut Panel 
        shortcutPanel = new JPanel();
        shortcutPanel.setBackground(Color.white);
        shortcutPanel.setLayout(null);
        shortcutPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        shortcutPanel.setBounds(40, 170, 400, 100);
        shortcutPanel.setVisible(true);
        homePanel.add(shortcutPanel);

        // view all records
        viewShortcut = new JPanel();
        viewShortcut.setBackground(Color.white);
        viewShortcut.setLayout(null);
        viewShortcut.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        viewShortcut.setBounds(40,320, 400, 100);
        viewShortcut.setVisible(true);
        homePanel.add(viewShortcut);

        //view analytics
        analyticsShortcut = new JPanel();
        analyticsShortcut.setBackground(Color.white);
        analyticsShortcut.setLayout(null);
        analyticsShortcut.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        analyticsShortcut.setBounds(40, 470, 400, 100);
        analyticsShortcut.setVisible(true);
        homePanel.add(analyticsShortcut);
        

        // Records Panel
        recordsPanel = new JPanel();
        recordsPanel.setBackground(Color.white);
        recordsPanel.setBounds(100, 0, 1180, 700);
        recordsPanel.setVisible(false); 
        add(recordsPanel);

        JLabel txtRecords = new JLabel("Records");
        txtRecords.setBounds(10, 10, 100, 50);
        recordsPanel.add(txtRecords);

        // View Panel
        viewPanel = new JPanel();
        viewPanel.setBackground(Color.white);
        viewPanel.setBounds(100, 0, 1180, 700);
        viewPanel.setVisible(false); 
        add(viewPanel);

        JLabel txtView = new JLabel("View");
        txtView.setBounds(10, 10, 100, 50);
        viewPanel.add(txtView);
        // Stats Panel
        statsPanel = new JPanel();
        statsPanel.setBackground(Color.white);
        statsPanel.setBounds(100, 0, 1180, 700);
        statsPanel.setVisible(false); 
        add(statsPanel);

        JLabel txtStats = new JLabel("Analytics");
        txtStats.setBounds(10, 10, 100, 50);    
        statsPanel.add(txtStats);

        // Image Icons
        ImageIcon homeIcon = new ImageIcon("C:\\Users\\Baby Rose R. Garcia\\Documents\\New folder\\VS_SHID-main\\Terms\\src\\img\\bahay.jpg");
        ImageIcon recordsIcon = new ImageIcon("C:\\Users\\Baby Rose R. Garcia\\Documents\\New folder\\VS_SHID-main\\Terms\\src\\img\\image-50x50 (2).jpg");
        ImageIcon viewIcon = new ImageIcon("C:\\Users\\Baby Rose R. Garcia\\Documents\\New folder\\VS_SHID-main\\Terms\\src\\img\\eye.jpg");
        ImageIcon statsIcon = new ImageIcon("C:\\Users\\Baby Rose R. Garcia\\Documents\\New folder\\VS_SHID-main\\Terms\\src\\img\\stats.jpg");

        // Home Item
        homeItem = createNavItem(homeIcon, "Home", 10, 130); // Adjusted position
        sidebar.add(homeItem);

        // Records Item
        recordsItem = createNavItem(recordsIcon, "Records", 10, 230); // Adjusted position
        sidebar.add(recordsItem);

        // View Item
        viewItem = createNavItem(viewIcon, "View", 10, 330); // Adjusted position
        sidebar.add(viewItem);

        // Stats Item
        statsItem = createNavItem(statsIcon, "Analytics", 10, 430); // Adjusted position
        sidebar.add(statsItem);

        // Add mouse listeners for switching panels
        homeItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showHomePanel();
                highlightSelectedItem(homeItem);
            }
        });

        recordsItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showRecordsPanel();
                highlightSelectedItem(recordsItem);
            }
        });

        viewItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showViewPanel();
                highlightSelectedItem(viewItem);
            }
        });

        statsItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showStatsPanel();
                highlightSelectedItem(statsItem);
            }
        });

        // Mouse listeners for sidebar hover effect
        sidebar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (collapseTimer != null && collapseTimer.isRunning()) {
                    collapseTimer.stop();
                }
                expandSidebar();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (expandTimer != null && expandTimer.isRunning()) {
                    expandTimer.stop();
                }
                collapseSidebar();
            }
        });

        setVisible(true);
    }

    private void showHomePanel() {
        homePanel.setVisible(true);
        recordsPanel.setVisible(false);
        viewPanel.setVisible(false);
        statsPanel.setVisible(false);
    }

    private void showRecordsPanel() {
        homePanel.setVisible(false);
        recordsPanel.setVisible(true);
        viewPanel.setVisible(false);
        statsPanel.setVisible(false);
    }

    private void showViewPanel() {
        homePanel.setVisible(false);
        recordsPanel.setVisible(false);
        viewPanel.setVisible(true);
        statsPanel.setVisible(false);
    }

    private void showStatsPanel() {
        homePanel.setVisible(false);
        recordsPanel.setVisible(false);
        viewPanel.setVisible(false);
        statsPanel.setVisible(true);
    }

    // Method to create sidebar navigation items
    private JPanel createNavItem(ImageIcon icon, String text, int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, sidebarWidth < expandedWidth ? 70 : 180, 80); // Adjusted width based on sidebar state
        panel.setBackground(Color.darkGray);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(10, 15, 50, 50); // Adjusted position of the icon
        panel.add(iconLabel);

        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 14));
        textLabel.setBounds(70, 15, 100, 50); // Adjusted position of the text
        textLabel.setVisible(sidebarWidth >= expandedWidth); // Set visibility based on sidebar state
        panel.add(textLabel);

        panel.putClientProperty("textLabel", textLabel); // Store textLabel in panel's client properties

        return panel;
    }

    // Method to highlight the selected item
    private void highlightSelectedItem(JPanel panel) {
        if (selectedPanel != null) {
            selectedPanel.setBorder(null); // Remove highlight from previous selection
            selectedPanel.setBackground(Color.darkGray); // Change to darkGray when deselected
        }

        panel.setBorder(BorderFactory.createLineBorder(new Color(41, 121, 255), 2, true));
        panel.setBackground(Color.gray);
        selectedPanel = panel;
    }

    private void expandSidebar() {
        expandTimer = new Timer(10, e -> {
            if (sidebarWidth < expandedWidth) {
                sidebarWidth += animationStep;
                sidebar.setBounds(0, 0, sidebarWidth, getHeight());
                homeItem.setBounds(10, 130, sidebarWidth - 20, 80); // Adjust home item width dynamically
                recordsItem.setBounds(10, 230, sidebarWidth - 20, 80); // Adjust records item width dynamically
                viewItem.setBounds(10, 330, sidebarWidth - 20, 80); // Adjust view item width dynamically
                statsItem.setBounds(10, 430, sidebarWidth - 20, 80); // Adjust stats item width dynamically
    
                // Show text gradually
                if (sidebarWidth > collapsedWidth + 20) {
                    ((JLabel) homeItem.getClientProperty("textLabel")).setVisible(true);
                    ((JLabel) recordsItem.getClientProperty("textLabel")).setVisible(true);
                    ((JLabel) viewItem.getClientProperty("textLabel")).setVisible(true);
                    ((JLabel) statsItem.getClientProperty("textLabel")).setVisible(true);
                }
            } else {
                expandTimer.stop();
            }
        });
        expandTimer.start();
    }
    
    private void collapseSidebar() {
        ((JLabel) homeItem.getClientProperty("textLabel")).setVisible(false); // Hide text immediately
        ((JLabel) recordsItem.getClientProperty("textLabel")).setVisible(false); // Hide text immediately
        ((JLabel) viewItem.getClientProperty("textLabel")).setVisible(false); // Hide text immediately
        ((JLabel) statsItem.getClientProperty("textLabel")).setVisible(false); // Hide text immediately
    
        collapseTimer = new Timer(10, e -> {
            if (sidebarWidth > collapsedWidth) {
                sidebarWidth -= animationStep;
                sidebar.setBounds(0, 0, sidebarWidth, getHeight());
                homeItem.setBounds(10, 130, sidebarWidth - 20, 80); // Adjust home item width dynamically
                recordsItem.setBounds(10, 230, sidebarWidth - 20, 80); // Adjust records item width dynamically
                viewItem.setBounds(10, 330, sidebarWidth - 20, 80); // Adjust view item width dynamically
                statsItem.setBounds(10, 430, sidebarWidth - 20, 80); // Adjust stats item width dynamically
            } else {
                collapseTimer.stop();
            }
        });
        collapseTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(test::new);
    }
}
