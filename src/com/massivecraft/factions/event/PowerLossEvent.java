package com.massivecraft.factions.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;


public class PowerLossEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;
    private Faction faction;
    private FPlayer fplayer;
    private String message;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public PowerLossEvent(Faction f, FPlayer p) {
        cancelled = false;
        faction = f;
        fplayer = p;
    }

    public Faction getFaction() {
        return faction;
    }

    public String getFactionId() {
        return faction.getId();
    }

    public String getFactionTag() {
        return faction.getTag();
    }

    public FPlayer getFPlayer() {
        return fplayer;
    }

    public Player getPlayer() {
        return fplayer.getPlayer();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
