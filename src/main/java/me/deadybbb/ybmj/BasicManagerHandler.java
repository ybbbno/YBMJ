package me.deadybbb.ybmj;

public abstract class BasicManagerHandler {
    protected boolean is_init = false;
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

    public boolean is_init_set() {
        return is_init;
    }

    protected abstract void onInit();
    protected abstract void onDeinit();
}
