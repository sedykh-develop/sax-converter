package com.dogma.service;

import com.dogma.enums.MachineState;
import com.dogma.exceptions.StateMachineTransmissionException;

public final class ParserStateMachine {
    private static final ParserStateMachine INSTANCE = new ParserStateMachine();

    private ParserStateMachineTransmission transmission = ParserStateMachineTransmission.INSTANCE;

    private ThreadLocal<ParserStateMachineAction> action;

    private ParserStateMachine() {
        action = new ThreadLocal<ParserStateMachineAction>() {
            @Override
            protected synchronized ParserStateMachineAction initialValue() {
                return new ParserStateMachineAction();
            }
        };
    }

    public static ParserStateMachine getInstance() {
        return INSTANCE;
    }


    public void parse(final char[] document, final ContentHandler contentHandler) throws StateMachineTransmissionException {
        ParserStateMachineAction stateMachineAction = action.get();
        stateMachineAction.setDocument(document);
        stateMachineAction.setHandler(contentHandler);
        MachineState curState = MachineState.INITIAL_STATE;
        for (int i = 0; i < document.length; i++) {
            if (isTransmissionSymbol(document[i])) {
                curState = transmission.getState(document[i], curState, i);
                stateMachineAction.onAction(curState, document[i]);
            }
        }
    }

    private boolean isTransmissionSymbol(char symbol) {
        return symbol != '\r' && symbol != '\t' && symbol != '\n';
    }
}
