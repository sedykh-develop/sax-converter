package com.dogma.service;

import com.dogma.enums.MachineState;
import com.dogma.exceptions.StateMachineTransmissionException;


/**
 * Сервис осуществления переходов для автомата.
 */
public enum ParserStateMachineTransmission {
    INSTANCE();

    /**
     * Возвращает состояние автомата согласно символу перехода.
     *
     * @param symbol символ перехода.
     * @return состояние автомата.
     */
    public MachineState getState(final char symbol, final MachineState prevState, int i)
            throws StateMachineTransmissionException {
        switch (prevState) {
            case INITIAL_STATE: {
                if (symbol == '<') {
                    return MachineState.OPEN_TAG;
                } else {
                    throw new StateMachineTransmissionException(prevState);
                }
            }
            case OPEN_TAG: {
                if (symbol == '?') {
                    return MachineState.XML_CONTENT;
                } else if (symbol == '/') {
                    return MachineState.START_END_ELEMENT;
                } else {
                    return MachineState.ELEMENT;
                }
            }
            case XML_CONTENT: {
                if (symbol == ' ') {
                    return MachineState.XML_ATTRIBUTE;
                } else {
                    return prevState;
                }
            }
            case XML_ATTRIBUTE: {
                if (symbol == ' ') {
                    return MachineState.END_XML_ATTRIBUTE;
                } else if (symbol == '>') {
                    return MachineState.END_XML_CONTENT;
                } else {
                    return prevState;
                }
            }
            case END_XML_ATTRIBUTE: {
                if (symbol == '>') {
                    return MachineState.END_XML_CONTENT;
                } else {
                    return MachineState.XML_ATTRIBUTE;
                }
            }
            case END_XML_CONTENT: {
                if (symbol == '<') {
                    return MachineState.OPEN_TAG;
                } else if (symbol == ' ') {
                    return prevState;
                } else {
                    throw new StateMachineTransmissionException(prevState);
                }
            }
            case ELEMENT: {
                switch (symbol) {
                    case '>': {
                        return MachineState.END_OPEN_TAG;
                    }
                    case '/': {
                        return MachineState.START_CLOSE_TAG;
                    }
                    case ' ': {
                        return MachineState.END_SEQUENCE;
                    }
                    default: {
                        return prevState;
                    }
                }
            }
            case END_OPEN_TAG: {
                if (symbol == '<') {
                    return MachineState.OPEN_TAG;
                } else if (symbol == ' ') {
                    return prevState;
                } else {
                    return MachineState.VALUE;
                }
            }
            case START_CLOSE_TAG: {
                if (symbol == '>') {
                    return MachineState.CLOSE_TAG;
                } else {
                    throw new StateMachineTransmissionException(prevState);
                }
            }
            case END_SEQUENCE: {
                return MachineState.ATTRIBUTE;
            }
            case VALUE: {
                if (symbol == '<') {
                    return MachineState.END_VALUE;
                } else {
                    return prevState;
                }
            }
            case END_VALUE: {
                if (symbol == '/') {
                    return MachineState.START_END_ELEMENT;
                } else {
                    throw new StateMachineTransmissionException(prevState);
                }
            }
            case START_END_ELEMENT: {
                return MachineState.END_ELEMENT;
            }
            case END_ELEMENT: {
                if (symbol == '>') {
                    return MachineState.CLOSE_END_ELEMENT;
                } else {
                    return prevState;
                }
            }
            case CLOSE_END_ELEMENT: {
                if (symbol == '<') {
                    return MachineState.OPEN_TAG;
                } else if (symbol == ' ') {
                    return prevState;
                } else {
                    throw new StateMachineTransmissionException(prevState);
                }
            }
            case CLOSE_TAG: {
                if (symbol == '<') {
                    return MachineState.OPEN_TAG;
                } else if (symbol == ' ') {
                    return prevState;
                } else {
                    throw new StateMachineTransmissionException(prevState);
                }
            }
            case ATTRIBUTE: {
                switch (symbol) {
                    case ' ': {
                        return MachineState.END_SEQUENCE;
                    }
                    case '>': {
                        return MachineState.END_OPEN_TAG;
                    }
                    default: {
                        return prevState;
                    }
                }
            }
        }
        throw new StateMachineTransmissionException(prevState);
    }
}
