package net.kigawa.util;

import java.util.Iterator;

public class Stocker<T> extends Syncer implements Iterator<T> {
    private Container<T> start;
    private Container<T> end;

    public void add(T obj) {
        startSync();
        Container<T> container = new Container<>(obj);
        if (end != null) end.setContainer(container);
        else start = container;
        end = container;
        endSync();
    }

    @Override
    public boolean hasNext() {
        return start != null;
    }

    @Override
    public synchronized T next() {
        startSync();
        Container<T> container = start;
        start = start.getContainer();
        endSync();
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