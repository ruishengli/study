package com.inlook.or.study;

import android.app.Application;
import com.inlook.or.study.dagger.AnalyticsManager;
import com.inlook.or.study.dagger.AppComponent;
import com.inlook.or.study.dagger.AppModule;
import com.inlook.or.study.dagger.DaggerAppComponent;
import com.inlook.or.study.dagger.DomainModule;

import javax.inject.Inject;

/**
 * desc TestApplication
 *
 * @author: or
 * @since: on 2016/5/18.
 */
public class TestApplication extends Application {
    private static TestApplication mInstance;

    AppComponent mAppComponent = null;
    @Inject
    AnalyticsManager analyticsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .domainModule(new DomainModule())
                .build();

        mAppComponent.inject(this);
        analyticsManager.register();
    }


    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static TestApplication getInstance() {
        return mInstance;
    }
}
