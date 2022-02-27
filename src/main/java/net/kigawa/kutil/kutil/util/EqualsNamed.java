package net.kigawa.kutil.kutil.util;

import net.kigawa.kutil.kutil.interfaces.Named;

/**
 * @deprecated
 */
public class EqualsNamed {
    String name;

    public EqualsNamed(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (o!=null) {
            if (o instanceof Named)
                return ((Named) o).getName().equals(name);
            return super.equals(o);
        }
        return false;
    }
}
