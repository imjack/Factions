package com.massivecraft.factions;

import cn.nukkit.utils.LogLevel;
import cn.nukkit.utils.TextFormat;
import com.google.gson.reflect.TypeToken;
import com.massivecraft.factions.util.MiscUtil;
import com.massivecraft.factions.zcore.persist.EntityCollection;
import com.massivecraft.factions.zcore.util.TextUtil;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Factions extends EntityCollection<Faction> {
    public static Factions i = new Factions();

    P p = P.p;

    private Factions() {
        super
                (
                        Faction.class,
                        new CopyOnWriteArrayList<Faction>(),
                        new ConcurrentHashMap<String, Faction>(),
                        new File(P.p.getDataFolder(), "factions.json"),
                        P.p.gson
                );
    }

    public static ArrayList<String> validateTag(String str) {
        ArrayList<String> errors = new ArrayList<String>();

        if (MiscUtil.getComparisonString(str).length() < Conf.factionTagLengthMin) {
            errors.add(P.p.txt.parse("<i>The faction tag can't be shorter than <h>%s<i> chars.", Conf.factionTagLengthMin));
        }

        if (str.length() > Conf.factionTagLengthMax) {
            errors.add(P.p.txt.parse("<i>The faction tag can't be longer than <h>%s<i> chars.", Conf.factionTagLengthMax));
        }

        for (char c : str.toCharArray()) {
            if (!MiscUtil.substanceChars.contains(String.valueOf(c))) {
                errors.add(P.p.txt.parse("<i>Faction tag must be alphanumeric. \"<h>%s<i>\" is not allowed.", c));
            }
        }

        return errors;
    }

    @Override
    public Type getMapType() {
        return new TypeToken<Map<String, Faction>>() {
        }.getType();
    }


    //----------------------------------------------//
    // GET
    //----------------------------------------------//

    @Override
    public boolean loadFromDisc() {
        if (!super.loadFromDisc()) return false;

        // Make sure the default neutral faction exists
        if (!this.exists("0")) {
            Faction faction = this.create("0");
            faction.setTag(TextFormat.DARK_GREEN + "Wilderness");
            faction.setDescription("");
        }

        // Make sure the safe zone faction exists
        if (!this.exists("-1")) {
            Faction faction = this.create("-1");
            faction.setTag("SafeZone");
            faction.setDescription("Free from PVP and monsters");
        } else {
            // if SafeZone has old pre-1.6.0 name, rename it to remove troublesome " "
            Faction faction = this.getSafeZone();
            if (faction.getTag().contains(" "))
                faction.setTag("SafeZone");
        }

        // Make sure the war zone faction exists
        if (!this.exists("-2")) {
            Faction faction = this.create("-2");
            faction.setTag("WarZone");
            faction.setDescription("Not the safest place to be");
        } else {
            // if WarZone has old pre-1.6.0 name, rename it to remove troublesome " "
            Faction faction = this.getWarZone();
            if (faction.getTag().contains(" "))
                faction.setTag("WarZone");
        }

        // populate all faction player lists
        for (Faction faction : i.get()) {
            faction.refreshFPlayers();
        }

        return true;
    }

    @Override
    public Faction get(String id) {
        if (!this.exists(id)) {
            p.log(LogLevel.WARNING, "Non existing factionId " + id + " requested! Issuing cleaning!");
            Board.clean();
            FPlayers.i.clean();
        }

        return super.get(id);
    }

    public Faction getNone() {
        return this.get("0");
    }

    public Faction getSafeZone() {
        return this.get("-1");
    }


    //----------------------------------------------//
    // Faction tag
    //----------------------------------------------//

    public Faction getWarZone() {
        return this.get("-2");
    }

    public Faction getByTag(String str) {
        String compStr = MiscUtil.getComparisonString(str);
        for (Faction faction : this.get()) {
            if (faction.getComparisonTag().equals(compStr)) {
                return faction;
            }
        }
        return null;
    }

    public Faction getBestTagMatch(String searchFor) {
        Map<String, Faction> tag2faction = new HashMap<String, Faction>();

        // TODO: Slow index building
        for (Faction faction : this.get()) {
            tag2faction.put(TextFormat.clean(faction.getTag()), faction);
        }

        String tag = TextUtil.getBestStartWithCI(tag2faction.keySet(), searchFor);
        if (tag == null) return null;
        return tag2faction.get(tag);
    }

    public boolean isTagTaken(String str) {
        return this.getByTag(str) != null;
    }

}
