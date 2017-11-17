package com.dogma.service

import org.junit.Test
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.Locator
import org.xml.sax.SAXException
import org.xml.sax.XMLReader
import org.xml.sax.helpers.DefaultHandler

import javax.xml.parsers.SAXParserFactory

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

    XMLReader getXmlReader() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true)
        factory.setValidating(false)
        factory.setNamespaceAware(true)
        XMLReader nonValidateXmlReader = factory.newSAXParser().getXMLReader()
        nonValidateXmlReader.setContentHandler(new DefaultHandler())
        return nonValidateXmlReader
    }

    @Test
    void testOne() {
        char [] document = new String(new File(this.getClass().getResource('/test-transmission.xml').getFile()).bytes)
                .toCharArray()
        long before = System.currentTimeMillis()
        ParserService service = new ParserService()
        for (int i = 0; i < 100000; i++) {
            service.parse(document)
        }
        println "${System.currentTimeMillis() - before}"
    }

    @Test
    void testTwo() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        XMLReader reader = factory.newSAXParser().getXMLReader();
        reader.setContentHandler(new org.xml.sax.ContentHandler() {
            @Override
            void setDocumentLocator(final Locator locator) {

            }

            @Override
            void startDocument() throws SAXException {

            }

            @Override
            void endDocument() throws SAXException {

            }

            @Override
            void startPrefixMapping(final String prefix, final String uri) throws SAXException {

            }

            @Override
            void endPrefixMapping(final String prefix) throws SAXException {

            }

            @Override
            void startElement(
                    final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
                //println ''
            }

            @Override
            void endElement(final String uri, final String localName, final String qName) throws SAXException {
                //println ''
            }

            @Override
            void characters(final char[] ch, final int start, final int length) throws SAXException {
                //println ''
            }

            @Override
            void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {

            }

            @Override
            void processingInstruction(final String target, final String data) throws SAXException {

            }

            @Override
            void skippedEntity(final String name) throws SAXException {

            }
        })

        byte[] document = new File(this.getClass().getResource('/test-transmission.xml').getFile()).bytes
        long before = System.currentTimeMillis()
        for (int i = 0; i < 100000; i++) {
            reader.parse(new InputSource(new ByteArrayInputStream(document)))
        }
        println "${System.currentTimeMillis() - before}"
    }
}
