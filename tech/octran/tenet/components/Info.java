package tech.octran.tenet.components;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import java.awt.Image;

public class Info {

    private static Color[] darkTheme = {
            new Color(5, 5, 5), // Dark +
            new Color(25, 25, 25), // Dark
            new Color(45, 45, 45), // Dark :: Hover
            new Color(0, 0, 0), // Special Color
            new Color(245, 245, 245), // Font Priority
            new Color(225, 225, 225), // Font
            new Color(135, 135, 135), // Font Minor
            new Color(232, 86, 86), // Font Keyword
            new Color(5, 117, 173), // Font Number
            new Color(207, 127, 8), // Font Special Symbols
            new Color(27, 198, 128), // Font Strings
    };
    private static Color[] lightTheme = {
            new Color(215, 215, 215), // Light +
            new Color(225, 225, 225), // Light
            new Color(245, 245, 245), // Light :: Hover
            new Color(0, 0, 0), // Special Color
            new Color(35, 35, 35), // Font Priority
            new Color(55, 55, 55), // Font
            new Color(195, 195, 195), // Font Minor
            new Color(232, 86, 86), // Font Keyword
            new Color(5, 117, 173), // Font Number
            new Color(153, 92, 2), // Font Annotation
            new Color(19, 156, 100), // Font Strings
    };

    public static final int THEME_PLUS = 0;
    public static final int THEME = 1;
    public static final int THEME_HOVER = 2;
    public static final int SPECIAL = 3;
    public static final int FONT_PRIORITY = 4;
    public static final int FONT = 5;
    public static final int FONT_MINOR = 6;

    private static String theme = "Dark";

    public static String getName() {
        return "Tenet AI";
    }

    public static String getUser() {
        return System.getProperty("user.name");
    }

    public static String getVersion() {
        return "0.0.1";
    }

    public static String getFullVersion() {
        return "v0.0.1 DEV";
    }

    public static String getTime(String str) {
        SimpleDateFormat format = new SimpleDateFormat(str);
        Date date = new Date();
        return format.format(date);
    }

    public static ImageIcon getImage(String path, int w, int h) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    public static void setUILookAndFeel() {
        // Set UI Look and Feel
        try {
            if (Info.theme.contains("Dark"))
                UIManager.setLookAndFeel(new FlatDarkLaf());
            else if (Info.theme.contains("Light"))
                UIManager.setLookAndFeel(new FlatLightLaf());

            //Frame
            UIManager.put("RootPane.background", Info.getThemeColor(Info.THEME));
            UIManager.put("TitlePane.centerTitle", true);

            //Button
            UIManager.put("Button.background", Info.getThemeColor(Info.THEME_PLUS));
            UIManager.put("Button.hoverBackground", Info.getThemeColor(Info.THEME));

            //Text Field
            UIManager.put("TextField.background", Info.getThemeColor(Info.THEME_PLUS));
            UIManager.put("TextField.focusedBackground", Info.getThemeColor(Info.THEME));

            //Scroll Bar
            UIManager.put("ScrollBar.track", Info.getThemeColor(Info.THEME));
            UIManager.put("ScrollBar.hoverTrackColor", Info.getThemeColor(Info.THEME_HOVER));
            UIManager.put("ScrollBar.pressedTrackColor", Info.getThemeColor(Info.THEME_HOVER));
            UIManager.put("ScrollBar.thumb", Info.getThemeColor(Info.THEME));
            UIManager.put("ScrollBar.hoverThumbColor", Info.getThemeColor(Info.THEME_HOVER));
            UIManager.put("ScrollBar.pressedThumbColor", Info.getThemeColor(Info.THEME_HOVER));

            if (Info.getFullVersion().contains("DEV")) {
                darkTheme[3] = new Color(112, 48, 160);
                lightTheme[3] = new Color(112, 48, 160);
            }
            else {
                darkTheme[3] = new Color(225, 225, 225);
                lightTheme[3] = new Color(0, 0, 0);
            }
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Exception Occured!", JOptionPane.ERROR_MESSAGE);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
    }

    public static Color getThemeColor(int shade) {
        if (theme.equals("Dark"))
            return darkTheme[shade];
        else if (theme.equals("Light"))
            return lightTheme[shade];
        return null;
    }

}