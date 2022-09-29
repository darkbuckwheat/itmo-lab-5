package Managers;

public enum OutputColor {
    RED("\u001B[31m"),
    GREEN("\u001B[32m");

    private String code;

    OutputColor(String code) {
        this.code = code;
    }

    public String toString() {
        return code;
    }
}
