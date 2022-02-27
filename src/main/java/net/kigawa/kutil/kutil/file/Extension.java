package net.kigawa.kutil.kutil.file;

public enum Extension {
    log;

    Extension() {
    }

    public String getExtension() {
        return "." + this;
    }

    public String addExtension(String name) {
        return name + getExtension();
    }

    public StringBuffer addExtension(StringBuffer stringBuffer) {
        return stringBuffer.append(".").append(this);
    }
}
