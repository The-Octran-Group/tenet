package tech.octran.tenet.components;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config {

    // AI settings
    public static boolean stopEveryTime = false;
    public static boolean routeToBestLLM = true;
    public static String defaultRoute = null;

    // Editor settings
    public static boolean allowProgramTimeout = true;
    public static int programTimeout = 60;
    public static int tabSize = 4;
    public static boolean animations = true;

    // API keys
    public static String OPENAI_KEY = null;
    public static String ANTHROPIC_KEY = null;

    // Immediately fetched
    public static JSONArray OPENAI_MODELS = null;
    public static String[] OPENAI_MODEL_NAMES = null;
    public static JSONArray ANTHROPIC_MODELS = null;
    public static String[] ANTHROPIC_MODEL_NAMES = null;

    // Projects
    public static JSONArray PROJECTS = null;

    // log error
    public static void logError(Exception error) {
        try {
            String appDataDir = System.getenv("APPDATA");
            File appDir = new File(appDataDir + "\\Tenet");
            if(!appDir.exists()) {
                appDir.mkdirs();
            }
            File errorDir = new File(appDir.getAbsolutePath() + "\\errors");
            if(!errorDir.exists()) {
                errorDir.mkdirs();
            }
            String time = Info.getTime("yyyy-MM-dd HH-mm-ss");
            File errorLog = new File(errorDir + "\\" + time + ".txt");
            if(!errorLog.exists()) { errorLog.createNewFile(); }
            FileWriter writer = new FileWriter(errorLog);
            writer.write(error.getMessage() + " - " + error.hashCode() + "\n\n" + error.getStackTrace());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // read the JSON settings file
    public static void setSettingsWindows() throws Exception {
        String appDataDir = System.getenv("APPDATA");
        File appDir = new File(appDataDir + "\\Tenet");
        if(!appDir.exists()) {
            appDir.mkdirs();
        }
        File settingsFile = new File(appDir.getAbsolutePath() + "\\settings.json");
        if(!settingsFile.exists()) {
            settingsFile.createNewFile();
            JSONObject settingsObj = new JSONObject();
            settingsObj.put("stopEveryTime", false);
            settingsObj.put("routeToBestLLM", true);
            settingsObj.put("defaultRoute", null);
            settingsObj.put("allowProgramTimeout", true);
            settingsObj.put("programTimeout", 60);
            settingsObj.put("tabSize", 4);
            settingsObj.put("animations", true);
            FileWriter writer = new FileWriter(settingsFile);
            writer.write(settingsObj.toJSONString());
            writer.flush();
            writer.close();
        }
        JSONParser settingsParser = new JSONParser();
        FileReader settingsReader = new FileReader(settingsFile);
        Object obj = settingsParser.parse(settingsReader);
        JSONObject settingsJson = (JSONObject) obj;
        stopEveryTime = (boolean) settingsJson.get("stopEveryTime");
        routeToBestLLM = (boolean) settingsJson.get("routeToBestLLM");
        defaultRoute = (String) settingsJson.get("defaultRoute");
        allowProgramTimeout = (boolean) settingsJson.get("allowProgramTimeout");
        programTimeout = (int) (long) settingsJson.get("programTimeout");
        tabSize = (int) (long) settingsJson.get("tabSize");
        animations = (boolean) settingsJson.get("animations");
        settingsReader.close();
    }

    // read the JSON file for getting API keys
    public static void setAPIKeys() throws Exception {
        String appDataDir = System.getenv("APPDATA");
        File appDir = new File(appDataDir + "\\Tenet");
        if(!appDir.exists()) {
            appDir.mkdirs();
        }
        File apiFile = new File(appDir.getAbsolutePath() + "\\api.json");
        if(!apiFile.exists()) {
            apiFile.createNewFile();
            JSONObject apiObj = new JSONObject();
            apiObj.put("OPENAI_KEY", null);
            apiObj.put("ANTHROPIC_KEY", null);
            FileWriter writer = new FileWriter(apiFile);
            writer.write(apiObj.toJSONString());
            writer.flush();
            writer.close();
        }
        JSONParser apiParser = new JSONParser();
        FileReader apiReader = new FileReader(apiFile);
        Object obj = apiParser.parse(apiReader);
        JSONObject apiJson = (JSONObject) obj;
        OPENAI_KEY = (String) apiJson.get("OPENAI_KEY");
        ANTHROPIC_KEY = (String) apiJson.get("ANTHROPIC_KEY");
        apiReader.close();
    }

    // read all the models of default supported LLMs
    public static void setLLMs() throws Exception {
        File openAiLLMs = new File("llm\\openai\\models.json");
        if(openAiLLMs.exists()) {
            JSONParser openAiParser = new JSONParser();
            FileReader openAiReader = new FileReader(openAiLLMs);
            Object obj = openAiParser.parse(openAiReader);
            OPENAI_MODELS = (JSONArray) obj;
            OPENAI_MODEL_NAMES = new String[OPENAI_MODELS.size()];
            for(int i = 0; i < OPENAI_MODEL_NAMES.length; i++) {
                OPENAI_MODEL_NAMES[i] = (String) ((JSONObject) OPENAI_MODELS.get(i)).get("model");
            }
        }
        File anthropicLLMs = new File("llm\\anthropic\\models.json");
        if(anthropicLLMs.exists()) {
            JSONParser anthropicParser = new JSONParser();
            FileReader anthropicReader = new FileReader(anthropicLLMs);
            Object obj = anthropicParser.parse(anthropicReader);
            ANTHROPIC_MODELS = (JSONArray) obj;
            ANTHROPIC_MODEL_NAMES = new String[ANTHROPIC_MODELS.size()];
            for(int i = 0; i < ANTHROPIC_MODEL_NAMES.length; i++) {
                ANTHROPIC_MODEL_NAMES[i] = (String) ((JSONObject) ANTHROPIC_MODELS.get(i)).get("model");
            }
        }
    }

    // retrieve projects
    public static void setProjects() throws Exception {
        String appDataDir = System.getenv("APPDATA");
        File appDir = new File(appDataDir + "\\Tenet");
        if(!appDir.exists()) {
            appDir.mkdirs();
        }
        File projectsFile = new File(appDir.getAbsolutePath() + "\\projects.json");
        if (!projectsFile.exists()) {
            projectsFile.createNewFile();
            JSONArray projectsJsonArray = new JSONArray();
            FileWriter writer = new FileWriter(projectsFile);
            writer.write(projectsJsonArray.toJSONString());
            writer.flush();
            writer.close();
        }
        JSONParser projectsParser = new JSONParser();
        FileReader projectsReader = new FileReader(projectsFile);
        Object obj = projectsParser.parse(projectsReader);
        PROJECTS = (JSONArray) obj;
    }

}