package com.dogma.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Метаинформация ою элементе документа.
 */

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlElement {
    /**
     * Название тега, с которого начинается документ.
     */
    String value();

    /**
     * Пространство имен документа.
     */
    String namespace() default "";
}
