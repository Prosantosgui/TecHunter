package com.prosantosgui.techunter.model.enums;

public enum PositionStatus {
    OPEN(1),
    CLOSED(2);

    private int code;

    PositionStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PositionStatus valueOf(int code) {
        for (PositionStatus value: PositionStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PositionStatus code");
    }
}
