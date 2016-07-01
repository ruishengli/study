package com.inlook.or.study.dagger;

import com.inlook.or.study.activity.MainActivity;

import dagger.Component;

/**
 * desc ActivityCompontent
 *
 * @author: or
 * @since: on 2016/7/1.
 */
@Component(
        dependencies = AppComponent.class
)
@ScopeActivity(AppComponent.class)
public interface ActivityCompontent {
    void inject(MainActivity activity);
}
