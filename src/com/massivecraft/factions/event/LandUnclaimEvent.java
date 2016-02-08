package com.massivecraft.factions.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;

public class LandUnclaimEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private FLocation location;
    private Faction faction;
    private FPlayer fplayer;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public LandUnclaimEvent(FLocation loc, Faction f, FPlayer p) {
        location = loc;
        faction = f;
        fplayer = p;
    }

    public FLocation getLocation() {
        return this.location;
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
}
