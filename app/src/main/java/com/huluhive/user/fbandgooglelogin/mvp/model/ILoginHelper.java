package com.huluhive.user.fbandgooglelogin.mvp.model;

import com.huluhive.user.fbandgooglelogin.di.modules.LoginActivityModule;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by User on 4/5/2017.
 */

public interface ILoginHelper  {
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, LoginActivityModule.ResultCallback callback);
}
