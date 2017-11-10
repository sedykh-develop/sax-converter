package com.dogma.service;

import com.dogma.enums.ParserState;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Конечный детерминированный автомат для разбора документа.
 */
public class FinishStateMachineParser {
    private Logger log = Logger.getLogger(this.getClass().getName());

    private ParserState state = ParserState.INITIAL_STATE;

    private ParserState prevState;

    /**
     * Возвращает состояние автомата согласно символу перехода.
     *
     * @param symbol символ перехода.
     * @return состояние автомата.
     */
    public ParserState getState(final char symbol) {
        switch (state) {
            case INITIAL_STATE: {
                if (symbol == '<') {
                    prevState = state;
                    return getStateWithPrevState(ParserState.OPEN_TAG);
                } else {
                    log.log(Level.WARNING, "error automat transmition");
                    break;
                }
            }
            case OPEN_TAG: {
                if (symbol != '<' && symbol != '>') {
                    return getStateWithPrevState(ParserState.ELEMENT);
                } else {
                    log.log(Level.WARNING, "error automat transmition");
                    break;
                }
            }
            case ELEMENT: {
                switch (symbol) {
                    case '/' : {
                        return getStateWithPrevState(ParserState.OPEN_CLOSE_TAG);
                    }
                    case '>' : {
                        return getStateWithPrevState(ParserState.END_TAG);
                    }
                    case ' ' : {
                        return getStateWithPrevState(ParserState.END_SEQUENCE);
                    }
                    default: {
                        return getStateWithPrevState(ParserState.ELEMENT);
                    }
                }
            }
            case END_SEQUENCE: {
                return getStateWithPrevState(ParserState.ATTRUBUTE);
            }
            case ATTRUBUTE: {
                switch (symbol) {
                    case '>' :{
                        return getStateWithPrevState(ParserState.END_TAG);
                    }
                    case ' ': {
                        return getStateWithPrevState(ParserState.END_SEQUENCE);
                    }
                    default: {
                        return getStateWithPrevState(ParserState.ATTRUBUTE);
                    }
                }
            }
            case END_TAG: {
                return getStateWithPrevState(ParserState.VALUE);
            }
            case OPEN_CLOSE_TAG: {
                if (symbol == '>') {
                    return getStateWithPrevState(ParserState.CLOSE_TAG);
                } else {
                    log.log(Level.WARNING, "error automat transmition");
                    break;
                }
            }
            case CLOSE_TAG: {
                if (symbol == '<') {
                    return getStateWithPrevState(ParserState.OPEN_TAG);
                } else {
                    log.log(Level.WARNING, "error automat transmition");
                    break;
                }
            }
            case VALUE: {
                if (symbol == '<') {
                    return getStateWithPrevState(ParserState.END_VALUE);
                } else {
                    return ParserState.VALUE;
                }
            }
            case END_VALUE: {
                return getStateWithPrevState(ParserState.ELEMENT);
            }
            default: {
                log.log(Level.WARNING, "error automat transmition");
                break;
            }
        }
        return null;
    }

    /**
     * Возвращает состояние, записав информацию об предидущем состоянии.
     */
    private ParserState getStateWithPrevState(ParserState transmitionState) {
        prevState = state;
        state = transmitionState;
        return transmitionState;
    }
}
