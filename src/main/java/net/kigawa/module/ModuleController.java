package net.kigawa.module;

import java.util.HashSet;
import java.util.Set;

public class ModuleController extends Module {
    private Set<Module> moduleSet = new HashSet<>();

    protected ModuleController(String name) {
        super(name);
    }

    public void registerModule(Module module) {
        moduleSet.add(module);
    }

    public void unregisterModule(Module module) {
        moduleSet.remove(module);
    }

    @Override
    void onEnable() {

    }

    @Override
    void onDisable() {

    }
}
