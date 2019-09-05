package com.simon.minecomponent;

import android.content.Context;
import android.content.Intent;

import com.simon.componentlib.service.IMineService;

public class MineService implements IMineService {

    @Override
    public void launcherMine(Context context) {
        context.startActivity(new Intent(context, MineActivity.class));
    }
}
