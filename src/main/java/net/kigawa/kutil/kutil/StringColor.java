package net.kigawa.kutil.kutil;

/**
 * to use colors
 */
public enum StringColor
{
    RESET("\u001b[00m"),
    GREEN("\u001b[00;32m"),
    BLUE("\u001b[00;34m"),
    ;
    private final String color;

    StringColor(String color)
    {
        this.color = color;
    }

    /**
     * get color str
     *
     * @return str
     */
    @Override
    public String toString()
    {
        return color;
    }
}
