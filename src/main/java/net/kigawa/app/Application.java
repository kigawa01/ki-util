package net.kigawa.app;

import net.kigawa.interfaces.Module;
import net.kigawa.log.LogSender;

import java.util.LinkedList;

public abstract class Application implements Module, LogSender {
    private final LinkedList<Module> moduleList = new LinkedList<>();
    private boolean run = false;

    public void enableAddModule(Module module) {
        addModule(module);
        enableModule(module);
    }

    public void enableModule(Module module) {
        try {
            module.enable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disableModule(Module module) {
        try {
            module.disable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addModule(Module module) {
        moduleList.add(module);
    }

    public boolean isRun() {
        return run;
    }

    public void enable() {
        run = true;
        moduleList.forEach(this::enableModule);
        onEnable();
    }

    public void disable() {
        try {
            onDisable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = moduleList.size() - 1; i >= 0; i--) {
            disableModule(moduleList.get(i));
        }
        run = false;
    }

    protected abstract void onEnable();

    protected abstract void onDisable();
}
