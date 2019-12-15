package com.project.inftrastructure.middlewares.ui.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Improves logging strategy. MUST be used for page objects.
 *
 * Title represents simple page name or description
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Page {
    String title();
}

