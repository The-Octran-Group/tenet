package tech.octran.tenet.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import tech.octran.tenet.data.Info;

public class MainWindow {

    private final JFrame frame;

    private int currentAction; // current tab opened

    private  final JPanel fileActionPanel;
    private  final JLabel fileActionLabel;

    private  final JPanel settingsActionPanel;
    private  final JLabel settingsActionLabel;

    // constructor
    public MainWindow() {
        frame = new JFrame();
        frame.setTitle(Info.getName() + " - " + Info.getFullVersion());
        frame.setBounds(0, 0, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setIconImage(Info.getImage("public\\logo_strip.png", 1024, 1024).getImage());
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });
        frame.getContentPane().setBackground(Info.getThemeColor(Info.THEME));
        frame.setMinimumSize(new Dimension(1000, 600));
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel actionPanel = new JPanel();
        actionPanel.setPreferredSize(new Dimension(50, frame.getHeight()));
        actionPanel.setBackground(Info.getThemeColor(Info.THEME));
        actionPanel.setBorder(null);
        actionPanel.setLayout(null);
        frame.getContentPane().add(actionPanel, BorderLayout.WEST);

        fileActionPanel = new JPanel();
        fileActionPanel.setBounds(0, 0, 50, 50);
        fileActionPanel.setBackground(Info.getThemeColor(Info.THEME));
        fileActionPanel.setLayout(null);
        fileActionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileActionPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentAction = 0;
                validateActionFocus();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                fileActionPanel.setBackground(Info.getThemeColor(Info.THEME_HOVER));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(currentAction != 0)
                    fileActionPanel.setBackground(Info.getThemeColor(Info.THEME));
            }
        });
        actionPanel.add(fileActionPanel);

        fileActionLabel = new JLabel();
        fileActionLabel.setBounds(12, 12, 26, 26);
        fileActionLabel.setIcon(Info.getImage("public\\file.png", 26, 26));
        fileActionPanel.add(fileActionLabel);

        settingsActionPanel = new JPanel();
        settingsActionPanel.setBounds(0, 50, 50, 50);
        settingsActionPanel.setBackground(Info.getThemeColor(Info.THEME));
        settingsActionPanel.setLayout(null);
        settingsActionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingsActionPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentAction = 1;
                validateActionFocus();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                settingsActionPanel.setBackground(Info.getThemeColor(Info.THEME_HOVER));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(currentAction != 1)
                    settingsActionPanel.setBackground(Info.getThemeColor(Info.THEME));
            }
        });
        actionPanel.add(settingsActionPanel);

        settingsActionLabel = new JLabel();
        settingsActionLabel.setBounds(12, 12, 26, 26);
        settingsActionLabel.setIcon(Info.getImage("public\\settings.png", 26, 26));
        settingsActionPanel.add(settingsActionLabel);

        JPanel workingPanel = new JPanel();
        workingPanel.setPreferredSize(new Dimension(frame.getWidth() - actionPanel.getWidth(), frame.getHeight()));
        workingPanel.setBackground(Info.getThemeColor(Info.THEME_PLUS));
        workingPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(workingPanel, BorderLayout.CENTER);

        JPanel filesPanel = new JPanel();
        filesPanel.setPreferredSize(new Dimension(workingPanel.getWidth(), workingPanel.getHeight()));
        filesPanel.setBackground(Info.getThemeColor(Info.THEME));
        workingPanel.add(filesPanel, BorderLayout.CENTER);
    }

    // display frame
    public void display() {
        currentAction = 0;
        validateActionFocus();
        frame.repaint();
        frame.revalidate();
        frame.setVisible(true);
    }

    // validate hover display
    public void validateActionFocus() {
        if (currentAction == 0) {
            fileActionPanel.setBackground(Info.getThemeColor(Info.THEME_HOVER));
            fileActionLabel.setIcon(Info.getImage("public\\file_selected.png", 26, 26));
            settingsActionPanel.setBackground(Info.getThemeColor(Info.THEME));
            settingsActionLabel.setIcon(Info.getImage("public\\settings.png", 26, 26));
        } else if (currentAction == 1) {
            settingsActionPanel.setBackground(Info.getThemeColor(Info.THEME_HOVER));
            settingsActionLabel.setIcon(Info.getImage("public\\settings_selected.png", 26, 26));
            fileActionPanel.setBackground(Info.getThemeColor(Info.THEME));
            fileActionLabel.setIcon(Info.getImage("public\\file.png", 26, 26));
        }
        frame.repaint();
        frame.revalidate();
    }

}