package com.simon.minecomponent;

import android.app.Application;

import com.simon.componentlib.IAppInit;
import com.simon.componentlib.ServiceFactory;

public class MineApplication extends Application implements IAppInit {

    private static Application application;

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void initializa(Application application) {
        this.application = application;
        ServiceFactory.getInstance().setmMine(new MineService());

    }
}
