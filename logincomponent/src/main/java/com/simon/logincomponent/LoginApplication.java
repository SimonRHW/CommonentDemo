package com.simon.logincomponent;

import android.app.Application;

import com.simon.componentlib.IAppInitialization;
import com.simon.componentlib.ServiceFactory;

public class LoginApplication extends Application implements IAppInitialization {

    private static Application application;

    public static Application getApplication() {
        return application;
    }


    @Override
    public void initialization(Application application) {
        this.application = application;
        ServiceFactory.getInstance().setmLogin(new LoginService());

    }
}
