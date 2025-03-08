import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test extends JFrame {
    private JPanel sidebar;
    private Timer expandTimer;
    private Timer collapseTimer;
    private int sidebarWidth = 100;
    private final int expandedWidth = 200;
    private final int collapsedWidth = 100;
    private final int animationStep = 10;

    private JLabel txtHome;
    private JLabel txtRecords;

    public test() {
        formatFrame();
    }

    public void formatFrame() {
        setTitle("TeRMS");
        setSize(1000, 600);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Sidebar Panel
        sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(Color.GRAY);
        sidebar.setBounds(0, 0, sidebarWidth, getHeight());
        add(sidebar);

        // image
        ImageIcon homeIcon = new ImageIcon("C:\\Users\\Baby Rose R. Garcia\\Documents\\New folder\\VS_SHID-main\\Terms\\src\\img\\Home.jpg");
        ImageIcon records = new ImageIcon("C:\\Users\\Baby Rose R. Garcia\\Documents\\New folder\\VS_SHID-main\\Terms\\src\\img\\image-50x50 (2).jpg");
        
        // Icon Labels
        JLabel homeIconLabel = new JLabel(homeIcon);
        homeIconLabel.setBounds(10, 150, 50, 50);
        sidebar.add(homeIconLabel);

        JLabel recordsIconLabel = new JLabel(records);
        recordsIconLabel.setBounds(10, 250, 50, 50);
        sidebar.add(recordsIconLabel);

        // Text Labels
        txtHome = new JLabel("Home");
        txtHome.setForeground(Color.WHITE);
        txtHome.setFont(new Font("Arial", Font.BOLD, 14));
        txtHome.setBounds(70, 150, 100, 50); 
        txtHome.setVisible(false);

        txtRecords = new JLabel("Records");
        txtRecords.setForeground(Color.WHITE);
        txtRecords.setFont(new Font("Arial", Font.BOLD, 14));
        txtRecords.setBounds(70, 250, 100, 50); 
        txtRecords.setVisible(false);

        sidebar.add(txtHome);
        sidebar.add(txtRecords);

        // Mouse hover effect
        MouseAdapter hoverEffect = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtHome.setForeground(Color.YELLOW); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtHome.setForeground(Color.WHITE); 
            }
        };

        homeIconLabel.addMouseListener(hoverEffect);
        txtHome.addMouseListener(hoverEffect);

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
                    txtHome.setVisible(true); 
                    txtRecords.setVisible(true); 
                }
            }
        });
        expandTimer.start();
    }

    private void collapseSidebar() {
        txtHome.setVisible(false); 
        txtRecords.setVisible(false); 
        collapseTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sidebarWidth > collapsedWidth) {
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
        SwingUtilities.invokeLater(test::new);
    }
}
