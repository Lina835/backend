package com.modeliosoft.modelio.javadesigner.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Annotation générée par Modelio.
 * Elle sert uniquement à tracer les IDs du modèle UML.
 * Pas nécessaire à l'exécution, mais utile pour compiler les classes générées.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface objid {
    String value();
}
