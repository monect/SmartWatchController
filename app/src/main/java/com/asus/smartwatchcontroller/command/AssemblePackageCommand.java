package com.asus.smartwatchcontroller.command;

import android.app.IntentService;
import android.util.Log;

import com.asus.smartwatchcontroller.Exceptions.MessageBuildException;
import com.asus.smartwatchcontroller.HLAssemblerPool;
import com.asus.smartwatchcontroller.HLPackage;

/**
 * Created by Monect on 25/11/2016.
 */

public class AssemblePackageCommand extends BaseCommand {
    private static final String TAG;
    private StringBuilder builder;
    private HLPackage hlPackage;

    static {
        TAG = AssemblePackageCommand.class.getSimpleName();
    }

    public AssemblePackageCommand() {
        this.builder = new StringBuilder("Execute AssemblePackageCommand : ");
    }

    @Override
    public void execute() {
        final HLAssemblerPool instance = HLAssemblerPool.INSTANCE;
        if (this.hlPackage == null) {
            throw new NullPointerException("must call setHLPackage() before execute");
        }
        try {
            instance.addPackage(this.hlPackage);
            this.builder.append("add package complete");
        } catch (MessageBuildException ex) {
            this.builder.append("add package fail");
            Log.e("TAG", "", (Throwable) ex);
        }
    }

    @Override
    public final Class<? extends IntentService> getServiceClassName() {
        return null;
    }

    @Override
    public String printLog() {
        return this.builder.toString();
    }

    public AssemblePackageCommand setHlPackage(final HLPackage hlPackage) {
        this.hlPackage = hlPackage;
        return this;
    }
}
