package com.massivecraft.factions.cmd;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import com.massivecraft.factions.*;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Relation;
import com.massivecraft.factions.struct.Role;
import com.massivecraft.factions.zcore.util.SmokeUtil;

import java.util.ArrayList;
import java.util.List;


public class CmdHome extends FCommand {

    public CmdHome() {
        super();
        this.aliases.add("home");

        //this.requiredArgs.add("");
        //this.optionalArgs.put("", "");

        this.permission = Permission.HOME.node;
        this.disableOnLock = false;

        senderMustBePlayer = true;
        senderMustBeMember = true;
        senderMustBeModerator = false;
        senderMustBeAdmin = false;
    }

    @Override
    public void perform() {
        // TODO: Hide this command on help also.
        if (!Conf.homesEnabled) {
            fme.msg("<b>Sorry, Faction homes are disabled on this server.");
            return;
        }

        if (!Conf.homesTeleportCommandEnabled) {
            fme.msg("<b>Sorry, the ability to teleport to Faction homes is disabled on this server.");
            return;
        }

        if (!myFaction.hasHome()) {
            fme.msg("<b>Your faction does not have a home. " + (fme.getRole().value < Role.MODERATOR.value ? "<i> Ask your leader to:" : "<i>You should:"));
            fme.sendMessage(p.cmdBase.cmdSethome.getUseageTemplate());
            return;
        }

        if (!Conf.homesTeleportAllowedFromEnemyTerritory && fme.isInEnemyTerritory()) {
            fme.msg("<b>You cannot teleport to your faction home while in the territory of an enemy faction.");
            return;
        }

        if (!Conf.homesTeleportAllowedFromDifferentWorld && me.getLevel().getId() != myFaction.getHome().getLevel().getId()) {
            fme.msg("<b>You cannot teleport to your faction home while in a different world.");
            return;
        }

        Faction faction = Board.getFactionAt(new FLocation(me.getLocation()));
        Vector3 loc = me.getLocation().clone();

        // if player is not in a safe zone or their own faction territory, only allow teleport if no enemies are nearby
        if
                (
                Conf.homesTeleportAllowedEnemyDistance > 0
                        &&
                        !faction.isSafeZone()
                        &&
                        (
                                !fme.isInOwnTerritory()
                                        ||
                                        (
                                                fme.isInOwnTerritory()
                                                        &&
                                                        !Conf.homesTeleportIgnoreEnemiesIfInOwnTerritory
                                        )
                        )
                ) {
            Level w = me.getLevel();
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();

            for (Player p : me.getServer().getOnlinePlayers().values()) {
                if (p == null || !p.isOnline() || p.getHealth() <= 0 || p == me || p.getLevel() != w)
                    continue;

                FPlayer fp = FPlayers.i.get(p);
                if (fme.getRelationTo(fp) != Relation.ENEMY)
                    continue;

                Location l = p.getLocation();
                double dx = Math.abs(x - l.getX());
                double dy = Math.abs(y - l.getY());
                double dz = Math.abs(z - l.getZ());
                double max = Conf.homesTeleportAllowedEnemyDistance;

                // box-shaped distance check
                if (dx > max || dy > max || dz > max)
                    continue;

                fme.msg("<b>You cannot teleport to your faction home while an enemy is within " + Conf.homesTeleportAllowedEnemyDistance + " blocks of you.");
                return;
            }
        }

        // if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
        if (!payForCommand(Conf.econCostHome, "to teleport to your faction home", "for teleporting to your faction home"))
            return;

        // Create a smoke effect
        if (Conf.homesTeleportCommandSmokeEffectEnabled) {
            List<Location> smokeLocations = new ArrayList<Location>();
            smokeLocations.add(new Location().fromObject(loc, me.getLevel()));
            smokeLocations.add(new Location().fromObject(loc.add(0, 1, 0), me.getLevel()));
            smokeLocations.add(myFaction.getHome());
            smokeLocations.add(new Location().fromObject(myFaction.getHome().clone().add(0, 1, 0), me.getLevel()));
            SmokeUtil.spawnCloudRandom(smokeLocations, Conf.homesTeleportCommandSmokeEffectThickness);
        }

        me.teleport(myFaction.getHome());
    }

}
