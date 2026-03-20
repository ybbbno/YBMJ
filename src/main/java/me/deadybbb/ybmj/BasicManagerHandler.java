package me.deadybbb.ybmj;

public abstract class BasicManagerHandler {
    private boolean is_init = false;
    protected final PluginProvider plugin;

    public BasicManagerHandler(PluginProvider plugin) {
        this.plugin = plugin;
    }

    public final synchronized void init() {
        if (is_init) return;
        onInit();
        is_init = true;
    }

    public final synchronized void deinit() {
        if (!is_init) return;
        onDeinit();
        is_init = false;
    }

    public final synchronized boolean isInit() {
        return is_init;
    }

    protected abstract void onInit();
    protected abstract void onDeinit();
}
