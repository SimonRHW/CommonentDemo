package com.simon.component;

import android.app.Application;

import com.simon.componentlib.IAppInitialization;
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
                if (instance instanceof IAppInitialization) {
                    ((IAppInitialization) instance).initialization(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
