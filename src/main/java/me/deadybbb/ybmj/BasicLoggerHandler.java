package me.deadybbb.ybmj;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class BasicLoggerHandler {
    private final Logger logger;
    private final String name;

    public BasicLoggerHandler(JavaPlugin plugin) {
        this.logger = plugin.getLogger();
        this.name = plugin.getName();
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void warning(String msg) {
        logger.warning(msg);
    }

    public void severe(String msg) {
        logger.severe(msg);
    }
}
