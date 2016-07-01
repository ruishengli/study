package com.inlook.or.study.dagger;

import com.inlook.or.study.TestApplication;

import javax.inject.Singleton;
import dagger.Component;

/**
 * desc AppComponent
 *
 * @author: or
 * @since: on 2016/7/1.
 */

@Component(
        modules = {
                AppModule.class,
                DomainModule.class
        }
)
@Singleton
public interface AppComponent extends BaseComponent{

    TestApplication inject(TestApplication app);
}
