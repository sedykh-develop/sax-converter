package com.dogma.exceptions;

import com.dogma.enums.MachineState;

/**
 * Ошибки перехода автомата.
 */
public class StateMachineTransmissionException extends Exception {

    public StateMachineTransmissionException() {
    }

    public StateMachineTransmissionException(MachineState state) {
        super(state.name());
    }
}
