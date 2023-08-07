package net.kigawa.kutil.kutil.file;

/**
 * 拡張子の管理のためのクラス
 * to manage file extension
 */
@SuppressWarnings("unused")
public enum FileExtension
{
    LOG("log"),
    /**
     * @deprecated
     */
    @Deprecated
    log("log");

    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    /**
     * 拡張子の取得
     * get extension
     *
     * @return extension
     */
    public String getExtension() {
        return "." + extension;
    }

    /**
     * 拡張子を追加して返す
     * add extension and return
     *
     * @param name file name
     * @return added file name
     */
    public String addExtension(String name) {
        return name + getExtension();
    }

    /**
     * 拡張子を追加する
     * add extension
     *
     * @param stringBuffer file name
     * @return added string buffer
     */
    public StringBuffer addExtension(StringBuffer stringBuffer) {
        return stringBuffer.append(".").append(extension);
    }
}
