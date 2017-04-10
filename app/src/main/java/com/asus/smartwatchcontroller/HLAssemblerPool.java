package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.MessageBuildException;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Monect on 25/11/2016.
 */

public enum HLAssemblerPool {
    INSTANCE,
    WATCH_INSTANCE;

    private HLPackageAssembler.Listener assemblerListener;
    private Map<HLPackageConstants.TYPE_MESSAGE, HLPackageAssembler> messageBuilderMap;
    private MessageFactory messageFactory;

    private HLAssemblerPool() {
        this.messageBuilderMap = new WeakHashMap<HLPackageConstants.TYPE_MESSAGE, HLPackageAssembler>();
        this.messageFactory = new MessageFactory();
    }

    private HLPackageAssembler createBuilderIfNotExist(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE) {
        HLPackageAssembler hlPackageAssembler;
        if ((hlPackageAssembler = this.messageBuilderMap.get(type_MESSAGE)) == null) {
            hlPackageAssembler = new HLPackageAssembler(this.messageFactory, this.assemblerListener);
            this.messageBuilderMap.put(type_MESSAGE, hlPackageAssembler);
        }
        return hlPackageAssembler;
    }

    public HLAssemblerPool addPackage(final HLPackage hlPackage) throws MessageBuildException {
        final HLPackageParser hlPackageParser = new HLPackageParser(hlPackage);
        this.createBuilderIfNotExist(hlPackageParser.getMessageType()).append(hlPackageParser);
        return this;
    }

    public void setMessageListener(final HLPackageAssembler.Listener assemblerListener) {
        this.assemblerListener = assemblerListener;
    }
}
