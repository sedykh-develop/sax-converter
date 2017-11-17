package com.dogma.service;

import com.dogma.exceptions.StateMachineTransmissionException;

/**
 * Сервис обработки документов.
 */
public class ParserService {
    public void parse(char[] document) throws StateMachineTransmissionException {
        ParserStateMachine.getInstance().parse(document, new ContentHandler());
    }
}
