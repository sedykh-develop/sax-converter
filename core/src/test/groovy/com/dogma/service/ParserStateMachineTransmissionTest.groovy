package com.dogma.service

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
        char[] document = new String(new File('C:\\Users\\Дима\\Documents\\myProj\\sax-converter\\core\\src\\test\\resources\\test-transmission.xml').bytes,
                'UTF-8')
                .toCharArray()
        long before = System.currentTimeMillis()
        ParserService service = new ParserService()
        for (int i = 0; i < 1000; i++) {
            service.parse(document)
            println i
        }
        /*for (int k = 0; k < 1000; k++) {
            transmission.state = MachineState.INITIAL_STATE
            for (int i = 0; i < document.length; i++) {
                MachineState state = transmission.getState(document[i])
                state.toString()
            }
        }*/
        println "${System.currentTimeMillis() - before}"
    }
}
