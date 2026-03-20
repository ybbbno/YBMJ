package me.deadybbb.ybmj;

import org.bukkit.entity.Player;

public interface PlayerEventHandler {
    boolean supports(Player player);
    void process(Player player);
}
