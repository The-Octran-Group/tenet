package tech.octran.tenet.data;

import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Info {

    private static final Color[] DARK_THEME = {
            new Color(5, 5, 5), // Dark +
            new Color(25, 25, 25), // Dark
            new Color(45, 45, 45), // Dark :: Hover
            null, // Special Color (initialized later)
            new Color(245, 245, 245), // Font Priority
            new Color(225, 225, 225), // Font
            new Color(135, 135, 135), // Font Minor
            new Color(232, 86, 86), // Font Keyword
            new Color(5, 117, 173), // Font Number
            new Color(207, 127, 8), // Font Special Symbols
            new Color(27, 198, 128), // Font Strings
    };
    private static final Color[] LIGHT_THEME = {
            new Color(215, 215, 215), // Light +
            new Color(225, 225, 225), // Light
            new Color(245, 245, 245), // Light :: Hover
            null, // Special Color (initialized later)
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

    private static final String theme = "Dark";
    private static final String name = "Tenet AI";
    private static final String version = "0.0.1";
    private static final String fullVersion = "v0.0.1 DEV";

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat();

    static {
        initializeSpecialColor();
    }

    private static void initializeSpecialColor() {
        if (fullVersion.contains("DEV")) {
            DARK_THEME[SPECIAL] = new Color(112, 48, 160);
            LIGHT_THEME[SPECIAL] = new Color(112, 48, 160);
        } else if (fullVersion.contains("BETA")) {
            DARK_THEME[SPECIAL] = new Color(84, 130, 53);
            LIGHT_THEME[SPECIAL] = new Color(84, 130, 53);
        } else {
            DARK_THEME[SPECIAL] = theme.equals("Dark") ? Color.WHITE : Color.BLACK;
            LIGHT_THEME[SPECIAL] = theme.equals("Dark") ? Color.BLACK : Color.WHITE;
        }
    }

    public static String getName() {
        return name;
    }

    public static String getUser() {
        return System.getProperty("user.name");
    }

    public static String getVersion() {
        return version;
    }

    public static String getFullVersion() {
        return fullVersion;
    }

    public static String getTime(String pattern) {
        FORMAT.applyPattern(pattern);
        return FORMAT.format(new Date());
    }

    public static ImageIcon getImage(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static void setUILookAndFeel() {
        try {
            if (theme.contains("Dark"))
                UIManager.setLookAndFeel(new FlatDarkLaf());
            else if (theme.contains("Light"))
                UIManager.setLookAndFeel(new FlatLightLaf());

            // Frame
            UIManager.put("RootPane.background", getThemeColor(THEME));
            UIManager.put("TitlePane.centerTitle", true);

            // Button
            UIManager.put("Button.background", getThemeColor(THEME_PLUS));
            UIManager.put("Button.hoverBackground", getThemeColor(THEME));

            // Text Field
            UIManager.put("TextField.background", getThemeColor(THEME_PLUS));
            UIManager.put("TextField.focusedBackground", getThemeColor(THEME));

            // Scroll Bar
            UIManager.put("ScrollBar.track", getThemeColor(THEME));
            UIManager.put("ScrollBar.hoverTrackColor", getThemeColor(THEME_HOVER));
            UIManager.put("ScrollBar.pressedTrackColor", getThemeColor(THEME_HOVER));
            UIManager.put("ScrollBar.thumb", getThemeColor(THEME));
            UIManager.put("ScrollBar.hoverThumbColor", getThemeColor(THEME_HOVER));
            UIManager.put("ScrollBar.pressedThumbColor", getThemeColor(THEME_HOVER));
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Exception Occurred!", JOptionPane.ERROR_MESSAGE);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
    }

    public static Color getThemeColor(int shade) {
        return theme.equals("Dark") ? DARK_THEME[shade] : LIGHT_THEME[shade];
    }
}