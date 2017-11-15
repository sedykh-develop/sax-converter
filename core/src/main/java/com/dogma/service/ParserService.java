package com.dogma.service;

import com.dogma.enums.MachineState;

/**
 * Сервис обработки документов.
 */
public class ParserService {

    private ParserStateMachineTransmission parserStateMachineTransmission = new ParserStateMachineTransmission();

    private ParserStateMachineAction parserStateMachineAction = new ParserStateMachineAction();

    public void parse(char[] document) {
        parserStateMachineAction.setDocument(document);
        for (int i = 0; i < document.length; i++) {
            MachineState state = parserStateMachineTransmission.getState(document[i]);
            parserStateMachineAction.onAction(state, i);
        }
    }
}
