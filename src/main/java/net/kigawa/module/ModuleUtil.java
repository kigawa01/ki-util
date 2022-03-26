package net.kigawa.module;

public class ModuleUtil {
    public static void changeModule(Module source, Module target) {
        var controllerMap = target.getControllerMap();
        for (ModuleController controller : controllerMap.keySet()) {
            controller.unregisterModule(target);
            controller.registerModule(source);
        }
    }
}
