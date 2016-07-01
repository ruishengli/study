package com.inlook.or.study.dagger;

import android.app.Application;
import android.content.Context;

import com.inlook.or.study.TestApplication;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * desc AppModule
 *
 * @author: or
 * @since: on 2016/7/1.
 */

@Module
public class AppModule {

    private TestApplication mApplication;

    public AppModule(TestApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mApplication;
    }
}
