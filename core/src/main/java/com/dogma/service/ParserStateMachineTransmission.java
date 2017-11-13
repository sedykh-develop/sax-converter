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
                    //log.error("error machine transmission");
                    return null;
                }
            }
            case OPEN_TAG: {
                if (symbol != '<' && symbol != '>') {
                    return getStateWithPrevState(MachineState.ELEMENT);
                } else {
                    //log.error("error machine transmission");
                    return null;
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
                    //log.error("error machine transmission");
                    return null;
                }
            }
            case CLOSE_TAG: {
                if (symbol == '<') {
                    return getStateWithPrevState(MachineState.OPEN_TAG);
                } else {
                    //log.error("error machine transmission");
                    return null;
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
                return getStateWithPrevState(MachineState.ELEMENT);
            }
            default: {
                return null;//log.error("error machine transmission");
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
