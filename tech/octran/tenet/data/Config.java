package tech.octran.tenet.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tech.octran.tenet.components.Extension;

public class Config {

    private static final String APP_DATA_DIR = System.getenv("APPDATA") + "\\Tenet";
    private static final String SETTINGS_FILE_PATH = APP_DATA_DIR + "\\settings.json";
    private static final String API_FILE_PATH = APP_DATA_DIR + "\\api.json";
    private static final String PROJECTS_FILE_PATH = APP_DATA_DIR + "\\projects.json";
    private static final String OPENAI_MODELS_FILE_PATH = "llm\\openai\\models.json";
    private static final String ANTHROPIC_MODELS_FILE_PATH = "llm\\anthropic\\models.json";

    // AI settings
    public static boolean stopEveryTime;
    public static boolean routeToBestLLM;
    public static String defaultRoute;

    // Editor settings
    public static boolean allowProgramTimeout;
    public static int programTimeout;
    public static int tabSize;
    public static boolean animations;

    // API keys
    public static String OPENAI_KEY;
    public static String ANTHROPIC_KEY;

    // Immediately fetched
    public static JSONArray OPENAI_MODELS;
    public static Map<String, JSONObject> OPENAI_MODEL_MAP = new HashMap<>();
    public static JSONArray ANTHROPIC_MODELS;
    public static Map<String, JSONObject> ANTHROPIC_MODEL_MAP = new HashMap<>();

    // Projects
    public static JSONArray PROJECTS;

    // Extensions
    private static final ArrayList<Extension> extensions = new ArrayList<Extension>();

    public static void loadSettings() throws IOException, ParseException {
        JSONObject settingsJson = readJsonFile(SETTINGS_FILE_PATH);
        stopEveryTime = getBooleanValue(settingsJson, "stopEveryTime", false);
        routeToBestLLM = getBooleanValue(settingsJson, "routeToBestLLM", true);
        defaultRoute = getStringValue(settingsJson, "defaultRoute", null);
        allowProgramTimeout = getBooleanValue(settingsJson, "allowProgramTimeout", true);
        programTimeout = getIntValue(settingsJson, "programTimeout", 60);
        tabSize = getIntValue(settingsJson, "tabSize", 4);
        animations = getBooleanValue(settingsJson, "animations", true);
    }

    public static void loadAPIKeys() throws IOException, ParseException {
        JSONObject apiJson = readJsonFile(API_FILE_PATH);
        OPENAI_KEY = getStringValue(apiJson, "OPENAI_KEY", null);
        ANTHROPIC_KEY = getStringValue(apiJson, "ANTHROPIC_KEY", null);
    }

    public static void loadLLMs() throws IOException, ParseException {
        OPENAI_MODELS = readJsonArray(OPENAI_MODELS_FILE_PATH);
        for (Object obj : OPENAI_MODELS) {
            JSONObject model = (JSONObject) obj;
            OPENAI_MODEL_MAP.put((String) model.get("model"), model);
        }

        ANTHROPIC_MODELS = readJsonArray(ANTHROPIC_MODELS_FILE_PATH);
        for (Object obj : ANTHROPIC_MODELS) {
            JSONObject model = (JSONObject) obj;
            ANTHROPIC_MODEL_MAP.put((String) model.get("model"), model);
        }
    }

    public static void loadProjects() throws IOException, ParseException {
        PROJECTS = readJsonArray(PROJECTS_FILE_PATH);
    }

    private static JSONObject readJsonFile(String filePath) throws IOException, ParseException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            return (JSONObject) parser.parse(reader);
        }
    }

    private static JSONArray readJsonArray(String filePath) throws IOException, ParseException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new JSONArray();
        }
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            return (JSONArray) parser.parse(reader);
        }
    }

    private static boolean getBooleanValue(JSONObject json, String key, boolean defaultValue) {
        return json.containsKey(key) ? (boolean) json.get(key) : defaultValue;
    }

    private static int getIntValue(JSONObject json, String key, int defaultValue) {
        return json.containsKey(key) ? ((Long) json.get(key)).intValue() : defaultValue;
    }

    private static String getStringValue(JSONObject json, String key, String defaultValue) {
        return json.containsKey(key) ? (String) json.get(key) : defaultValue;
    }

    public static void logError(Exception error) {
        try {
            File appDir = new File(APP_DATA_DIR);
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            File errorDir = new File(appDir.getAbsolutePath() + "\\errors");
            if (!errorDir.exists()) {
                errorDir.mkdirs();
            }
            String time = Info.getTime("yyyy-MM-dd HH-mm-ss");
            File errorLog = new File(errorDir + "\\" + time + ".txt");
            if (!errorLog.exists()) {
                errorLog.createNewFile();
            }
            try (FileWriter writer = new FileWriter(errorLog)) {
                writer.write(error.getMessage() + " - " + error.hashCode() + "\n\n" + error.getStackTrace());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addExtension(Extension extension) {
        if(!extensions.contains(extension)) {
            extensions.add(extension);
        }
    }

    public static void removeExtension(Extension extension) {
        extensions.remove(extension);
    }
}