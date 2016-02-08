package com.massivecraft.factions.zcore;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerLoginEvent;
import com.massivecraft.factions.zcore.persist.EM;
import com.massivecraft.factions.zcore.persist.Entity;
import com.massivecraft.factions.zcore.persist.EntityCollection;
import com.massivecraft.factions.zcore.persist.PlayerEntityCollection;

public class MPluginSecretPlayerListener implements Listener {
    private MPlugin p;

    public MPluginSecretPlayerListener(MPlugin p) {
        this.p = p;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) return;

        if (p.handleCommand(event.getPlayer(), event.getMessage())) {//error
            if (p.logPlayerCommands())
                Server.getInstance().getLogger().info("[PLAYER_COMMAND] " + event.getPlayer().getName() + ": " + event.getMessage());
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(PlayerChatEvent event) {
        if (event.isCancelled()) return;

        if (p.handleCommand(event.getPlayer(), event.getMessage(), false, true)) {
            if (p.logPlayerCommands())
                Server.getInstance().getLogger().info("[PLAYER_COMMAND] " + event.getPlayer().getName() + ": " + event.getMessage());
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerPreLogin(PlayerLoginEvent event) {
        for (EntityCollection<? extends Entity> ecoll : EM.class2Entities.values()) {
            if (ecoll instanceof PlayerEntityCollection) {
                ecoll.get(event.getPlayer().getName());
            }
        }
    }
}
