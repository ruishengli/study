package com.inlook.or.study.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * desc ScopeActivity
 *
 * @author: or
 * @since: on 2016/7/1.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ScopeActivity {
    Class<?> value();
}
