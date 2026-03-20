package me.deadybbb.ybmj;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventManager extends BasicManagerHandler {
    private BukkitTask globalTask;

    private final List<PlayerEventHandler> handlers = new ArrayList<>();

    public EventManager(PluginProvider plugin) {
        super(plugin);
    }

    @Override
    protected void onInit() {
        if (globalTask != null || isInit()) return;

        globalTask = Bukkit.getScheduler().runTaskTimer(plugin, this::tick, 0L, 20L);
    }

    @Override
    protected void onDeinit() {
        if (globalTask == null || !isInit()) return;

        globalTask.cancel();
        globalTask = null;
    }

    private void tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player == null || !player.isOnline()) continue;

            GameMode gm = player.getGameMode();
            if (gm == GameMode.CREATIVE || gm == GameMode.SPECTATOR) continue;

            for (PlayerEventHandler handler : handlers) {
                if (handler.supports(player)) {
                    try {
                        handler.process(player);
                    } catch (Exception e) {
                        plugin.getLogger().severe("Error in event handler: " + handler.getClass().getSimpleName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public final boolean addHandler(@NotNull PlayerEventHandler handler) {
        return handlers.add(handler);
    }

    public final boolean removeHandler(@NotNull PlayerEventHandler handler) {
        return handlers.remove(handler);
    }
}
