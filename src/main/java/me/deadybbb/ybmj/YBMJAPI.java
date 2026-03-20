package me.deadybbb.ybmj;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class YBMJAPI {
    private final YBMJ plugin;

    private YBMJAPI(YBMJ plugin) { this.plugin = plugin; }

    public EventManager getEventManager() { return plugin.getEventManager(); }

    public static YBMJAPI getAPI() {
        Plugin p = Bukkit.getPluginManager().getPlugin("YBMJ");
        if (p instanceof YBMJ) {
            return new YBMJAPI((YBMJ) p);
        }
        return null;
    }
}
