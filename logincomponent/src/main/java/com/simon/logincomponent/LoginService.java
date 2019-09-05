package com.simon.logincomponent;

import android.content.Context;
import android.content.Intent;

import com.simon.componentlib.service.ILoginService;

public class LoginService implements ILoginService {
    @Override
    public void launcherLogin(Context context) {
        context.startActivity(new Intent(context,LoginActivity.class));
    }
}
