package enums;

public enum Environment {
    DEV,
    QA,
    STAGING,
    PROD;

    public static Environment from(String name) {
        for (Environment e : values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unsupported environment: " + name);
    }

    public boolean isProd() {
        return this == PROD;
    }
}
