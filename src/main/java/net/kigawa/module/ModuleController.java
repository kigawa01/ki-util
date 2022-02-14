package net.kigawa.module;

import java.util.HashSet;
import java.util.Set;

public class ModuleController extends Module {
    private final Set<Module> moduleSet = new HashSet<>();

    public ModuleController(String name) {
        super(name);
    }

    public void registerModule(Module module) {
        moduleSet.add(module);
    }

    public void unregisterModule(Module module) {
        moduleSet.remove(module);
        module.unregister(this);
    }

    public void unregisterAll() {
        moduleSet.forEach(m -> m.unregister(this));
        moduleSet.clear();
    }

    public void enable() {
        enable(this);
    }

    public void disable() {
        disable(this);
    }
    //--------------------------------------------------------------------------------------------

    @Override
    protected void onEnable() {
        moduleSet.forEach(m -> m.enable(this));
    }

    @Override
    protected void onDisable() {
        moduleSet.forEach(m -> m.disable(this));
    }
}
