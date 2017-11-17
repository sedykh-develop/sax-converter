package com.dogma;

import com.dogma.annotations.XmlDocument;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by Дима on 16.11.2017.
 */
@SupportedAnnotationTypes({"com.dogma.annotations.XmlDocument"})
public class XmlDocumentAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(XmlDocument.class);
        for (Element element : elements) {
            System.out.println(element.getEnclosingElement().getClass());
        }
        return true;
    }
}
