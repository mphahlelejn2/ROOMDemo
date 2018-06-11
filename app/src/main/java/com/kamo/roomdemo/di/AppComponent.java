package com.kamo.roomdemo.di;

import com.kamo.roomdemo.db.Application_Database;
import com.kamo.roomdemo.rxJava.BaseSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;


@Singleton
@Component(modules={AppModule.class,UserModule.class})
public interface AppComponent {
    BaseSchedulerProvider getBaseSchedulerProvider();
    CompositeDisposable getCompositeDisposable();
    Application_Database getDatabase();
}
