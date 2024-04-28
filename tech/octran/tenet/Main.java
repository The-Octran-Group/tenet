package tech.octran.tenet;

import javax.swing.JOptionPane;

import tech.octran.tenet.data.Config;
import tech.octran.tenet.data.Info;
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
            Config.loadSettings();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(splashScreen.getFrame(), "An error occured: " + e.getMessage() + "\nWe will try to write an error log about this. Check the appdata folder.", "Fatal Error Occured!", JOptionPane.ERROR_MESSAGE);
            Config.logError(e);
            splashScreen.stop();
            System.exit(0);
        }
        splashScreen.setProcess("Checking your API keys...", 10);
        try {
            Config.loadAPIKeys();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(splashScreen.getFrame(), "An error occured: " + e.getMessage() + "\nWe will try to write an error log about this. Check the appdata folder.", "Fatal Error Occured!", JOptionPane.ERROR_MESSAGE);
            Config.logError(e);
            splashScreen.stop();
            System.exit(0);
        }
        splashScreen.setProcess("Retrieving LLM Models...", 12);
        try {
            Config.loadLLMs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(splashScreen.getFrame(), "An error occured: " + e.getMessage() + "\nWe will try to write an error log about this. Check the appdata folder.", "Fatal Error Occured!", JOptionPane.ERROR_MESSAGE);
            Config.logError(e);
            splashScreen.stop();
            System.exit(0);
        }
        splashScreen.setProcess("Loading your Projects...", 17);
        try {
            Config.loadProjects();
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