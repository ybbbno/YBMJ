package me.deadybbb.ybmj;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class BasicLoggerHandler {
    private final Logger logger;

    public BasicLoggerHandler(@NotNull JavaPlugin plugin) {
        this.logger = plugin.getLogger();
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
