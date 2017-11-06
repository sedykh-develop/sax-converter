package com.dogma.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Обозначает комплексный тип внутри документа.
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComplexType {

    /**
     * Тег обозначающий комплексный тип.
     */
    String value();

    /**
     * Пространство имен эллемента.
     */
    String namespace() default "";
}
