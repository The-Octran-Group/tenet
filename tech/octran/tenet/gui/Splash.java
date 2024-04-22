package tech.octran.tenet.gui;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import tech.octran.tenet.components.Info;

public class Splash {

    private JWindow window;
    private JPanel loadingBar;
    private JLabel processLabel;

    public JWindow getFrame() {
        return window;
    }

    public void display() {
        window = new JWindow();
        window.setBounds(0, 0, 500, 300);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setAlwaysOnTop(false);

        window.getContentPane().setBackground(Info.getThemeColor(Info.THEME));

        JLabel logoLabel = new JLabel();
        logoLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 40));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setForeground(Info.getThemeColor(Info.FONT_PRIORITY));
        logoLabel.setIcon(Info.getImage("public\\logo.png", 125, 125));
        logoLabel.setBounds((window.getWidth()-125)/2, 50, 125, 125);
        window.getContentPane().add(logoLabel, SwingConstants.CENTER);

        JLabel affiliateLabel = new JLabel();
        affiliateLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 40));
        affiliateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        affiliateLabel.setForeground(Info.getThemeColor(Info.FONT_PRIORITY));
        if (Info.getFullVersion().contains("DEV"))
            affiliateLabel.setIcon(Info.getImage("public\\dev.png", 60, 30));
        else if (Info.getFullVersion().contains("BETA"))
            affiliateLabel.setIcon(Info.getImage("public\\beta.png", 60, 30));
        affiliateLabel.setBounds(window.getWidth()-80, 20, 60, 30);
        window.getContentPane().add(affiliateLabel, SwingConstants.CENTER);

        JLabel versionLabel = new JLabel(Info.getFullVersion());
        versionLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        versionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        versionLabel.setForeground(Info.getThemeColor(Info.FONT));
        versionLabel.setBounds((window.getWidth()/2) - 200, 185, 400, 20);

        processLabel = new JLabel("");
        processLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        processLabel.setHorizontalAlignment(SwingConstants.LEFT);
        processLabel.setVerticalAlignment(SwingConstants.CENTER);
        processLabel.setForeground(Info.getThemeColor(Info.FONT));
        processLabel.setBounds(5, 270, 490, 20);

        loadingBar = new JPanel();
        loadingBar.setBackground(Info.getThemeColor(Info.FONT));
        loadingBar.setBounds(0, 290, 15, 10);

        window.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        window.getContentPane().add(versionLabel, SwingConstants.CENTER);
        window.getContentPane().add(processLabel, SwingConstants.CENTER);
        window.getContentPane().add(loadingBar, SwingConstants.CENTER);

        window.revalidate();
        window.repaint();
    }

    public void setProcess(String process, int x) {
        processLabel.setText(process);
        loadingBar.setBounds(0, 290, (int)((double)(x/100.0)*500), 10);
    }

    public void holdup(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        window.setVisible(false);
    }

}