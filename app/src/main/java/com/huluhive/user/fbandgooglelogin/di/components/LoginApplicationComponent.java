package com.huluhive.user.fbandgooglelogin.di.components;

import android.content.Context;

import com.huluhive.user.fbandgooglelogin.di.modules.LoginApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by User on 4/5/2017.
 */
@Component(modules = LoginApplicationModule.class)
@Singleton
public interface LoginApplicationComponent {
    Context exposeContext();
}
