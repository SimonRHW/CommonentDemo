package com.simon.logincomponent;

import android.app.Application;

import com.simon.componentlib.IAppInit;
import com.simon.componentlib.ServiceFactory;

public class LoginApplication extends Application implements IAppInit {

    private static Application application;

    public static Application getApplication() {
        return application;
    }


    @Override
    public void initializa(Application application) {
        this.application = application;
        ServiceFactory.getInstance().setmLogin(new LoginService());

    }
}
