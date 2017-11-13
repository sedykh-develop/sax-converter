package com.dogma.service

import com.dogma.enums.MachineState
import org.junit.Test

class ParserStateMachineTransmissionJUnitTest {

    /*def "Проверка перехода состояний парсера"() {
        char[] document = new String(new File('\\sax-converter\\core\\src\\test\\resources\\test-transmission.xml').bytes,
                'UTF-8')
                .toCharArray()
        ParserStateMachineTransmission transmission = new ParserStateMachineTransmission()
        for (int i = 0; i < document.length; i++) {
            println "${transmission.getState(document[i]).name}"
        }
    }*/

    @Test
    void testOne() {
        char[] document = new String(new File(this.getClass().getResource('/test-transmission.xml').getFile()).bytes,
                'UTF-8')
                .toCharArray()
        long before = System.currentTimeMillis()
        ParserStateMachineTransmission transmission = new ParserStateMachineTransmission()
        for (int k = 0; k < 1000; k++) {
            transmission.state = MachineState.INITIAL_STATE
            for (int i = 0; i < document.length; i++) {
                MachineState state = transmission.getState(document[i])
                state.toString()
            }
        }
        println "${System.currentTimeMillis() - before}"
    }
}
