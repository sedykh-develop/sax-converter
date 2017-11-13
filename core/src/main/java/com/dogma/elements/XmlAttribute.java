package com.dogma.elements;

/**
 * Описание атрибутов документа.
 */
public interface XmlAttribute {
    /**
     * Возвращает имя атрибута.
     */
    String getName();

    /**
     * Возвращает значение атрибута.
     */
    String getValue();
}
