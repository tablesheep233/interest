package org.table.lifecycle.scope.annotation;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 * The interface Thread scope.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(org.table.lifecycle.scope.ThreadScope.SCOPE)
public @interface ThreadScope {
}
