package net.kigawa.app;

import net.kigawa.interfaces.Module;

import java.util.LinkedList;

public interface Application extends Module {
    LinkedList<Module> moduleList = new LinkedList<>();

    default void enableModule(Module module) {
        module.enable();
        moduleList.add(module);
    }

    default void enable() {
        onEnable();
    }

    default void disable() {
        onDisable();
    }

    void onEnable();

    void onDisable();
}
