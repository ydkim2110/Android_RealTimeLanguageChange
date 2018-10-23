package com.example.anti2110.realtimelanguage;

import android.app.Application;
import android.content.Context;

import com.example.anti2110.realtimelanguage.Helper.LocaleHelper;

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}
