package com.massivecraft.factions.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Factions;

public class FactionCreateEvent extends Event implements Cancellable

{
    private static final HandlerList handlers = new HandlerList();

    private String factionTag;
    private Player sender;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public FactionCreateEvent(Player sender, String tag) {
        this.factionTag = tag;
        this.sender = sender;
    }


    public FPlayer getFPlayer() {
        return FPlayers.i.get(sender);
    }

    public String getFactionId() {
        return Factions.i.getNextId();
    }

    public String getFactionTag() {
        return factionTag;
    }

}