package com.massivecraft.factions.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;

public class FactionRenameEvent extends Event implements Cancellable

{
    private static final HandlerList handlers = new HandlerList();

    private FPlayer fplayer;
    private Faction faction;
    private String tag;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public FactionRenameEvent(FPlayer sender, String newTag) {
        fplayer = sender;
        faction = sender.getFaction();
        tag = newTag;
    }

    public Faction getFaction() {
        return (faction);
    }

    public FPlayer getFPlayer() {
        return (fplayer);
    }

    public Player getPlayer() {
        return (fplayer.getPlayer());
    }

    public String getOldFactionTag() {
        return (faction.getTag());
    }

    public String getFactionTag() {
        return (tag);
    }


}
