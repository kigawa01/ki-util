package net.kigawa.kutil.kutil;

/**
 * utilities about array
 */
public class KutilArray {

    /**
     * test obj contain in array
     *
     * @param array array to test
     * @param obj   obj to test
     * @param <T>   class type
     * @return return true when contain
     */
    public static <T> boolean contain(T[] array, T obj) {
        for (var obj1 : array) if (obj.equals(obj1)) return true;
        return false;
    }
}
