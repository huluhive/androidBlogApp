package com.huluhive.user.fbandgooglelogin.di.components;

import com.huluhive.user.fbandgooglelogin.di.modules.LoginActivityModule;
import com.huluhive.user.fbandgooglelogin.di.scopes.PerActivity;
import com.huluhive.user.fbandgooglelogin.ui.activities.LoginActivity;

import dagger.Component;

/**
 * Created by User on 4/5/2017.
 */
@Component(modules = LoginActivityModule.class)
@PerActivity
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);

}
