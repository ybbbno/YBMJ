package me.deadybbb.ybmj;

public final class YBMJ extends PluginProvider {
    private final EventManager eventManager = new EventManager(this);

    @Override
    public void onEnable() {
        eventManager.init();
    }

    @Override
    public void onDisable() {
        eventManager.deinit();
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
