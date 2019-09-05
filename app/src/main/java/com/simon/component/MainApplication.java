package com.simon.component;

import android.app.Application;

import com.simon.componentlib.IAppInit;
import com.simon.componentlib.config.AppConfig;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        for (String component : AppConfig.component) {
            try {
                Class<?> clazz = Class.forName(component);
                Object instance = clazz.newInstance();
                if (instance instanceof IAppInit) {
                    ((IAppInit) instance).initializa(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
