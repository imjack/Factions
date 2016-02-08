package com.massivecraft.factions.event;

import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.struct.Relation;


public class FactionRelationEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private Faction fsender;
    private Faction ftarget;
    private Relation foldrel;
    private Relation frel;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public FactionRelationEvent(Faction sender, Faction target, Relation oldrel, Relation rel) {
        fsender = sender;
        ftarget = target;
        foldrel = oldrel;
        frel = rel;
    }

    public Relation getOldRelation() {
        return foldrel;
    }

    public Relation getRelation() {
        return frel;
    }

    public Faction getFaction() {
        return fsender;
    }

    public Faction getTargetFaction() {
        return ftarget;
    }
}
