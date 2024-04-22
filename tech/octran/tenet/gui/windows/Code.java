package tech.octran.tenet.gui.windows;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import tech.octran.tenet.components.Info;

public class Code extends JPanel {

    private JTabbedPane tabbedPane;

    public Code() {
        setBackground(Info.getThemeColor(Info.THEME));
        setLayout(new BorderLayout(20, 20));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Info.getThemeColor(Info.THEME_PLUS));
    }

}
