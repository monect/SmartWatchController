package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */

public class Music implements HLMediateResponseMessage {
    public static final int COMMAND_ERROR = -1;
    public static final int COMMAND_NEXT = 4;
    public static final int COMMAND_PAUSE = 2;
    public static final int COMMAND_PLAY = 1;
    public static final int COMMAND_PREVIOUS = 3;
    public static final int COMMAND_STOP = 0;
    public static final int COMMAND_VOLUME_DOWN = 6;
    public static final int COMMAND_VOLUME_UP = 5;
    private static final long serialVersionUID = 7901376796260405119L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_COMMAND;
    private int command;
    private Map<String, Object> valueMap;

    public Music() {
        this.FIELD_COMMAND = "command";
        this.command = -1;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.SIGNED_INT, "command", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 16));
        }
        return this.AttrInfos;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        this.valueMap.put("command", this.command);
        return this.valueMap;
    }

    public int getCommand() {
        return this.command;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.MUSIC;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setCommand((Integer) map.get("command"));
    }

    public void setCommand(final int command) throws InvalidMessageFieldException {
        if (command > 6 || command < 0) {
            throw new InvalidMessageFieldException("Wrong value for command : " + command);
        }
        this.command = command;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Music{");
        String s = "";
        switch (this.command) {
            case 0: {
                s = "COMMAND_STOP";
                break;
            }
            case 1: {
                s = "COMMAND_PLAY";
                break;
            }
            case 4: {
                s = "COMMAND_NEXT";
                break;
            }
            case 2: {
                s = "COMMAND_PAUSE";
                break;
            }
            case 3: {
                s = "COMMAND_PREVIOUS";
                break;
            }
            case 5: {
                s = "COMMAND_VOLUME_UP";
                break;
            }
            case 6: {
                s = "COMMAND_VOLUME_DOWN";
                break;
            }
        }
        sb.append("command=").append(s);
        sb.append('}');
        return sb.toString();
    }
}
