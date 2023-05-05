package net.kigawa.kutil.kutil.list;

import java.util.*;

/**
 * @param <T> class
 * @deprecated use BlockingQueue
 * list that can get first and remove it
 */
public class Stocker<T> implements Iterator<T>
{
    private Container<T> start;
    private Container<T> end;

    /**
     * @param obj add obj to list
     */
    public synchronized void add(T obj)
    {
        Container<T> container = new Container<>(obj);
        if (end != null) end.setContainer(container);
        else start = container;
        end = new Container<>(obj);
    }

    /**
     * @return return true when has next
     */
    @Override
    public boolean hasNext()
    {
        return start != null;
    }

    /**
     * @return get first and remove it
     */
    @Override
    public synchronized T next()
    {
        Container<T> container = start;
        start = start.getContainer();
        if (start == null) end = null;
        return container.getTask();
    }
}

class Container<T>
{
    private final T obj;
    private Container<T> container;

    protected Container(T obj)
    {
        this.obj = obj;
    }

    public Container<T> getContainer()
    {
        return container;
    }

    public void setContainer(Container<T> container)
    {
        this.container = container;
    }

    public T getTask()
    {
        return obj;
    }
}