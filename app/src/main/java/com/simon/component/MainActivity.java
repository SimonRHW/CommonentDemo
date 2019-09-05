package com.simon.component;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.simon.componentlib.ServiceFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchMine(View view) {
        ServiceFactory.getInstance().getmMine().launcherMine(this);

    }

    public void launchLogin(View view) {
        ServiceFactory.getInstance().getmLogin().launcherLogin(this);
    }
}
