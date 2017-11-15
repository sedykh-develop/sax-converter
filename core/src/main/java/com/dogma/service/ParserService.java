package com.dogma.service;

import com.dogma.enums.MachineState;
import com.dogma.exceptions.StateMachineTransmissionException;

/**
 * Сервис обработки документов.
 */
public class ParserService {

    private ParserStateMachineTransmission parserStateMachineTransmission = new ParserStateMachineTransmission();

    private ParserStateMachineAction parserStateMachineAction = new ParserStateMachineAction();

    public void parse(char[] document) throws StateMachineTransmissionException {
        parserStateMachineAction.setDocument(document);
        MachineState state = MachineState.INITIAL_STATE;
        for (int i = 0; i < document.length; i++) {
            if (document[i] != '\r' && document[i] != '\t' && document[i] != '\n') {
                state = parserStateMachineTransmission.getState(document[i], state);
            }
            //parserStateMachineAction.onAction(state, i);
        }
    }
}
