package net.kigawa.app;

import net.kigawa.interfaces.Module;
import net.kigawa.log.LogSender;

import java.util.LinkedList;

public interface Application extends Module, LogSender {
    LinkedList<Module> moduleList = new LinkedList<>();

    default void enableAddModule(Module module) {
        addModule(module);
        enableModule(module);
    }

    default void enableModule(Module module) {
        try {
            module.enable();
        } catch (Exception e) {
            warning(e);
        }
    }

    default void disableModule(Module module) {
        try {
            module.disable();
        } catch (Exception e) {
            warning(e);
        }
    }

    default void addModule(Module module) {
        moduleList.add(module);
    }

    default void enable() {
        moduleList.forEach(this::enableModule);
        onEnable();
    }

    default void disable() {
        try {
            onDisable();
        } catch (Exception e) {
            warning(e);
        }
        moduleList.forEach(this::disableModule);
    }

    void onEnable();

    void onDisable();
}
