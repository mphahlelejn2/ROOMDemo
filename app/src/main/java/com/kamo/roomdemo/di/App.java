package com.kamo.roomdemo.di;

import android.app.Application;


public class App extends Application {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(App application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }
}
