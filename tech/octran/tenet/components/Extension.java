package tech.octran.tenet.components;

public class Extension {

    private String NAME;
    private String VERSION;

    public Extension(String NAME, String VERSION) {
        this.NAME = NAME;
        this.VERSION = VERSION;
    }

    public String getName() {
        return NAME;
    }

    public String getVersion() {
        return VERSION;
    }

}
