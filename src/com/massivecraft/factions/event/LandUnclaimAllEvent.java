package com.massivecraft.factions.event;

import cn.nukkit.Player;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;

public class LandUnclaimAllEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private Faction faction;
    private FPlayer fplayer;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public LandUnclaimAllEvent(Faction f, FPlayer p) {
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
}
