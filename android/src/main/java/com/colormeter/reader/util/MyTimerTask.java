package com.colormeter.reader.util;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask {
    private Timer timer;
    private TimerTask task;
    private long time;

    public MyTimerTask(long time, TimerTask task) {
        this.task = task;
        this.time = time;
        if (timer == null){
            timer=new Timer();
        }
    }

    public void start(){
        timer.schedule(task, 0, time);//每隔time时间段就执行一次
    }

    public void stop(){
        if (timer != null) {
            timer.cancel();
            if (task != null) {
                task.cancel();  //将原任务从队列中移除
            }
        }
    }
}
