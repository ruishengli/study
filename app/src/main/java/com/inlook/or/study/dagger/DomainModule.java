package com.inlook.or.study.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * desc DomainModule
 *
 * @author: or
 * @since: on 2016/7/1.
 */

@Module
public class DomainModule {

    @Provides
    @Singleton
    public AnalyticsManager provideAnalyticsManager(Context context) {
        return new AnalyticsManager(context);
    }
}
