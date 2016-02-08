package com.massivecraft.factions.event;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;

public class FPlayerLeaveEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    FPlayer FPlayer;
    Faction Faction;
    private PlayerLeaveReason reason;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public FPlayerLeaveEvent(FPlayer p, Faction f, PlayerLeaveReason r) {
        FPlayer = p;
        Faction = f;
        reason = r;
    }

    public PlayerLeaveReason getReason() {
        return reason;
    }

    public FPlayer getFPlayer() {
        return FPlayer;
    }

    public Faction getFaction() {
        return Faction;
    }

    public enum PlayerLeaveReason {
        KICKED, DISBAND, RESET, JOINOTHER, LEAVE
    }
}