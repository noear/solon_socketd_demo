package demo01.xcanal.base.config;

public enum ConfigLabel {
    NORMAL("NORMAL"),
    FILTER("FILTER"),
    MAPPING("MAPPING"),
    PERFORMANCE("PERFORMANCE");

    public final String name;
    ConfigLabel(String name) {
        this.name = name;
    }
}