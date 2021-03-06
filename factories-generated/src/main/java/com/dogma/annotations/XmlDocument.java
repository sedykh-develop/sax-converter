package com.dogma.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация обозначающая сущность xml документа.
 */
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface XmlDocument {
    /**
     * Название тега, с которого начинается документ.
     */
    String value();

    /**
     * Пространство имен документа.
     */
    String namespace() default "";
}
