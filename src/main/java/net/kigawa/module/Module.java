package net.kigawa.module;

import net.kigawa.interfaces.Named;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Module implements Named {
    private final Map<ModuleController, Boolean> controllerMap = new LinkedHashMap<>();
    private final String name;
    private boolean enable = false;

    protected Module(String name) {
        this.name = name;
    }

    abstract void onEnable();

    abstract void onDisable();

    @Override
    public String getName() {
        return name;
    }

    synchronized void enable(ModuleController controller) {
        controllerMap.put(controller, true);
        if (enable) return;
        enable = true;
        onEnable();
    }

    void disable(ModuleController controller) {
        if (!enable) return;
        controllerMap.put(controller, false);
        if (controllerMap.containsValue(true)) return;
        onDisable();
        enable = false;
    }

    void unregister(ModuleController controller) {
        disable(controller);
        controllerMap.remove(controller);
    }
}
