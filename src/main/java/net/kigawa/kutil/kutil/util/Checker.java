package net.kigawa.kutil.kutil.util;

import net.kigawa.kutil.kutil.interfaces.HasContain;

/**
 * @deprecated
 */
public class Checker {

    private final String str;
    private final Type type;

    public Checker(String str, Type type) {
        this.str = str;
        this.type = type;
    }

    public boolean equals(Object o) {
        if (o instanceof HasContain)
            return ((HasContain) o).contain(this);
        return super.equals(o);
    }

    public Type getType(){
        return type;
    }

    public String getStr() {
        return str;
    }

    public enum Type {
        CONTAIN_STRING
    }
}
