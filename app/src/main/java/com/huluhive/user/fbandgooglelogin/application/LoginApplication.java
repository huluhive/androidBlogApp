package com.huluhive.user.fbandgooglelogin.application;

import android.app.Application;

import com.huluhive.user.fbandgooglelogin.di.components.DaggerLoginApplicationComponent;
import com.huluhive.user.fbandgooglelogin.di.components.LoginApplicationComponent;
import com.huluhive.user.fbandgooglelogin.di.modules.LoginApplicationModule;

/**
 * Created by User on 4/5/2017.
 */

public class LoginApplication extends Application {

    private LoginApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeAppComponent();
    }

    private void initializeAppComponent() {
        mApplicationComponent= DaggerLoginApplicationComponent.builder()
                .loginApplicationModule(new LoginApplicationModule(this))
                .build();
    }

    public LoginApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
