package com.dogma.enums;

/**
 * Состояния автомата.
 */
public enum MachineState {
    INITIAL_STATE,
    OPEN_TAG,
    XML_CONTENT,
    XML_ATTRIBUTE,
    END_XML_ATTRIBUTE,
    END_XML_CONTENT,
    ELEMENT,
    END_SEQUENCE,
    ATTRIBUTE,
    END_OPEN_TAG,
    START_CLOSE_TAG,
    CLOSE_TAG,
    VALUE,
    END_VALUE,
    START_END_ELEMENT,
    END_ELEMENT,
    CLOSE_END_ELEMENT
}
