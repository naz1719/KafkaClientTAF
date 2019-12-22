package com.project.inftrastructure.middlewares.ui.annotations;


import com.project.inftrastructure.middlewares.ui.controls.base.ControlBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImplementedBy {
    Class<?> value() default ControlBase.class;
}

