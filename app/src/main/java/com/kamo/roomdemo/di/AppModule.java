package com.kamo.roomdemo.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.kamo.roomdemo.db.Application_Database;
import com.kamo.roomdemo.rxJava.BaseSchedulerProvider;
import com.kamo.roomdemo.rxJava.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by Jeffrey.Mphahlele on 3/19/2018.
 */
@Module
public class AppModule {

    private Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public Context getApplication() {
        return appContext;
    }

    @Provides
    @Singleton
    Application_Database getDatabase(Context context)
    {
        return  Room.databaseBuilder(context,Application_Database.class,"application_Database").build();
    }

    @Provides
    @Singleton
    BaseSchedulerProvider getBaseSchedulerProvider(){
        return SchedulerProvider.getInstance();
    }

    @Provides
    @Singleton
    CompositeDisposable getCompositeDisposable(){
        return new CompositeDisposable();
    }

}
