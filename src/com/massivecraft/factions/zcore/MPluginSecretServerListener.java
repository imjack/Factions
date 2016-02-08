package com.massivecraft.factions.zcore;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.ServerCommandEvent;


public class MPluginSecretServerListener implements Listener {
    private MPlugin p;

    public MPluginSecretServerListener(MPlugin p) {
        this.p = p;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onServerCommand(ServerCommandEvent event) {
        if (event.getCommand().length() == 0) return;

        if (p.handleCommand(event.getSender(), event.getCommand())) {
            event.setCommand(p.refCommand);
        }
    }

}
