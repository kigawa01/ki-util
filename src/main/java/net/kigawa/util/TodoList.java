package net.kigawa.util;

import java.util.Iterator;

public class TodoList implements Iterator<Task> {
    private Container start;
    private Container end;

    public synchronized void add(Task task) {
        Container container = new Container(task);
        if (end != null) end.setContainer(container);
        else start = container;
        end = container;
    }

    @Override
    public boolean hasNext() {
        return start != null;
    }

    @Override
    public Task next() {
        Container container=start;
        start=start.getContainer();
        return container.getTask();
    }
}

class Container {
    private final Task task;
    private Container container;

    protected Container(Task task) {
        this.task = task;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    public Task getTask() {
        return task;
    }
}