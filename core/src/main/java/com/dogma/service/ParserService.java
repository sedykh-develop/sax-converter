package com.dogma.service;

import com.dogma.enums.MachineState;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Сервис обработки документов.
 */
public class ParserService {

    private DefaultHandler handler;

    private ParserStateMachineTransmission parserStateMachineTransmission = new ParserStateMachineTransmission();

    public void parse(char[] document) {
        for (int i = 0; i < document.length; i++) {
            MachineState state = parserStateMachineTransmission.getState(document[i]);
        }
    }
}
