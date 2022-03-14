package net.kigawa.kutil.kutil;

public class KutilArray {
    public static <T> boolean contain(T[] array, Object obj) {
        for (var obj1 : array) if (obj.equals(obj1)) return true;
        return false;
    }
}
