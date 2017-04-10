package com.asus.smartwatchcontroller.command;

import android.app.IntentService;

import java.io.Serializable;

/**
 * Created by Monect on 25/11/2016.
 */
public abstract class BaseCommand
        implements Serializable {
    private static final long serialVersionUID = -438537365077037350L;

    public abstract void execute();

    public abstract Class<? extends IntentService> getServiceClassName();

    public abstract String printLog();
}
