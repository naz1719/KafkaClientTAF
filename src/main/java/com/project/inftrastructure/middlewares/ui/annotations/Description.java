package com.project.inftrastructure.middlewares.ui.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Improves logging strategy. MAY be used for web elements in page objects.
 *
 * Name represents simple element name or description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Description {
    String name();
    String description() default "";
    String methodName() default "";
}
