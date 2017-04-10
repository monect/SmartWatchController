package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class SequenceResponse implements HLImmediateResponseMessage {
    private static final long serialVersionUID = 3745527398092314201L;
    private List<MessageAttributeInfo> Attrs;
    private final String FIELD_SEQUENCE;
    private int sequenceNumber;
    private HLPackageConstants.TYPE_MESSAGE type;
    private Map<String, Object> valueMap;

    public SequenceResponse(final HLPackageConstants.TYPE_MESSAGE type, final int sequenceNumber) {
        this.FIELD_SEQUENCE = "sequenceNumber";
        this.sequenceNumber = 0;
        this.type = type;
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.Attrs == null) {
            (this.Attrs = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "sequenceNumber", 8));
        }
        return this.Attrs;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        this.valueMap.put("sequenceNumber", this.sequenceNumber);
        return this.valueMap;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return this.type;
    }

    public void reset(final HLPackageConstants.TYPE_MESSAGE type) {
        this.type = type;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setSequenceNumber((Integer) map.get("sequenceNumber"));
    }

    public void setSequenceNumber(final int sequenceNumber) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Byte(sequenceNumber)) {
            throw new InvalidMessageFieldException("sequenceNumber", sequenceNumber);
        }
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SequenceResponse{");
        sb.append("sequenceNumber=").append(this.sequenceNumber);
        sb.append(", type=").append(this.type);
        sb.append('}');
        return sb.toString();
    }
}
