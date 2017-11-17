package com.dogma.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContentHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void attribute(final String name, final String value) {
        //log.info(String.format("attribute %s %s", name, value));
    }

    public void element(final String localName, final String name) {
        //log.info(String.format("element %s %s", localName, name));
    }

    public void value(final String value) {
        //log.info(String.format("value %s", value));
    }

    public void endElement(final String localName, final String name) {
        //log.info(String.format("end element %s %s", localName, name));
    }
}
