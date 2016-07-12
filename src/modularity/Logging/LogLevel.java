package modularity.Logging;

public enum LogLevel {
    NONE(-1),
    ERROR(0),
    INFO(1),
    DEBUG(2),
    ALL(3);

    final int value;

    LogLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}