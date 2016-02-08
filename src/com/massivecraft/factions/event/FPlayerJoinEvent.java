package com.massivecraft.factions.event;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;

public class FPlayerJoinEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final FPlayer fplayer;
    private final Faction faction;
    private final PlayerJoinReason reason;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public FPlayerJoinEvent(FPlayer fp, Faction f, PlayerJoinReason r) {
        fplayer = fp;
        faction = f;
        reason = r;
    }


    public FPlayer getFPlayer() {
        return fplayer;
    }

    public Faction getFaction() {
        return faction;
    }

    public PlayerJoinReason getReason() {
        return reason;
    }

    public enum PlayerJoinReason {
        CREATE, LEADER, COMMAND
    }
}