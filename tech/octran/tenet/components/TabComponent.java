package tech.octran.tenet.components;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class TabComponent {

    private final File file;
    private final JLabel label;
    private final RSyntaxTextArea textArea;

    public TabComponent(File file, JLabel label, RSyntaxTextArea textPane) {
        this.file = file;
        this.label = label;
        this.textArea = textPane;
    }

    public File getFile() {
        return file;
    }

    public JLabel getLabel() {
        return label;
    }

    public RSyntaxTextArea getTextArea() {
        return textArea;
    }

}
