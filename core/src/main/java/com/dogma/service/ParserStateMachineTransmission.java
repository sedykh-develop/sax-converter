package com.dogma.service;

import com.dogma.enums.MachineState;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Сервис осуществления переходов для автомата..
 */
public class ParserStateMachineTransmission {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Setter
    private MachineState state = MachineState.INITIAL_STATE;

    /**
     * Возвращает состояние автомата согласно символу перехода.
     *
     * @param symbol символ перехода.
     * @return состояние автомата.
     */
    public MachineState getState(final char symbol) {
        switch (state) {
            case INITIAL_STATE: {
                if (symbol == '<') {
                    return getStateWithPrevState(MachineState.OPEN_TAG);
                } else {
                    return state;
                }
            }
            case OPEN_TAG: {
                if (symbol != '<' && symbol != '>') {
                    System.out.println(symbol);
                    return getStateWithPrevState(MachineState.ELEMENT);
                } else {
                    return state;
                }
            }
            case ELEMENT: {
                switch (symbol) {
                    case '/': {
                        return getStateWithPrevState(MachineState.OPEN_CLOSE_TAG);
                    }
                    case '>': {
                        return getStateWithPrevState(MachineState.END_TAG);
                    }
                    case ' ': {
                        return getStateWithPrevState(MachineState.END_SEQUENCE);
                    }
                    default: {
                        return getStateWithPrevState(MachineState.ELEMENT);
                    }
                }
            }
            case END_SEQUENCE: {
                return getStateWithPrevState(MachineState.ATTRIBUTE);
            }
            case ATTRIBUTE: {
                switch (symbol) {
                    case '>': {
                        return getStateWithPrevState(MachineState.END_TAG);
                    }
                    case ' ': {
                        return getStateWithPrevState(MachineState.END_SEQUENCE);
                    }
                    default: {
                        return getStateWithPrevState(MachineState.ATTRIBUTE);
                    }
                }
            }
            case END_TAG: {
                return getStateWithPrevState(MachineState.VALUE);
            }
            case OPEN_CLOSE_TAG: {
                if (symbol == '>') {
                    return getStateWithPrevState(MachineState.CLOSE_TAG);
                } else {
                    return MachineState.END_ELEMENT;
                }
            }
            case CLOSE_TAG: {
                if (symbol == '<') {
                    return getStateWithPrevState(MachineState.OPEN_TAG);
                } else {
                    return state;
                }
            }
            case VALUE: {
                if (symbol == '<') {
                    return getStateWithPrevState(MachineState.END_VALUE);
                } else {
                    return MachineState.VALUE;
                }
            }
            case END_VALUE: {
                if (symbol == '/') {
                    return getStateWithPrevState(MachineState.OPEN_CLOSE_TAG);
                } else {
                    log.error("END VALUE BAD");
                }
            }
            case END_ELEMENT: {
                if (symbol == '>') {
                    return getStateWithPrevState(MachineState.CLOSE_TAG);
                } else {
                    return state;
                }
            }
            default: {
                return state; //log.error("error machine transmission");
            }
        }
        //log.error("error machine transmission");
    }

    /**
     * Возвращает состояние, записав информацию об предидущем состоянии.
     */
    private MachineState getStateWithPrevState(MachineState transmitionState) {
        state = transmitionState;
        return transmitionState;
    }
}
