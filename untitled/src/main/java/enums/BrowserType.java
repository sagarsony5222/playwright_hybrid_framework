package enums;

public enum BrowserType {
    CHROMIUM("chromium"),
    FIREFOX("firefox"),
    WEBKIT("webkit"),
    CHROME("chrome"),   // chromium with Chrome channel
    EDGE("edge");       // chromium with Edge channel

    private final String value;

    BrowserType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static BrowserType from(String name) {
        for (BrowserType bt : values()) {
            if (bt.name().equalsIgnoreCase(name) || bt.value.equalsIgnoreCase(name)) {
                return bt;
            }
        }
        throw new IllegalArgumentException("Unsupported browser type: " + name);
    }
}
