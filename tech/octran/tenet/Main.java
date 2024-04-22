package tech.octran.tenet;

import javax.swing.JOptionPane;

import tech.octran.tenet.components.Config;
import tech.octran.tenet.components.Info;
import tech.octran.tenet.gui.MainWindow;
import tech.octran.tenet.gui.Splash;

public class Main {

    public static void main(String[] args) {
        // preliminary tasks
        Info.setUILookAndFeel();

        // splash screen
        setLoadingSplash();

        // main screen
        displayMainScreen();
    }

    public static void setLoadingSplash() {
        Splash splashScreen = new Splash();
        splashScreen.display();
        splashScreen.setProcess("Loading... Please be patient...", 1);
        splashScreen.holdup(1000);
        splashScreen.setProcess("Loading your settings...", 5);
        try {
            Config.setSettingsWindows();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(splashScreen.getFrame(), "An error occured: " + e.getMessage() + "\nWe will try to write an error log about this. Check the appdata folder.", "Fatal Error Occured!", JOptionPane.ERROR_MESSAGE);
            Config.logError(e);
            splashScreen.stop();
            System.exit(0);
        }
        splashScreen.setProcess("Checking your API keys...", 10);
        try {
            Config.setAPIKeys();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(splashScreen.getFrame(), "An error occured: " + e.getMessage() + "\nWe will try to write an error log about this. Check the appdata folder.", "Fatal Error Occured!", JOptionPane.ERROR_MESSAGE);
            Config.logError(e);
            splashScreen.stop();
            System.exit(0);
        }
        splashScreen.setProcess("Retrieving LLM Models...", 12);
        try {
            Config.setLLMs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(splashScreen.getFrame(), "An error occured: " + e.getMessage() + "\nWe will try to write an error log about this. Check the appdata folder.", "Fatal Error Occured!", JOptionPane.ERROR_MESSAGE);
            Config.logError(e);
            splashScreen.stop();
            System.exit(0);
        }
        splashScreen.setProcess("Loading your Projects...", 17);
        try {
            Config.setProjects();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(splashScreen.getFrame(), "An error occured: " + e.getMessage() + "\nWe will try to write an error log about this. Check the appdata folder.", "Fatal Error Occured!", JOptionPane.ERROR_MESSAGE);
            Config.logError(e);
            splashScreen.stop();
            System.exit(0);
        }
        splashScreen.setProcess("Done! Opening Editor...", 97);
        splashScreen.holdup(1000);
        splashScreen.stop();
    }

    public static void displayMainScreen() {
        MainWindow mainWindow = new MainWindow();
        mainWindow.display();
    }

}