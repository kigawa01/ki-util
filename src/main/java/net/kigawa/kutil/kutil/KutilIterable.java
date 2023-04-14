package net.kigawa.kutil.kutil;

import java.util.*;
import java.util.stream.*;

/**
 * utilities about array
 */
public class KutilIterable
{

    /**
     * test obj contain in array
     *
     * @param array array to test
     * @param obj   obj to test
     * @param <S>   source class type
     * @param <T>   test class type
     * @return return true when contain
     */
    public static <S, T extends S> boolean contain(Iterable<S> array, T obj)
    {
        return array.contains(obj);
    }
    public static < T > List<T> toList(Iterable<T> iterable)
    {
        return ;
    }
    /**
     * creat set from array
     *
     * @param ts  base array
     * @param <T> class type
     * @return created set
     */
    public static <T> Set<T> toSet(T[] ts)
    {
        return Arrays.stream(ts).collect(Collectors.toSet());
    }

}
