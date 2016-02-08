package com.massivecraft.factions.util;

import cn.nukkit.Server;
import com.massivecraft.factions.Conf;
import com.massivecraft.factions.P;

public class AutoLeaveTask implements Runnable {
    private static AutoLeaveProcessTask task;
    double rate;

    public AutoLeaveTask() {
        this.rate = Conf.autoLeaveRoutineRunsEveryXMinutes;
    }

    public synchronized void run() {
        if (task != null && !task.isFinished())
            return;

        task = new AutoLeaveProcessTask(P.p);
        Server.getInstance().getScheduler().scheduleRepeatingTask(task, 20 * 1);

        // maybe setting has been changed? if so, restart this task at new rate
        if (this.rate != Conf.autoLeaveRoutineRunsEveryXMinutes)
            P.p.startAutoLeaveTask(true);
    }
}
