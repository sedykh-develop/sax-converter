package com.dogma.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Простой тип в xml.
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SimpleType {

    /**
     * Тег эллемента.
     */
    String value();

    /**
     * Пространство имен эллемента.
     */
    String namespase() default "";
}
