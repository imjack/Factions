package com.massivecraft.factions.cmd;

import cn.nukkit.Server;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Conf;
import com.massivecraft.factions.P;
import com.massivecraft.factions.event.LandUnclaimAllEvent;
import com.massivecraft.factions.struct.Permission;

public class CmdUnclaimall extends FCommand {
    public CmdUnclaimall() {
        this.aliases.add("unclaimall");
        this.aliases.add("declaimall");

        //this.requiredArgs.add("");
        //this.optionalArgs.put("", "");

        this.permission = Permission.UNCLAIM_ALL.node;
        this.disableOnLock = true;

        senderMustBePlayer = true;
        senderMustBeMember = false;
        senderMustBeModerator = true;
        senderMustBeAdmin = false;
    }

    @Override
    public void perform() {

        LandUnclaimAllEvent unclaimAllEvent = new LandUnclaimAllEvent(myFaction, fme);
        Server.getInstance().getPluginManager().callEvent(unclaimAllEvent);
        // this event cannot be cancelled

        Board.unclaimAll(myFaction.getId());
        myFaction.msg("%s<i> unclaimed ALL of your faction's land.", fme.describeTo(myFaction, true));

        if (Conf.logLandUnclaims)
            P.p.log(fme.getName() + " unclaimed everything for the faction: " + myFaction.getTag());
    }

}
