// Libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame {
    // Panels
    private JPanel personalInformationPanel;
    private JPanel homePanel;
    private JPanel sidebar;
    private boolean isSidebarVisible = false;
    private Point initialClick;
    private Timer expandTimer;
    private Timer collapseTimer;
    private int sidebarWidth = 50;
    private final int expandedWidth = 200;
    private final int animationStep = 10;

    public Login() {
        formatFrame();
    }

    public void formatFrame() {
        // DO NOT TOUCH

        // Components

        // Panels
        JPanel titleBar = new JPanel();
        JPanel home = new JPanel();
        homePanel = new JPanel();
        sidebar = new JPanel();
        JPanel personalInformation = new JPanel();
        JPanel educationalBackground = new JPanel();
        JPanel employmentHistory = new JPanel();
        personalInformationPanel = new JPanel();

        // Labels
        JLabel x = new JLabel("=");
        JLabel txtHome = new JLabel("Home");
        JLabel txtPersonalInformation = new JLabel("Personal Information");
        JLabel txtEducationalBackground = new JLabel("Educational Background");
        JLabel txtEmploymentHistory = new JLabel("Employment History");

        
        // Buttons
        JButton btnHome = new JButton("Home");
        JButton btnServices = new JButton("Services");
        JButton btnOperations = new JButton("Operations");
        JButton btnAbout = new JButton("About");
        JButton btnContact = new JButton("Contact");
        
        // Main format
        setSize(1280, 720);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurations
        titleBar.setLayout(null);
        homePanel.setLayout(null);
        personalInformationPanel.setLayout(null);
        sidebar.setLayout(new GridLayout(4, 1));
        sidebar.setBackground(new Color(40, 100, 250));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setVisible(false);

        personalInformation.setLayout(null);
        titleBar.setBackground(Color.white);
        home.setBackground(Color.white);
        x.setForeground(Color.BLACK);
        txtHome.setForeground(Color.blue);
        homePanel.setBackground(new Color(189, 195, 199));
        personalInformationPanel.setBackground(new Color(0x228B22));
        txtPersonalInformation.setForeground(Color.blue);
        educationalBackground.setBackground(Color.white);
        txtEducationalBackground.setForeground(Color.blue);
        employmentHistory.setBackground(Color.white);
        txtEmploymentHistory.setForeground(Color.blue);

        // Placements
        titleBar.setBounds(0, 0, 1280, 60);
        homePanel.setBounds(100, 60, 1500, 660); // Adjusted bounds
        sidebar.setBounds(0, 60, 200, 660);
        personalInformationPanel.setBounds(180, 60, 1100, 660); // Adjusted bounds
        personalInformation.setBounds(10, 200, 160, 40);
        txtPersonalInformation.setBounds(25, 0, 160, 40);
        educationalBackground.setBounds(10, 270, 160, 40); 
        txtEducationalBackground.setBounds(12, 0, 160, 40);
        employmentHistory.setBounds(10, 340, 160, 40);
        txtEmploymentHistory.setBounds(22, 0, 160, 40);

       

        // Adding components
        // For personal information panel
        add(titleBar);
        add(sidebar);
        titleBar.add(x);
        add(home);
        home.add(txtHome);
        add(personalInformationPanel);
        add(homePanel);
        ;

        // Title bar
        add(titleBar);
        titleBar.add(x);

        // Navigation panel
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout());
        navBar.add(btnHome);
        navBar.add(btnServices);
        navBar.add(btnOperations);
        navBar.add(btnAbout);
        navBar.add(btnContact);
        add(navBar, BorderLayout.NORTH);

       

        titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Get the current location of the window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move the window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });

        // Add mouse listeners for sidebar hover effect
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
    }

    

    private void expandSidebar() {
        expandTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sidebarWidth < expandedWidth) {
                    sidebarWidth += animationStep;
                    sidebar.setBounds(0, 0, sidebarWidth, getHeight());
                    sidebar.revalidate();
                    sidebar.repaint();
                } else {
                    expandTimer.stop();
                }
            }
        });
        expandTimer.start();
    }

    private void collapseSidebar() {
        collapseTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sidebarWidth > 50) {
                    sidebarWidth -= animationStep;
                    sidebar.setBounds(0, 0, sidebarWidth, getHeight());
                    sidebar.revalidate();
                    sidebar.repaint();
                } else {
                    collapseTimer.stop();
                }
            }
        });
        collapseTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}



