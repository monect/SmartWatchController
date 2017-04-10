package com.asus.smartwatchcontroller;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Monect on 25/11/2016.
 */

public enum OttoBusProxy {
    INSTANCE;

    private static Bus bus;
    private final Handler mHandler;

    static {
        OttoBusProxy.bus = new Bus(ThreadEnforcer.ANY);
    }

    private OttoBusProxy() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void post(final Object o) {
        OttoBusProxy.bus.post(o);
    }

    public void postOnMainThread(final Object o) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            OttoBusProxy.bus.post(o);
            return;
        }
        this.mHandler.post((Runnable) new Runnable() {
            @Override
            public void run() {
                OttoBusProxy.bus.post(o);
            }
        });
    }

    public void register(final Object o) {
        OttoBusProxy.bus.register(o);
    }

    public void unregister(final Object o) {
        OttoBusProxy.bus.unregister(o);
    }
}
