package me.deadybbb.customzones.ybbbbasicmodule;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginProvider extends JavaPlugin {
    public final BasicLoggerHandler logger = new BasicLoggerHandler(this);
}
