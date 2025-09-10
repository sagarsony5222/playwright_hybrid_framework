package config;

public class EnvironmentConfig {

    public enum Environment {
        DEV, QA, STAGING, PROD
    }

    private static final String ENV_VAR = "env";

    public static Environment getCurrentEnvironment() {
        String env = System.getProperty(ENV_VAR, "QA").toUpperCase();
        try {
            return Environment.valueOf(env);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid environment: " + env);
        }
    }

    public static String getBaseUrl() {
        Environment env = getCurrentEnvironment();
        return switch (env) {
            case DEV -> "https://dev.example.com";
            case QA -> "https://qa.example.com";
            case STAGING -> "https://staging.example.com";
            case PROD -> "https://www.example.com";
            default -> throw new IllegalStateException("Unexpected environment: " + env);
        };
    }

    public static String getApiBaseUrl() {
        Environment env = getCurrentEnvironment();
        return switch (env) {
            case DEV -> "https://api.dev.example.com";
            case QA -> "https://api.qa.example.com";
            case STAGING -> "https://api.staging.example.com";
            case PROD -> "https://api.example.com";
            default -> throw new IllegalStateException("Unexpected environment: " + env);
        };
    }
}
