package exceptions;

public class ElementNotFoundException extends FrameworkException {
    private final String selector;
    private final long timeoutMs;

    public ElementNotFoundException(String selector, long timeoutMs) {
        super("ELEMENT_NOT_FOUND", String.format("Element not found: '%s' within %d ms", selector, timeoutMs));
        this.selector = selector;
        this.timeoutMs = timeoutMs;
    }

    public ElementNotFoundException(String selector, long timeoutMs, Throwable cause) {
        super("ELEMENT_NOT_FOUND", String.format("Element not found: '%s' within %d ms", selector, timeoutMs), cause);
        this.selector = selector;
        this.timeoutMs = timeoutMs;
    }

    public String getSelector() {
        return selector;
    }

    public long getTimeoutMs() {
        return timeoutMs;
    }
}
