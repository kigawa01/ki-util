package net.kigawa.kutil.kutil.list;

import java.util.Iterator;

public class Stocker<T> implements Iterator<T> {
    private Container<T> start;
    private Container<T> end;

    public synchronized void add(T obj) {
        Container<T> container = new Container<>(obj);
        if (end != null) end.setContainer(container);
        else start = container;
    }

    @Override
    public boolean hasNext() {
        return start != null;
    }

    @Override
    public synchronized T next() {
        Container<T> container = start;
        start = start.getContainer();
        return container.getTask();
    }
}

class Container<T> {
    private final T obj;
    private Container<T> container;

    protected Container(T obj) {
        this.obj = obj;
    }

    public Container<T> getContainer() {
        return container;
    }

    public void setContainer(Container<T> container) {
        this.container = container;
    }

    public T getTask() {
        return obj;
    }
}