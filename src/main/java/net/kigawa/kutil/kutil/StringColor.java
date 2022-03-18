package net.kigawa.kutil.kutil;

public enum StringColor {
    RESET("\u001b[00m"),
    GREEN("\u001b[00;32m"),
    BLUE("\u001b[00;34m"),
    ;
    private String color;

    StringColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
