package com.project.inftrastructure.middlewares.ui.utils;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import com.project.inftrastructure.middlewares.ui.controls.base.Control;

public final class ImplementedByProcessor {
    private ImplementedByProcessor() {
    }

    public static <T> Class<?> getWrapperClass(Class<T> iface) {
        if (iface.isAnnotationPresent(ImplementedBy.class)) {
            ImplementedBy annotation = iface.getAnnotation(ImplementedBy.class);
            Class<?> clazz = annotation.value();
            if (Control.class.isAssignableFrom(clazz)) {
                return annotation.value();
            }
        }
        throw new UnsupportedOperationException("Apply @ImplementedBy interface to your Interface " + iface.getCanonicalName() + " if you want to extend ");
    }
}
