package com.dogma.elements;

import java.util.List;

/**
 * Возвращает атрибуты элемента.
 */
public interface XmlAttributes {

    /**
     * Возвращает атрибуты элемента.
     */
    List<XmlAttribute> getAttributes();

    /**
     * Возвращает атрибут по имени.
     *
     * @param name имя атрибута.
     */
    XmlAttribute getAttribute(String name);
}
