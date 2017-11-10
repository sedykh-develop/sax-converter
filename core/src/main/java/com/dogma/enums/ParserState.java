package com.dogma.enums;

/**
 * Состояние разбора документа.
 */
public enum ParserState {
    INITIAL_STATE,
    OPEN_TAG,
    ELEMENT,
    END_SEQUENCE,
    ATTRUBUTE,
    END_TAG,
    OPEN_CLOSE_TAG,
    CLOSE_TAG,
    VALUE,
    END_VALUE,
    FINISH_STATE;
}
