package com.massivecraft.factions.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;

public class FactionDisbandEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final String id;
    private final Player sender;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public FactionDisbandEvent(Player sender, String factionId) {
        this.sender = sender;
        this.id = factionId;
    }

    public Faction getFaction() {
        return Factions.i.get(id);
    }

    public FPlayer getFPlayer() {
        return FPlayers.i.get(sender);
    }

    public Player getPlayer() {
        return sender;
    }

}
