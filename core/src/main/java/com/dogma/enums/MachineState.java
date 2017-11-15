package com.dogma.enums;

/**
 * Состояния автомата.
 */
public enum MachineState {
    INITIAL_STATE,
    OPEN_TAG,
    ELEMENT,
    END_ELEMENT,
    END_SEQUENCE,
    ATTRIBUTE,
    END_TAG,
    OPEN_CLOSE_TAG,
    CLOSE_TAG,
    VALUE,
    END_VALUE,
    FINISH_STATE;
}
