package com.huluhive.user.fbandgooglelogin.di.modules;

import com.huluhive.user.fbandgooglelogin.di.scopes.PerActivity;
import com.huluhive.user.fbandgooglelogin.mvp.view.WelcomeView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 4/5/2017.
 */
@Module
public class WelcomeActivityModule {
    private WelcomeView mWelcomeView;
    public WelcomeActivityModule(WelcomeView welcomeView){
        mWelcomeView=welcomeView;
    }

    @Provides
    @PerActivity
    WelcomeView providesWelcomeView(){
        return mWelcomeView;
    }
}
